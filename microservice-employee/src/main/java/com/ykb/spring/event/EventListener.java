package com.ykb.spring.event;

import org.springframework.stereotype.Component;

@Component
public class EventListener {

    @org.springframework.context.event.EventListener(MyRemoteEvent.class)
    public void listen(final MyRemoteEvent me) {
        System.out.println(me.getMessage());
    }

}
