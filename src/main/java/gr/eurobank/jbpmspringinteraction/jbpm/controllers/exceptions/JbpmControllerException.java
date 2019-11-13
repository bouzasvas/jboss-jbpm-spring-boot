package gr.eurobank.jbpmspringinteraction.jbpm.controllers.exceptions;

import gr.eurobank.jbpmspringinteraction.jbpm.exceptions.JbpmProcessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class JbpmControllerException {

    @ResponseBody
    @ExceptionHandler(JbpmProcessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(JbpmProcessException ex) {
        return ex.getMessage();
    }
}
