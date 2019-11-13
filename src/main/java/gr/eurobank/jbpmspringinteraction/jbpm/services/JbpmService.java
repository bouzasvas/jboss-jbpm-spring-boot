package gr.eurobank.jbpmspringinteraction.jbpm.services;

import gr.eurobank.jbpmspringinteraction.jbpm.JbpmConfigurationProperties;
import org.kie.server.client.ProcessServicesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JbpmService {

    private JbpmConfigurationService jbpmConfigurationService;
    private JbpmConfigurationProperties jbpmConfigurationProperties;

    @Autowired
    public JbpmService(JbpmConfigurationService jbpmConfigurationService, JbpmConfigurationProperties jbpmConfigurationProperties) {
        this.jbpmConfigurationService = jbpmConfigurationService;
        this.jbpmConfigurationProperties = jbpmConfigurationProperties;
    }

    public void startProcess(String processId) {
        if (processId == null) processId = this.jbpmConfigurationProperties.getProcessId();

        ProcessServicesClient processServicesClient = this.jbpmConfigurationService.getProcessServicesClient();
        processServicesClient.startProcess(this.jbpmConfigurationProperties.getContainerId(), processId);
    }
}
