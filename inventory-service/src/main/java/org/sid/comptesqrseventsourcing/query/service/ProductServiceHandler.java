package org.sid.comptesqrseventsourcing.query.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.sid.comptesqrseventsourcing.commonApi.events.ProductCreatedEvent;
import org.sid.comptesqrseventsourcing.commonApi.events.ProductDeletedEvent;
import org.sid.comptesqrseventsourcing.commonApi.events.ProductUpdatedEvent;
import org.sid.comptesqrseventsourcing.commonApi.queries.GetAllProductsQuery;
import org.sid.comptesqrseventsourcing.commonApi.queries.GetProductByIdQuery;
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
public class ProductServiceHandler {
    ProductRepository productRepository;
    CategorieRepository categorieRepository;
    @EventHandler
    public void on(ProductCreatedEvent event){
        log.info("*************************");
        log.info("ProductCreatedEvent received");
        Product product=new Product();
        product.setId(event.getId());
        product.setNom(event.getNom());
        product.setPrix(event.getPrix());
        product.setQuantite(event.getQuantite());
        //récupérer la catégorie par son nom et l'atribuer au produit :
        product.setCategorie(categorieRepository.findByName(event.getCategorie()));
        productRepository.save(product);

    }

    //debit operation
    @EventHandler
    public void on(ProductDeletedEvent event){
        log.info("*************************");
        log.info("CustomerDeletedEvent received");
        //supprimer le produit
        productRepository.deleteById(event.getId());
    }
    //credit operation
    @EventHandler
    public void on(ProductUpdatedEvent event){
        log.info("*************************");
        log.info("ProductUpdatedEvent received");
        Product product= productRepository.findById(event.getId()).get();
        product.setNom(event.getNom());
        product.setPrix(event.getPrix());
        product.setQuantite(event.getQuantite());
        product.setStatus(event.getStatus());
        product.setCategorie(categorieRepository.findByName(event.getCategorie()));
        //sauvegarder le customer modifié
        productRepository.save(product);
    }
    //retourner la liste des produits quand l'événement GetAllProductsQuery est declénché
    @QueryHandler
    public List<Product> on(GetAllProductsQuery query){
        return productRepository.findAll();
    }
    //retourner un produit par son id  quand l'événement GetAllProductsQuery est declénché
    @QueryHandler
    public Product on(GetProductByIdQuery query){
        return productRepository.findById(query.getProductId()).get();
    }
}
