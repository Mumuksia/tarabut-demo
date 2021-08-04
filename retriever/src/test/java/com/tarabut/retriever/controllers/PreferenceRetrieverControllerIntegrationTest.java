package com.tarabut.retriever.controllers;

import com.tarabut.retriever.BaseIntegrationTest;
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
@Sql({"/data/init.sql", "/data/insert.sql"})
public class PreferenceRetrieverControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldRetrievePreferencesIfPresent() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/retrieve/preference/by").queryParam("user", "123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"userIdentifier\":\"123\"")));
    }

    @Test
    public void shouldRetrieveEmptyPreferencesIfNotPresent() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/retrieve/preference/by").queryParam("user", "12"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{}")));
    }

    @Test
    public void shouldRetrieveLastPreferencesIfMultiplePresent() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/retrieve/preference/by").queryParam("user", "12345"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"userIdentifier\":\"12345\"")))
                .andExpect(content().string(containsString("\"email\":true")));
    }
}
