package org.sid.comptesqrseventsourcing.commonApi.events;

import lombok.Getter;
import org.sid.comptesqrseventsourcing.commonApi.enums.ProductStatus;

public class ProductDeletedEvent extends BaseEvent<String>{
@Getter ProductStatus status;

    public ProductDeletedEvent(String id, ProductStatus status) {
        super(id);
        this.status = status;
    }
}
