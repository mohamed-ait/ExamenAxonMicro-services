package org.sid.comptesqrseventsourcing.commonApi.commands;

import lombok.Getter;

public class CreateCategorieCommand extends BaseCommand<String> {
   @Getter
   private String nom;
   @Getter private String description;

    public CreateCategorieCommand(String id, String nom, String description) {
        super(id);
        this.nom = nom;
        this.description = description;
    }
}
