package edu.neu.csye.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.neu.csye.useraccount.endpoint.UserAccountEndointController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.core.JsonParser.Feature;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        super.register(UserAccountEndointController.class);
    }

    @Bean
    ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        return objectMapper;
    }
}
