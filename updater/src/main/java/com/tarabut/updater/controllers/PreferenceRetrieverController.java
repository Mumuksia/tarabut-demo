package com.tarabut.updater.controllers;

import com.tarabut.updater.services.PreferenceUpdaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PreferenceRetrieverController {

    @Autowired
    PreferenceUpdaterService preferenceUpdaterService;

    @GetMapping(value = "update/preference")
    @ResponseBody
    public String updateUserPreferences(@RequestParam(value = "user") String userId, @RequestParam(value = "sms") Optional<String> sms,
                                          @RequestParam(value = "post") Optional<String> post, @RequestParam(value = "email") Optional<String> email) {
        return preferenceUpdaterService.updatePreferenceForUser(userId, sms.orElse("false"), post.orElse("false"), email.orElse("false"));
    }
}
