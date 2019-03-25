package com.tangenta.controller;

import com.tangenta.repositories.PictureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;

@Controller
@RequestMapping(value = "/upload", method = RequestMethod.POST)
public class FileUploadingController {
    private Logger logger = LoggerFactory.getLogger(FileUploadingController.class);

    private PictureRepository pictureRepository;

    @Value("${profileImage-dir}")
    private String profileImgPath;

    public FileUploadingController(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @RequestMapping("/user-picture")
    public ResponseEntity<?> processProfilePictureUploading(
            @RequestParam("profilePicture") MultipartFile profilePicture,
            @RequestParam("studentId") Long studentId) {
        try {
            if (profilePicture.isEmpty()) return new ResponseEntity<>(HttpStatus.OK);

            logger.info("transfer to: {}", profileImgPath);
            File file = Paths.get(profileImgPath).resolve(profilePicture.getName()).toFile();
            profilePicture.transferTo(file);

            pictureRepository.setUserPicture(studentId, file.getName());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("uploaded a profile picture: {} for student: {}", profilePicture, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
