package com.tarabut.retriever.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarabut.retriever.dto.Preferences;
import com.tarabut.retriever.dto.repository.PreferencesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistencePreferenceRetriever implements PreferenceRetrieverService {

    private final Logger logger = LoggerFactory.getLogger(PersistencePreferenceRetriever.class);

    private final static String EMPTY_DATA = "{}";

    @Autowired
    PreferencesRepository preferencesRepository;

    @Override
    public String retrievePreferencesForUserId(String userIdentifier) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(preferencesRepository.findFirstByUserIdentifierOrderByIdDesc(userIdentifier).orElse(new Preferences()));
        }
        catch (IllegalArgumentException ie){
            logger.error("User identifier is null", ie);
            return EMPTY_DATA;
        }
        catch (JsonProcessingException e) {
            logger.error("Exception when parsing preferences output", e);
            return EMPTY_DATA;
        }
    }

    public void setPreferencesRepository(PreferencesRepository preferencesRepository) {
        this.preferencesRepository = preferencesRepository;
    }
}
