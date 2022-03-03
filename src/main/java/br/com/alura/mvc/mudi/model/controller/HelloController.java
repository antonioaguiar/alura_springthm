package br.com.alura.mvc.mudi.model.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    //setando valor para a view usando HttpServletRequest
    @GetMapping("/hello1")
    public String hello1(HttpServletRequest request){
        request.setAttribute("nome", "Antonio");
        return "hello";
    }

    //setando valor usando classe Model do spring
    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("nome", "Antonio");
        return "hello";
    }

}
