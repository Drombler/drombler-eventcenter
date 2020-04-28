package org.drombler.eventcenter.model;

import org.drombler.event.core.protocol.json.EventCenterErrorCode;
import org.softsmithy.lib.util.BusinessException;

/**
 *
 * @author Florian
 */
public class EventCenterException extends BusinessException{
   private static final long serialVersionUID = 2423918971447441288L;

    private final EventCenterErrorCode errorCode;

    public EventCenterException(EventCenterErrorCode errorCode, String message) {
        super(formatMessage(errorCode, message));
        this.errorCode = errorCode;
    }

    public EventCenterException(EventCenterErrorCode errorCode, String message, Throwable cause) {
        super(formatMessage(errorCode, message), cause);
        this.errorCode = errorCode;
    }

    private static String formatMessage(EventCenterErrorCode errorCode, String message) {
        return errorCode + ": " + message;
    }

    public EventCenterErrorCode getErrorCode() {
        return errorCode;
    }


}
