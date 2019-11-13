package gr.eurobank.jbpmspringinteraction.jbpm.exceptions;

public class JbpmProcessException extends RuntimeException {

    public JbpmProcessException(String processId) {
        super(String.format("Cannot find Process with Process Id %s", processId));
    }
}
