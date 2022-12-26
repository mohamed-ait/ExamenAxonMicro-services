package org.sid.comptesqrseventsourcing.commonApi.events;

public class CategorieDeletedEvent extends BaseEvent<String>{

    public CategorieDeletedEvent(String categorieId) {
        super(categorieId);
    }

}
