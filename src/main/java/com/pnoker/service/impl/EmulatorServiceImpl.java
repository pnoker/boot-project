package com.pnoker.service.impl;

import com.mongodb.client.MongoDatabase;
import com.pnoker.service.EmulatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author pnoker
 */
@Slf4j
@Service
public class EmulatorServiceImpl implements EmulatorService {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void initial() {
        MongoDatabase database = mongoTemplate.getDb();
        log.info("database: {}", database.getName());
    }
}
