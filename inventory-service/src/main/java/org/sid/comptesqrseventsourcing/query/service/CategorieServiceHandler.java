package org.sid.comptesqrseventsourcing.query.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.sid.comptesqrseventsourcing.commonApi.events.*;
import org.sid.comptesqrseventsourcing.commonApi.queries.GetAllCategoriesQuery;
import org.sid.comptesqrseventsourcing.commonApi.queries.GetAllProductsQuery;
import org.sid.comptesqrseventsourcing.commonApi.queries.GetCategorieByIdQuery;
import org.sid.comptesqrseventsourcing.commonApi.queries.GetProductByIdQuery;
import org.sid.comptesqrseventsourcing.query.entities.Categorie;
import org.sid.comptesqrseventsourcing.query.entities.Product;
import org.sid.comptesqrseventsourcing.query.repositories.CategorieRepository;
import org.sid.comptesqrseventsourcing.query.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class CategorieServiceHandler {
    CategorieRepository categorieRepository;
    @EventHandler
    public void on(CategorieCreatedEvent event){
        log.info("*************************");
        log.info("CategorieCreatedEvent received");
        Categorie categorie=new Categorie();
        categorie.setId(event.getId());
        categorie.setNom(event.getNom());
        categorie.setDescription(event.getDescription());
        categorieRepository.save(categorie);

    }

    //debit operation
    @EventHandler
    public void on(CategorieDeletedEvent event){
        log.info("*************************");
        log.info("CategorieDeletedEvent received");
        //supprimer la catégorie
        categorieRepository.deleteById(event.getId());
    }
    //credit operation
    @EventHandler
    public void on(CategorieUpdatedEvent event){
        log.info("*************************");
        log.info("CategorieUpdatedEvent received");
        Categorie categorie= categorieRepository.findById(event.getId()).get();
        categorie.setNom(event.getNom());
        categorie.setNom(event.getNom());
        categorie.setDescription(event.getDescription());
        //sauvegarder le customer modifié
        categorieRepository.save(categorie);
    }
    //retourner la liste des categories
    @QueryHandler
    public List<Categorie> on(GetAllCategoriesQuery query){
        return categorieRepository.findAll();
    }
    //retourner une categorie par son id  quand l'événement GetCategorieByIdQuery est declénché
    @QueryHandler
    public Categorie on(GetCategorieByIdQuery query){
        return categorieRepository.findById(query.getCategorieId()).get();
    }

}
