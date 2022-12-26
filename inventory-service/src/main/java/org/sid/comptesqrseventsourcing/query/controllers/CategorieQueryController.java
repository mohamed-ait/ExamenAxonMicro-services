package org.sid.comptesqrseventsourcing.query.controllers;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.sid.comptesqrseventsourcing.commonApi.queries.GetAllCategoriesQuery;
import org.sid.comptesqrseventsourcing.commonApi.queries.GetAllProductsQuery;
import org.sid.comptesqrseventsourcing.commonApi.queries.GetProductByIdQuery;
import org.sid.comptesqrseventsourcing.query.entities.Categorie;
import org.sid.comptesqrseventsourcing.query.entities.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Slf4j
@Transactional
@RequestMapping("/query/categorie")
public class CategorieQueryController {
    private QueryGateway queryGateway;
    @GetMapping("/allCategories")
            public List<Categorie> categoriesList(){
        List<Categorie> response=queryGateway.query(new GetAllCategoriesQuery(), ResponseTypes.multipleInstancesOf(Categorie.class)).join();
        return response;
    }
    @GetMapping(path = "/categories/{id}")
    public Categorie getCustomer(@PathVariable String id){
        Categorie response=queryGateway.query(new GetProductByIdQuery(id), ResponseTypes.instanceOf(Categorie.class)).join();
        return response;
    }

}
