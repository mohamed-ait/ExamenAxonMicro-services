package org.sid.comptesqrseventsourcing.commonApi.commands;

import lombok.Getter;

public class CreateCustomerCommand extends BaseCommand<String>{
    @Getter private String nom;
    @Getter private String adress;
    @Getter private String email;
    @Getter private String telephone;

    public CreateCustomerCommand(String id, String nom, String adress, String email, String telephone) {
        super(id);
        this.nom = nom;
        this.adress = adress;
        this.email = email;
        this.telephone = telephone;
    }
}
