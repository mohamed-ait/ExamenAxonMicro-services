package org.sid.comptesqrseventsourcing.commonApi.events;

import lombok.Getter;
import org.sid.comptesqrseventsourcing.commonApi.enums.ProductStatus;

public class ProductCreatedEvent extends BaseEvent<String>{
    @Getter private String nom;
    @Getter private double prix;
    @Getter private double quantite;
    @Getter private ProductStatus status;
    @Getter private String categorie;

    public ProductCreatedEvent(String id, String nom, double prix, double quantite, ProductStatus status, String categorie) {
        super(id);
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.status = status;
        this.categorie = categorie;
    }
}
