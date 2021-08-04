package com.tarabut.updater.services;

import com.tarabut.updater.dto.repository.PreferencesRepository;
import com.tarabut.updater.mappers.MapperService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tarabut.updater.util.PreferencesBuilder.createFalsePreference;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersistencePreferenceUpdaterTest {

    private static final String NO_PREFERENCES_USER = "123";

    private static final PersistencePreferenceUpdater persistencePreferenceUpdater = new PersistencePreferenceUpdater();

    @BeforeAll
    static void init(){
        PreferencesRepository preferencesRepository = mock(PreferencesRepository.class);
        when(preferencesRepository.save(createFalsePreference(NO_PREFERENCES_USER)))
                .thenReturn(createFalsePreference(NO_PREFERENCES_USER));

        when(preferencesRepository.save(createFalsePreference(null)))
                .thenThrow(IllegalArgumentException.class);

        MapperService mapperService = mock(MapperService.class);
        when(mapperService.mapToString(createFalsePreference(NO_PREFERENCES_USER)))
                .thenReturn("{\"userIdentifier\":\"123\",\"sms\":false,\"post\":false,\"email\":false}");

        persistencePreferenceUpdater.setPreferencesRepository(preferencesRepository);
        persistencePreferenceUpdater.setMapperService(mapperService);
    }

    @Test
    void updatePreferenceForUser_Success() {
        String testee = persistencePreferenceUpdater.updatePreferenceForUser(NO_PREFERENCES_USER, "false", "false", "false");
        Assertions.assertEquals("{\"userIdentifier\":\"123\",\"sms\":false,\"post\":false,\"email\":false}", testee);
    }

    @Test
    void updatePreferenceForUser_NullUser() {
        String testee = persistencePreferenceUpdater.updatePreferenceForUser(null, "false", "false", "false");
        Assertions.assertEquals("{}", testee);
    }


}
