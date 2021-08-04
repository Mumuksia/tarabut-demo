package com.tarabut.updater.controllers;

import com.tarabut.updater.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@Transactional
@Sql({"/data/init.sql"})
class PreferenceRetrieverControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldUpdatePreferences() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/update/preference").queryParam("user", "123")
                .queryParam("sms", "true").queryParam("email", "true").queryParam("post", "true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"userIdentifier\":\"123\"")))
                .andExpect(content().string(containsString("\"email\":true")));
    }

    @Test
    public void shouldUpdatePreferencesWithoutSms() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/update/preference").queryParam("user", "1234"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"userIdentifier\":\"1234\"")))
                .andExpect(content().string(containsString("\"email\":false")));
    }
}
