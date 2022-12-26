package org.sid.comptesqrseventsourcing.commonApi.commands;

import lombok.Getter;
import org.sid.comptesqrseventsourcing.commonApi.enums.ProductStatus;

public class UpdateProductCommand extends BaseCommand<String>{
    @Getter private String nom;
    @Getter private double prix;
    @Getter private double  quantite;
    @Getter private String categorie;

    public UpdateProductCommand(String id, String nom, double prix, double quantite, String categorie) {
        super(id);
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.categorie = categorie;
    }
}
