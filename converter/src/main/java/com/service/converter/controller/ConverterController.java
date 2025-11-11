package com.service.converter.controller;

import com.service.converter.model.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/convert")
public class ConverterController {
    @Autowired
    private Dispatcher model;

    @GetMapping("/test")
    public String test(){
        return "Тестовый GET работает!";
    }

    @PostMapping("/test")
    public String test(@RequestBody String t){
        return "String input: "+t;
    }

    @PostMapping("/morseEncode")
    public String morseEncode(@RequestBody String txt){
        return model.convertToMorseCode(txt);
    }

    @PostMapping("/morseDecode/{language}")
    public String morseEncode(@RequestBody String txt, @PathVariable String language){
        return model.convertMorseCodeToText(txt, language);
    }

}
