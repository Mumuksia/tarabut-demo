package com.tarabut.retriever.services;

import com.tarabut.retriever.dto.Preferences;
import com.tarabut.retriever.dto.repository.PreferencesRepository;
import com.tarabut.retriever.mappers.MapperService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersistencePreferenceRetrieverTest{

    private static final String PRESENT_USER = "123";
    private static final String MISSING_USER = "1234";

    static PersistencePreferenceRetriever persistencePreferenceRetriever = new PersistencePreferenceRetriever();

    @BeforeAll
    static void init(){
        PreferencesRepository preferencesRepository = mock(PreferencesRepository.class);
        when(preferencesRepository.findFirstByUserIdentifierOrderByIdDesc(PRESENT_USER))
                .thenReturn(Optional.of(createPreference()));
        when(preferencesRepository.findFirstByUserIdentifierOrderByIdDesc(MISSING_USER))
                .thenReturn(Optional.empty());
        when(preferencesRepository.findFirstByUserIdentifierOrderByIdDesc(null))
                .thenThrow(IllegalArgumentException.class);

        MapperService mapperService = mock(MapperService.class);
        when(mapperService.mapToString(createPreference()))
                .thenReturn("{\"userIdentifier\":\"123\",\"sms\":true,\"post\":false,\"email\":true}");
        when(mapperService.mapToString(new Preferences()))
                .thenReturn("{}");

        persistencePreferenceRetriever.setPreferencesRepository(preferencesRepository);
        persistencePreferenceRetriever.setMapperService(mapperService);
    }

    @Test
    public void shouldRetrieveUserWhenPresent() {
        String testee = persistencePreferenceRetriever.retrievePreferencesForUserId(PRESENT_USER);
        Assertions.assertEquals("{\"userIdentifier\":\"123\",\"sms\":true,\"post\":false,\"email\":true}", testee);
    }

    @Test
    public void shouldRetrieveEmptyWhenNotPresent() {
        String testee = persistencePreferenceRetriever.retrievePreferencesForUserId(MISSING_USER);
        Assertions.assertEquals("{}", testee);
    }

    @Test
    public void shouldRetrieveEmptyWhenUserNull() {
        String testee = persistencePreferenceRetriever.retrievePreferencesForUserId(null);
        Assertions.assertEquals("{}", testee);
    }

    private static Preferences createPreference(){
        Preferences testee = new Preferences();
        testee.setEmail(true);
        testee.setPost(false);
        testee.setUserIdentifier(PRESENT_USER);
        testee.setSms(true);
        return testee;
    }
}
