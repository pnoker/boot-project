package com.pnoker.runner;

import com.pnoker.service.EmulatorService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author pnoker
 */
@Component
public class InitialRunner implements ApplicationRunner {
    @Resource
    private EmulatorService emulatorService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        emulatorService.initial();
    }
}
