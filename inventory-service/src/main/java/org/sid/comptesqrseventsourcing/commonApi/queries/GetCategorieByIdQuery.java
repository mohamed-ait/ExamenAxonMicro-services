package org.sid.comptesqrseventsourcing.commonApi.queries;

import lombok.Getter;

public class GetCategorieByIdQuery {

    @Getter private String categorieId;

    public GetCategorieByIdQuery(String categorieId) {
        this.categorieId = categorieId;
    }
}
