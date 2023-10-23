package com.test.Authenticate.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @ResponseBody
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String index(){
        return "hello";
    }

    @ResponseBody
    @RequestMapping(value = "/about",method = RequestMethod.GET)
    public String about(){	
        return "about";
    }
     	
}