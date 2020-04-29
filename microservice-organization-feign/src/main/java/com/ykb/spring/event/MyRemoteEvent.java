package com.ykb.spring.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

public class MyRemoteEvent extends RemoteApplicationEvent {

    private static final long serialVersionUID = -8959778515132594399L;

    private String            message;


    public MyRemoteEvent() {
    }

    public MyRemoteEvent(final Object source,
                         final String id,
                         final String message) {
        super(source,
              id);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String messageParam) {
        message = messageParam;
    }

}
