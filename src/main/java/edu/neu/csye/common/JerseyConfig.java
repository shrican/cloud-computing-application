
/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/
package edu.neu.csye.common;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.neu.csye.tasks.endpoint.TasksEndpointController;
import edu.neu.csye.useraccount.endpoint.HomeEndpointController;
import edu.neu.csye.useraccount.endpoint.UserAccountEndointController;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        super.register(UserAccountEndointController.class);
        super.register(HomeEndpointController.class);
        super.register(TasksEndpointController.class);
        super.register(MultiPartFeature.class);
    }

    @Bean
    ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        return objectMapper;
    }
}
