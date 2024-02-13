package com.example.jin.controllers;

import com.example.jin.models.AuthorModel;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeApplication extends BaseController {

    @GetMapping("/")
    @ResponseBody
    public ArrayList<AuthorModel> get() {
        ArrayList<AuthorModel> arr = new ArrayList<>();
         
        AuthorModel author = new AuthorModel();
        author.setId(1);
        author.setName("@jinhuy");
        author.setEmail("JinHuy@gmail.com");
         
        arr.add(author);
         
        return arr;
    }
}
