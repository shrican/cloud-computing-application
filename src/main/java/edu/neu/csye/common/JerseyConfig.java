
/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/
package edu.neu.csye.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.neu.csye.useraccount.endpoint.HomeEndpointController;
import edu.neu.csye.useraccount.endpoint.UserAccountEndointController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.core.JsonParser.Feature;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        super.register(UserAccountEndointController.class);
        super.register(HomeEndpointController.class);
    }

    @Bean
    ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        return objectMapper;
    }
}
