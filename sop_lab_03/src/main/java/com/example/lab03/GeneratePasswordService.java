package com.example.lab03;

import org.springframework.web.bind.annotation.*;
import java.util.Random;

@RestController
public class GeneratePasswordService {
    @RequestMapping(value = "/{username}.generate", method = RequestMethod.GET)
    public String generate (@PathVariable String username) {
        String n = "012345678abcd9";

        StringBuilder n_password = new StringBuilder();
        Random rand = new Random();

        for(int i = 0; i < 10; i++) {
            int string_index = rand.nextInt(9);

//            System.out.printf(string_index + "");

            n_password.append(n.charAt(string_index));
        };
        return "Hi, " + username + "\n" + "Your new password is " + n_password;
    }
}
