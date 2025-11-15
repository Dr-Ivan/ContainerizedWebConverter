package com.service.converter.model.morse;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class MorseCodeConverter {
    private final Map<String, String> symbols;
    private final Map<String, String> russian;
    private final Map<String, String> english;

    MorseCodeConverter() {
        ObjectMapper mapper = new ObjectMapper();
        ClassPathResource symbols = new ClassPathResource("dictionaries\\Symbols.json");
        ClassPathResource russian = new ClassPathResource("dictionaries\\RussianLetters.json");
        ClassPathResource english = new ClassPathResource("dictionaries\\EnglishLetters.json");
        Map s = new HashMap<>(), rus = new HashMap<>(), eng = new HashMap<>();
        try {
            s = mapper.readValue(symbols.getInputStream(), HashMap.class);
            rus = mapper.readValue(russian.getInputStream(), HashMap.class);
            eng = mapper.readValue(english.getInputStream(), HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.symbols = s;
        this.russian = rus;
        this.english = eng;
        // System.out.println(this.symbols);
    }

    private String encodeWord(String text) {
        text = text.toUpperCase();
        String[] res = new String[text.length()];
        char letter;
        for (int i = 0; i < text.length(); ++i) {
            letter = text.charAt(i);
            if (letter <= 'Z' && letter >= 'A') res[i] = english.get(String.valueOf(letter));
            else if (letter <= 'Я' && letter >= 'А') res[i] = russian.get(String.valueOf(letter));
            else res[i] = symbols.getOrDefault(String.valueOf(letter), " ");
        }

        return String.join(" ", res); // при кодировании слова между буквами один пробел
    }

    public String convertToCode(String text) {
        text = text.toUpperCase().replace('Ё', 'е');
        String[] words = text.split(" ");
        String[] res = new String[words.length];
        for (int i = 0; i < words.length; ++i) {
            res[i] = encodeWord(words[i]);
        }

        return String.join("  ", res); // при соединении слов между словами два пробела
    }

    public String convertToText(String code, String language) {
        Map<String, String> letters;
        if (language.equals("ru")) letters = russian;
        else  letters = english;

        String[] codes = code.split(" ");
        String[] res = new String[codes.length];

        int i = 0;
        for (String morse: codes) {
            if (!morse.isBlank()) // буквы разделены одним пробелом
                res[i++] = letters.getOrDefault(morse, symbols.getOrDefault(morse," "));
            else    // если больше одного пробела - это пробел между словами
                res[i++] = " ";
        }

        return String.join("", res);
    }

    @PostConstruct
    public static void main() {
        MorseCodeConverter t = new MorseCodeConverter();
        System.out.println(t.convertToCode("АМОГУС - анимогус"));
        System.out.println(t.convertToText("•– –– ––– ––• ••– •••  ––––––  •– –• •• –– ––– ––• ••– •••",
                "ru"));
    }
//        ObjectMapper mapper = new ObjectMapper();
//        HashMap<String, String> letters = new HashMap<>();
//        HashMap<String, String> symbols = new HashMap<>();
//
//        String filePath = "D:\\Загрузки\\converter\\converter\\src\\main\\java\\com\\service\\converter\\model\\morse\\codes.txt";
//
//        try (Scanner scanner = new Scanner(new File(filePath))) {
//            while (scanner.hasNextLine()) {
//                String[] line = scanner.nextLine().split("\t");
//                System.out.println(Arrays.toString(line));
//                if (Character.isLetter(line[0].charAt(0))) {
//                    letters.put(line[0], line[1]);
//                    letters.put(line[1], line[0]);
//                } else {
//                    symbols.put(line[0], line[1]);
//                    symbols.put(line[1], line[0]);
//                }
//            }
//
//            String s1 = mapper.writeValueAsString(letters);
//            String s2 = mapper.writeValueAsString(symbols);
//            System.out.println(s1);
//            System.out.println(s2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}
