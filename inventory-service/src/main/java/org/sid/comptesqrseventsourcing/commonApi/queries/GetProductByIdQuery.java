package org.sid.comptesqrseventsourcing.commonApi.queries;

import lombok.Getter;

public class GetProductByIdQuery {
    @Getter private String productId;

    public GetProductByIdQuery(String productId) {
        this.productId = productId;
    }
}
