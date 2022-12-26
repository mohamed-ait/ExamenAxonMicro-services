package org.sid.comptesqrseventsourcing.commonApi.commands;

import lombok.Getter;

public class UpdateCategorieCommand extends BaseCommand<String> {
    @lombok.Getter
    private String nom;
    @Getter
    private String description;

    public UpdateCategorieCommand(String id, String nom, String description) {
        super(id);
        this.nom = nom;
        this.description = description;
    }
}
