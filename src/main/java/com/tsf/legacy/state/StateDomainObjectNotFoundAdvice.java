/* Used to render a HTTP 404 */

package com.tsf.legacy.state;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class StateDomainObjectNotFoundAdvice {

    /*
     * @ResponseBody Signals that this advice is rendered straight into the response
     * body
     *
     * @ExceptionHandler Configures the advice to only respond if an
     * EmployeeNotFoundException is thrown
     *
     * @ResponseStatus Says to issue an HttpStatus.NOT_FOUND ( HTTP 404 )
     *
     * The body of the advice generates the content. In this case, it gives the
     * message of the exception.
     */
    @ResponseBody
    @ExceptionHandler(StateDomainObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String StateDomainObjectNotFoundHandler(StateDomainObjectNotFoundException ex) {
        return ex.getMessage();
    }
}
