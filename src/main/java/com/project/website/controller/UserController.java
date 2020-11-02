package com.project.website.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.website.model.Student;
import com.project.website.model.User;
import com.project.website.repository.StudentRepository;
import com.project.website.repository.UserRepository;
import com.project.website.service.MailService;

import utility.Constants;

@RestController
public class UserController {                                                                                                                                        

	public static final String UPLOAD_FOLDER = "D:\\EasyAccess.com\\website\\src\\main\\resources\\profilePhoto\\";
	
	@Autowired
	private UserRepository users;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private StudentRepository student;

	static String path="";
	
		
	@CrossOrigin("*")
	@PostMapping("api/v1/users")
	public ResponseEntity<User> addUsers(@RequestBody User user) throws Exception
	{
		//testing
		
		HttpHeaders headers = new HttpHeaders();
				
		User optional= this.users.findByUserName(user.getUserName());
		ResponseEntity<User> responseEntity =null;

		
			if(optional!=null)
			{
				headers.add("message", "User name already exists");
				 responseEntity =
						 new ResponseEntity<User>(
					          user, headers, HttpStatus.BAD_REQUEST);
			}
			else
			{
				
			responseEntity =new ResponseEntity<User>(user, headers, HttpStatus.ACCEPTED);
			user.setRegisterDate(LocalDate.now());
			user.setToken("acs-asdc-ss");
			user.setStudent(new Student(user));
			this.users.save(user);
			//Insert entry to the student table
			//mailService.sendMail(user);
			
			
			}
		return responseEntity;
	
		
	}
	/*Use to token to validate the user*/
	@GetMapping("api/v1/users/authenticate/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable String token)
	{
		
		String Actualtoken="acs-asdc-ss";
		if(Actualtoken.equals(token))
		{
			
			User user=users.findByToken(token);
			user.setProfileActive(true);	
			users.save(user);
				
			return new ResponseEntity<>("Account Activated Successfully",HttpStatus.OK);
		}else
		{
			throw new NullPointerException("Invalid Token");
			
		}
	}
	@CrossOrigin("*")
	@PostMapping("api/v1/users/login")
	public ResponseEntity<User> authenticate(@RequestBody User user  )
	{
		//token validation is not included
	
		Optional<User>  auth=users.findByUserNameAndPas(user.getUserName(),user.getPassword());
		if(!auth.isPresent())
		{
			throw new RuntimeException("No Such user with that user name found");
		}
		return new ResponseEntity<User>(auth.get(),HttpStatus.ACCEPTED);
	}
	
	@PostMapping("api/v1/users/{id}/uploadPhoto")
	public ResponseEntity<String> singleFileUpload(@PathVariable int id,@RequestBody User user,
									@RequestParam("file") MultipartFile file,
									RedirectAttributes redirectAttributes) {
			
		Optional<User> optional=this.users.findById(id);
		
		if(!optional.isPresent()) {
			throw new RuntimeException("No User found with user id:"+id);
		}else 
		{
		if(!(file.getOriginalFilename().endsWith(Constants.PDF_FILE_FORMAT)||
				file.getOriginalFilename().endsWith(Constants.JPEG_FILE_FORMAT)||
				file.getOriginalFilename().endsWith(Constants.JPG_FILE_FORMAT)||
				file.getOriginalFilename().endsWith(Constants.PNG_FILE_FORMAT)))
		{
			throw new RuntimeException(Constants.INVALID_FILE_FORMAT);
		}
		if (file.isEmpty()) {
			throw new RuntimeException("Please select a file to upload");
			}

		try {
			// Get the file and save it database
			String fileName=file.getOriginalFilename();
			String fileDownloadUri=ServletUriComponentsBuilder.fromCurrentContextPath().
					path(Constants.DOWNLOAD_PATH).path(fileName).toUriString();
					
			
			byte[] bytes = file.getBytes();
				Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
				Files.write(path, bytes);
				
				user.setPhotoUrl(path.toString());
				this.users.save(user);

			} catch (IOException e) {
				e.printStackTrace();
				}
	
		}
		return new ResponseEntity<String>("Successfully Uploaded Images",HttpStatus.ACCEPTED);
		}
	
	
	
	@GetMapping("api/v1/users")
	public List<User> getAllUsers()
	{
		return users.findAll();
		
	}
	@CrossOrigin("*")
	@GetMapping("api/v1/users/{id}")
	public User findUsersById(@PathVariable int id)
	{
		Optional<User> optional=users.findById(id);
		
		if(!optional.isPresent()) {
			throw new RuntimeException("No User found with user id:"+id);
		}
		return optional.get();
		
	}


	@DeleteMapping("api/v1/users/{id}")
	public void deleteUserById(@PathVariable int id)
	{
		users.deleteById(id);
	}
	
	@CrossOrigin("*")
	@PutMapping(value="api/v1/users/{id}",produces="text/html")
	public ResponseEntity<String> updateUserById(@PathVariable int id, @RequestBody User user) {
	
		
		Optional<User> optional=users.findById(id);
		
		if(!optional.isPresent()) {
			throw new RuntimeException("No User found with user id:"+id);
		}else
		{
//		user.setPhotoUrl(path);
		user.setId(optional.get().getId());
		users.save(user);
		return new ResponseEntity<String>("Updated Successfully",HttpStatus.ACCEPTED);
		}
	}
		
}
