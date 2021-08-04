package com.tarabut.updater.services;

import com.tarabut.updater.dto.Preferences;
import com.tarabut.updater.dto.repository.PreferencesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersistencePreferenceUpdaterTest {

    private static final String NO_PREFERENCES_USER = "123";

    private static final PersistencePreferenceUpdater persistencePreferenceUpdater = new PersistencePreferenceUpdater();

    @BeforeAll
    static void init(){
        PreferencesRepository preferencesRepository = mock(PreferencesRepository.class);
        when(preferencesRepository.save(createPreference(NO_PREFERENCES_USER)))
                .thenReturn(createPreference(NO_PREFERENCES_USER));

        when(preferencesRepository.save(createPreference(null)))
                .thenThrow(IllegalArgumentException.class);

        persistencePreferenceUpdater.setPreferencesRepository(preferencesRepository);
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

    private static Preferences createPreference(String userId){
        return new Preferences(userId, false, false, false);
    }
}
