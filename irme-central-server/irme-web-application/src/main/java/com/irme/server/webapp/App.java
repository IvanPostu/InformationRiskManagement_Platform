package com.irme.server.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(App.class);

        logger.debug("Hello world from slf4j");
        logger.debug("This logger supports confidentail messages....");

        // com.irme.common.App.main(new String[] {});
        // app.run(args);

        logger.debug("Hello world from slf4j");
        logger.debug("This logger supports confidentail messages....");
    }

}


