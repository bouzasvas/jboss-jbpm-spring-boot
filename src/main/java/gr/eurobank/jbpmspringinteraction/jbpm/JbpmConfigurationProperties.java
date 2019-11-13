package gr.eurobank.jbpmspringinteraction.jbpm;

import lombok.Data;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:jbpm.properties")
@ConfigurationProperties(prefix = "jbpm")
@Data
public class JbpmConfigurationProperties {
    private String url;
    private String username;
    private String password;
    private String containerId;
    private String processId;
    private MarshallingFormat marshallingFormat;
}
