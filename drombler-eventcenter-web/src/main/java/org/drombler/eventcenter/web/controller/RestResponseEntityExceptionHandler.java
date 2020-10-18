package org.drombler.eventcenter.web.controller;

/**
 *
 * @author Florian
 */

import lombok.extern.slf4j.Slf4j;
import org.drombler.event.core.protocol.json.ErrorResponse;
import org.drombler.event.core.protocol.json.EventCenterErrorCode;
import org.drombler.eventcenter.model.EventCenterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.EnumMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Map<EventCenterErrorCode, HttpStatus> CODE_STATUS_MAP = new EnumMap<>(EventCenterErrorCode.class);

    static {
        CODE_STATUS_MAP.put(EventCenterErrorCode.EVENTCENTER_ILLEGAL_PROPERTY, HttpStatus.BAD_REQUEST);
        CODE_STATUS_MAP.put(EventCenterErrorCode.EVENTCENTER_UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EventCenterException.class)
    public ResponseEntity<ErrorResponse> handleEventCenterException(EventCenterException ex) {
        return handleException(ex, ex.getErrorCode());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return handleException(ex, EventCenterErrorCode.EVENTCENTER_ILLEGAL_PROPERTY);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        return handleException(ex, EventCenterErrorCode.EVENTCENTER_UNKNOWN);
    }

    private ResponseEntity<ErrorResponse> handleException(Exception ex, EventCenterErrorCode errorCode) {
        log.error(ex.getMessage(), ex);
        ErrorResponse errorResponse = createErrorResponse(errorCode, ex.getMessage());
        HttpStatus httpStatus = determineHttpStatus(errorCode);
        return createResponseEntity(errorResponse, httpStatus);
    }

    private ResponseEntity<ErrorResponse> createResponseEntity(ErrorResponse errorResponse, HttpStatus httpStatus) {
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    private ErrorResponse createErrorResponse(EventCenterErrorCode errorCode, String message) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(errorCode);
        errorResponse.setErrorMessage(message);
        return errorResponse;
    }

    private HttpStatus determineHttpStatus(EventCenterErrorCode errorCode) {
        return CODE_STATUS_MAP.getOrDefault(errorCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
