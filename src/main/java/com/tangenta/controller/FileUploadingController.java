package com.tangenta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/upload", method = RequestMethod.POST)
public class FileUploadingController {
    private Logger logger = LoggerFactory.getLogger(FileUploadingController.class);

    @RequestMapping("/user-picture")
    @ResponseBody
    public String processProfilePictureUploading(
            @RequestPart("profilePicture")Part profilePicture) {
        logger.info("uploaded a profile picture: {}", profilePicture.name());
        return "test";
    }
}
