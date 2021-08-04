package com.tarabut.updater.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JsonMapperService implements MapperService{

    private final Logger logger = LoggerFactory.getLogger(JsonMapperService.class);
    private final static String NO_DATA = "{}";

    @Override
    public <T> String mapToString(T obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Exception when parsing preferences output", e);
            return NO_DATA;
        }
    }
}
