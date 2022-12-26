package org.sid.comptesqrseventsourcing.commonApi.events;

import lombok.Getter;

public class CustomerDeletedEvent extends BaseEvent<String>{

    public CustomerDeletedEvent(String customerId) {
        super(customerId);
    }

}
