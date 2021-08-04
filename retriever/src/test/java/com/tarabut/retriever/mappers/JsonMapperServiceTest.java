package com.tarabut.retriever.mappers;

import com.tarabut.retriever.dto.Preferences;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonMapperServiceTest {

    @Test
    void mapToString_Success() {
        JsonMapperService mapperService = new JsonMapperService();

        Assertions.assertEquals("{\"userIdentifier\":\"123\",\"sms\":true,\"post\":false,\"email\":true}",
                mapperService.mapToString(createPreference()));
    }

    private static Preferences createPreference(){
        Preferences testee = new Preferences();
        testee.setEmail(true);
        testee.setPost(false);
        testee.setUserIdentifier("123");
        testee.setSms(true);
        return testee;
    }
}
