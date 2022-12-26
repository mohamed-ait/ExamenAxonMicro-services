package org.sid.comptesqrseventsourcing.query.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.comptesqrseventsourcing.commonApi.enums.ProductStatus;

import javax.persistence.*;

@Data @NoArgsConstructor @AllArgsConstructor @Entity
public class Product {
    @Id
    private String id;
    private String nom;
    private double prix;
    private double quantite;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @ManyToOne
    private Categorie categorie;

}
