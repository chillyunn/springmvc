package com.jirandata.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/datatable")
    public String datatable(){
        return "datatable";
    }
    @GetMapping("/modal")
    public String modal(){
        return "modal";
    }

    @GetMapping("/organization")
    public String organization(){
        return "organization";
    }
}
