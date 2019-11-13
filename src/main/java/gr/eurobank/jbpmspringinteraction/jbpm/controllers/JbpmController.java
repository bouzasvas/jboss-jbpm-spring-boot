package gr.eurobank.jbpmspringinteraction.jbpm.controllers;

import gr.eurobank.jbpmspringinteraction.jbpm.exceptions.JbpmProcessException;
import gr.eurobank.jbpmspringinteraction.jbpm.services.JbpmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

@Slf4j
@RestController
@RequestMapping("jbpm")
public class JbpmController {

    private JbpmService jbpmService;

    @Autowired
    public JbpmController(JbpmService jbpmService) {
        this.jbpmService = jbpmService;
    }

    @GetMapping(value = "/process/{id}", produces = MediaType.TEXT_HTML)
    public ResponseEntity<String> startJbpmProcess(@PathVariable("id") String processId) {
        log.info("JbpmController :: startJbpmProcess :: Method Entered with Process Id {}", processId);

        try {
            this.jbpmService.startProcess(processId);
        } catch (Exception ex) {
            throw new JbpmProcessException(processId);
        }

        String html = String.format("<h1>Process %s started successfully.</h1>", processId);

        log.info("JbpmController :: startJbpmProcess :: Method Returned");
        return ResponseEntity.ok(html);
    }

}
