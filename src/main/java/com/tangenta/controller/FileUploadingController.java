package com.tangenta.controller;

import com.tangenta.repositories.PictureRepository;
import com.tangenta.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping(value = "/upload", method = RequestMethod.POST)
public class FileUploadingController {
    private Logger logger = LoggerFactory.getLogger(FileUploadingController.class);

    private PictureRepository pictureRepository;
    private ValidationService validationService;

    @Value("${profileImage-dir}")
    private String profileImgPath;

    public FileUploadingController(PictureRepository pictureRepository, ValidationService validationService) {
        this.pictureRepository = pictureRepository;
        this.validationService = validationService;
    }

    @RequestMapping("/user-picture")
    public ResponseEntity<?> processProfilePictureUploading(
            @RequestParam("profilePicture") MultipartFile profilePicture,
            @RequestParam("studentId") Long studentId) {
        try {
            if (profilePicture.isEmpty() || !validationService.userExist(studentId))  {
                logger.info("ignore empty profile picture and studentId");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Path fileSavingDir = Paths.get(profileImgPath);
            if (!fileSavingDir.toFile().exists()) fileSavingDir.toFile().mkdirs();

            String randomFilename = UUID.randomUUID().toString();
            File file = fileSavingDir.resolve(studentId.toString() + "-" + randomFilename).toFile();
            profilePicture.transferTo(file);

            pictureRepository.setUserPicture(studentId, studentId.toString() + "-" + randomFilename);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("uploaded a profile picture: {} for student: {}", profilePicture, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
