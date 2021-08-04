package com.tarabut.updater.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarabut.updater.dto.Preferences;
import com.tarabut.updater.dto.repository.PreferencesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistencePreferenceUpdater implements PreferenceUpdaterService {

    private final Logger logger = LoggerFactory.getLogger(PersistencePreferenceUpdater.class);
    private final static String NO_DATA = "{}";

    @Autowired
    PreferencesRepository preferencesRepository;

    @Override
    public String updatePreferenceForUser(String userId, String sms, String post, String email) {
        try {
            Preferences preferences = preferencesRepository.save(new Preferences(userId, Boolean.valueOf(sms),
                    Boolean.valueOf(post), Boolean.valueOf(email)));
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(preferences);
        }
        catch (IllegalArgumentException ie){
            logger.error("User identifier is null", ie);
            return NO_DATA;
        }
        catch (JsonProcessingException e) {
            logger.error("Exception when parsing preferences output", e);
            return NO_DATA;
        }
    }

    public void setPreferencesRepository(PreferencesRepository preferencesRepository) {
        this.preferencesRepository = preferencesRepository;
    }
}
