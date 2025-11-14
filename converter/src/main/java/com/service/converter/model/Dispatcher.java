package com.service.converter.model;

import com.service.converter.model.morse.MorseCodeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Dispatcher {
    @Autowired
    private MorseCodeConverter morse;

    public String convertToMorseCode(String text) {
        return morse.convertToCode(text);
    }

    public String convertMorseCodeToText(String code, String language) {
        return morse.convertToText(code, language);
    }
}
