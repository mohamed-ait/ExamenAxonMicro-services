package org.sid.comptesqrseventsourcing.query.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
@Data @NoArgsConstructor @AllArgsConstructor @Entity
public class Customer {
    @Id
    private String id;
    private String nom;
    private String adresse;
    private String email;
    private String telephone;

}
