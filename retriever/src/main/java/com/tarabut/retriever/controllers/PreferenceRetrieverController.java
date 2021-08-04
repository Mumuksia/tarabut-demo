package com.tarabut.retriever.controllers;

import com.tarabut.retriever.services.PreferenceRetrieverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PreferenceRetrieverController {

    @Autowired
    PreferenceRetrieverService preferenceRetrieverService;

    @GetMapping(value = "retrieve/preference/by")
    @ResponseBody
    public String getPreferencesByUser(@RequestParam(value = "user") String userId) {
        return preferenceRetrieverService.retrievePreferencesForUserId(userId);
    }
}
