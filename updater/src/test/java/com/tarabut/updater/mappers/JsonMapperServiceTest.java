package com.tarabut.updater.mappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.tarabut.updater.util.PreferencesBuilder.createFalsePreference;

class JsonMapperServiceTest {

    private static final String PREFERENCES_USER = "123";

    @Test
    void mapToString_Success() {
        JsonMapperService mapperService = new JsonMapperService();

        Assertions.assertEquals("{\"userIdentifier\":\"123\",\"sms\":false,\"post\":false,\"email\":false}", mapperService.mapToString(createFalsePreference(PREFERENCES_USER)));
    }
}
