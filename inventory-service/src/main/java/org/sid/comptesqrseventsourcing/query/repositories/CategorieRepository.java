package org.sid.comptesqrseventsourcing.query.repositories;

import org.sid.comptesqrseventsourcing.query.entities.Categorie;
import org.sid.comptesqrseventsourcing.query.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategorieRepository extends JpaRepository<Categorie,String> {
    @Query("SELECT p FROM Categorie p WHERE p.nom = :name")
    Categorie findByName(@Param("name") String name);
}
