package org.sid.comptesqrseventsourcing.commonApi.queries;

import lombok.Getter;

public class GetCustomerByIdQuery {
    @Getter private String customerId;

    public GetCustomerByIdQuery(String customerId) {
        this.customerId = customerId;
    }
}
