package gr.eurobank.jbpmspringinteraction.jbpm.services;

import gr.eurobank.jbpmspringinteraction.jbpm.JbpmConfigurationProperties;
import gr.eurobank.jbpmspringinteraction.jbpm.exceptions.JpbmKieServerConnectionException;
import lombok.extern.slf4j.Slf4j;
import org.kie.remote.common.rest.KieRemoteHttpRequestException;
import org.kie.server.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class JbpmConfigurationService {

    private KieServicesClient kieServicesClient;
    private JbpmConfigurationProperties jbpmProperties;

    @Autowired
    public JbpmConfigurationService(JbpmConfigurationProperties jbpmProperties) {
        this.jbpmProperties = jbpmProperties;
    }

    @PostConstruct
    public void initJbpmService() {
        log.info("JbpmConfigurationService :: initJbpmService :: Method Entered on @PostConstruct phase");

        this.initKieServicesFactory();

        log.info("JbpmConfigurationService :: initJbpmService :: Method Returned");
    }

    public RuleServicesClient getRuleServicesClient() {
        if (this.kieServicesClient != null) {
            return this.kieServicesClient.getServicesClient(RuleServicesClient.class);
        }

        return null;
    }

    public ProcessServicesClient getProcessServicesClient() {
        if (this.kieServicesClient != null) {
            return this.kieServicesClient.getServicesClient(ProcessServicesClient.class);
        }

        return null;
    }

    private void initKieServicesFactory() {
        KieServicesConfiguration kieServicesConfiguration = this.initKieRestConfiguration();

        try {
            this.kieServicesClient = KieServicesFactory.newKieServicesClient(kieServicesConfiguration);
        } catch (KieRemoteHttpRequestException httpException) {
            throw new JpbmKieServerConnectionException(this.jbpmProperties.getUrl());
        }
    }

    private KieServicesConfiguration initKieRestConfiguration() {
        KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(
                this.jbpmProperties.getUrl(),
                this.jbpmProperties.getUsername(),
                this.jbpmProperties.getPassword());
        conf.setMarshallingFormat(this.jbpmProperties.getMarshallingFormat());

        return conf;
    }
}
