package com.tarabut.retriever.services;

import com.tarabut.retriever.dto.Preferences;
import com.tarabut.retriever.dto.repository.PreferencesRepository;
import com.tarabut.retriever.mappers.MapperService;
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
    @Autowired
    MapperService mapperService;

    @Override
    public String retrievePreferencesForUserId(String userIdentifier) {
        try {
            return mapperService.mapToString(preferencesRepository.findFirstByUserIdentifierOrderByIdDesc(userIdentifier).orElse(new Preferences()));
        }
        catch (IllegalArgumentException ie){
            logger.error("User identifier is null", ie);
            return EMPTY_DATA;
        }
    }

    public void setPreferencesRepository(PreferencesRepository preferencesRepository) {
        this.preferencesRepository = preferencesRepository;
    }

    public void setMapperService(MapperService mapperService) {
        this.mapperService = mapperService;
    }
}
