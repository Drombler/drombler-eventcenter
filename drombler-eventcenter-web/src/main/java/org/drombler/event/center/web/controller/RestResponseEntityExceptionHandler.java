package org.drombler.event.center.web.controller;

/**
 *
 * @author Florian
 */

import java.util.EnumMap;
import java.util.Map;
import org.drombler.event.center.model.EventCenterException;
import org.drombler.event.core.protocol.json.ErrorResponse;
import org.drombler.event.core.protocol.json.EventCenterErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Map<EventCenterErrorCode, HttpStatus> CODE_STATUS_MAP = new EnumMap<>(EventCenterErrorCode.class);

    static {
        CODE_STATUS_MAP.put(EventCenterErrorCode.EVENTCENTER_ILLEGAL_PROPERTY, HttpStatus.BAD_REQUEST);
        CODE_STATUS_MAP.put(EventCenterErrorCode.EVENTCENTER_UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EventCenterException.class)
    public ResponseEntity<ErrorResponse> handleEventCenterException(EventCenterException ex) {
        ErrorResponse errorResponse = createErrorResponse(ex.getErrorCode(), ex.getMessage());
        HttpStatus httpStatus = determineHttpStatus(ex.getErrorCode());
        return handleErrorResponse(errorResponse, httpStatus);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        EventCenterErrorCode errorCode = EventCenterErrorCode.EVENTCENTER_ILLEGAL_PROPERTY;
        ErrorResponse errorResponse = createErrorResponse(errorCode, ex.getMessage());
        HttpStatus httpStatus = determineHttpStatus(errorCode);
        return handleErrorResponse(errorResponse, httpStatus);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        EventCenterErrorCode errorCode = EventCenterErrorCode.EVENTCENTER_UNKNOWN;
        ErrorResponse errorResponse = createErrorResponse(errorCode, ex.getMessage());
        HttpStatus httpStatus = determineHttpStatus(errorCode);
        return handleErrorResponse(errorResponse, httpStatus);
    }

    private ResponseEntity<ErrorResponse> handleErrorResponse(ErrorResponse errorResponse, HttpStatus httpStatus) {
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
