package br.com.fiap.cashflowpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CategoriaController {

    @RequestMapping(method = RequestMethod.GET , path = "/categoria", produces = "application/json")
    @ResponseBody
    public String index(){
        return "lista";
    } 
    
}
