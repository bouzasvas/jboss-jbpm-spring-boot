package gr.eurobank.jbpmspringinteraction.jbpm.exceptions;

public class JpbmKieServerConnectionException extends RuntimeException {

    public JpbmKieServerConnectionException(String url) {
        super(String.format("Connection to %s failed. Maybe the host is down?", url));
    }
}
