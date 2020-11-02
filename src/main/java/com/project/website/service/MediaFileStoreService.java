package com.project.website.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.project.website.model.Media;
import com.project.website.repository.MediaRepository;

@Service
public class MediaFileStoreService  {

	
	
	@Autowired
	private MediaRepository media;
	
	private final Path rootLocation = Paths.get("src/main/resources/images");
	
    public  Media storeFile(MultipartFile file) throws FileSystemException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileSystemException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            String fileNameUnique=UUID.randomUUID().toString()+fileName;
            Media mediaFile = new Media(fileNameUnique,file.getSize(),file.getContentType());
            
            return  this.media.save(mediaFile);
        } 
        catch (FileSystemException ex) {
            throw new FileSystemException("Could not store file ");
        }
    }
    
   
    
    public Resource loadAsResource(String filename) throws FileNotFoundException {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            System.out.println(resource);
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new FileNotFoundException(
                        "Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not read file: " + filename);
        }
    }

//    public Optional<Media> getFile(String fileId) {
//        return media.findById(fileId);
////                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
//    }
}
