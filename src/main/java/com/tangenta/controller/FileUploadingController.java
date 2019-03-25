package com.tangenta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.file.Paths;

@Controller
@RequestMapping(value = "/upload", method = RequestMethod.POST)
public class FileUploadingController {
    private Logger logger = LoggerFactory.getLogger(FileUploadingController.class);

    @RequestMapping("/user-picture")
    public ResponseEntity<?> processProfilePictureUploading(
            @RequestParam("profilePicture") MultipartFile profilePicture,
            @RequestParam("studentId") Long studentId) {
        try {
            ClassPathResource classPathResource = new ClassPathResource("/resources/picture");
//            logger.info("")
            boolean res = Paths.get(classPathResource.getPath()).resolve("test.txt").toFile().createNewFile();
//            boolean res = Paths.get("classpath:/resources/picture/test.txt").toFile().createNewFile();
//            logger.info("--{}", res);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("uploaded a profile picture: {} for student: {}", profilePicture, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
