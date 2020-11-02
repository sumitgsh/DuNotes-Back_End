package com.project.website.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.website.model.Media;
import com.project.website.model.User;
import com.project.website.repository.MediaRepository;
import com.project.website.repository.UserRepository;
import com.project.website.service.MediaFileStoreService;
import com.project.website.service.PaperType;

import utility.Constants;

@CrossOrigin("*")
@RestController
public class MediaController {
	
	public static final String UPLOAD_FOLDER = "D:\\EasyAccess.com\\website\\src\\main\\resources\\images\\";
	
	
	@Autowired
	private MediaFileStoreService mediafilestorageservice;
	
	@Autowired
	private MediaRepository media;
	
	@Autowired
	private UserRepository userRep;

	@CrossOrigin("*") 
	@PostMapping("api/v1/media/{user_id}")
	public ResponseEntity<String> addMedia(@RequestParam("media") String mediaJson,
							@PathVariable int user_id,
							@RequestParam("file") MultipartFile file,
							RedirectAttributes redirectAttributes)
							throws JsonParseException, JsonMappingException, IOException
	{	
		if(!(file.getOriginalFilename().endsWith(Constants.PNG_FILE_FORMAT)||
				file.getOriginalFilename().endsWith(Constants.JPEG_FILE_FORMAT)||
				file.getOriginalFilename().endsWith(Constants.JPG_FILE_FORMAT)||
				file.getOriginalFilename().endsWith(Constants.PDF_FILE_FORMAT)))
		{
			throw new RuntimeException(Constants.INVALID_FILE_FORMAT);
		}
		if (file.isEmpty()) {
			return new ResponseEntity<>("Please select a file to upload",HttpStatus.BAD_REQUEST);
			}

		try {
			Media media=mediafilestorageservice.storeFile(file);
			System.out.println(media.getId());
			//Download path URI
			
			 String fileDownloadUri=ServletUriComponentsBuilder.fromCurrentContextPath().
					path(Constants.DOWNLOAD_PATH).
					path(String.valueOf(media.getId())).
					toUriString();
			 
			 media.setDownloadUri(fileDownloadUri);
			 ObjectMapper mapper = new ObjectMapper();
	
			 Media mediadet= mapper.readValue(mediaJson, Media.class);
			 
		
			 
			 //get the user detials based on id
			 Optional<User> curUser=userRep.findById(user_id);
			 
			 media.setUser(curUser.get());
			 media.setDepartment(mediadet.getDepartment());
			 media.setDegreeName(mediadet.getDegreeName());
			 media.setSubject(mediadet.getSubject());
			 media.setPaperYear(mediadet.getPaperYear());
			 media.setSemester(mediadet.getSemester());
			 media.setTopic(mediadet.getTopic());
			 media.setDesc(mediadet.getDesc());
			 media.setPaperType(mediadet.getPaperType());
			 media.setImportantLinks(mediadet.getImportantLinks());
			 media.setVerified(false);
			 media.setDateOfUpload(LocalDate.now());
			 Path path = Paths.get(UPLOAD_FOLDER + media.getPath());
			 Files.write(path, file.getBytes());
			 this.media.save(media);
			 	
			 return new ResponseEntity<>("Upload Successfull", HttpStatus.OK);
	
			}
			catch (IOException e)
			{
			return new ResponseEntity<>("Something Went Wrong",HttpStatus.BAD_REQUEST);
			}
		
		}
	
	//Get all the un-verified medias
	@GetMapping("api/v1/medias/un-verified")
	public List<Media> mediaUnVerified()
	{
			return media.findByMediaUnverified(false);
	}
	
	@GetMapping("api/v1/medias/verified")
	public List<Media> media()
	{
			return media.findByMediaVerified(true);
	}
	
	//For downloading the file
	@GetMapping("api/v1/media/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int fileId) throws FileNotFoundException {
        // Load file from database
		Optional<Media> dbFile = this.media.findById(fileId);
		Resource file = mediafilestorageservice.loadAsResource(dbFile.get().getPath());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/png"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename()+ "\"")
                .body(file);
    }
	
	//For viewing the file
	@GetMapping("api/v1/media/viewFile/{fileId}")
    public ResponseEntity<Resource> viewFile(@PathVariable int fileId) throws FileNotFoundException {
        // Load file from database
		Optional<Media> dbFile = this.media.findById(fileId);
		Resource file = mediafilestorageservice.loadAsResource(dbFile.get().getPath());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.get().getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename()+ "\"")
                .body(file);
    }
	 

//	@GetMapping("api/v1/media/{userId}")
//	public List<Media> getAllMediaByUserId(@PathVariable("userId") int userId)
//									
//	{	
//	List<Media> res=media.findMediaByUid(userId);
//	
//	return res;
//	//	return media.findAll();
//		
//	}
	
	@GetMapping("api/v1/media/{id}")
	public Media findMediaById(@PathVariable int id)
	{
		Optional<Media> optional=media.findById(id);
		
		if(!optional.isPresent())
		{
			throw new RuntimeException("No Media found with user id:"+id);
		}
		return optional.get();
		
	}


	@DeleteMapping("api/v1/media/{id}")
	public void deleteMediaById(@PathVariable int id)
	{
		media.deleteById(id);
	}

	
	@PutMapping(value="api/v1/media/{id}",produces="text/html")
	public ResponseEntity<String> updateMediaById(@PathVariable int id, @RequestBody Media media) {
	
		Optional<Media> optional=this.media.findById(id);
		User medUser=userRep.findByUserName(optional.get().getUser());
		
		if(!optional.isPresent()) {
			throw new RuntimeException("No media found with user id:"+id);
		}
		media.setId(optional.get().getId());
		media.setVerified(true);
		media.setUser(medUser);
		this.media.save(media);
		return new ResponseEntity<>("Successfully updated",HttpStatus.ACCEPTED);
		
	}
	

}
