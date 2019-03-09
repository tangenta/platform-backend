package com.tangenta.controller;

import com.tangenta.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterAcceptingController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterAcceptingController.class);
    private RegisterService registerService;

    public RegisterAcceptingController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping("/register")
    @ResponseBody
    public String handleRegisterToken(@RequestParam("token") String token) {
        logger.info("handleRegisterToken called with token = {}", token);
        if (registerService.validateRegisterToken(token)) {
            return "ok";
        } else {    // token is invalid
            return "expired";
        }
    }
}
