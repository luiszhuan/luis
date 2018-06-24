package com.luis.sites.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    private static final Logger LOGGER = LogManager.getLogger(DefaultController.class);

    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

}
