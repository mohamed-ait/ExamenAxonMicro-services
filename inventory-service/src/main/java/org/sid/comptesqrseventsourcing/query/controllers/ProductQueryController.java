package org.sid.comptesqrseventsourcing.query.controllers;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.sid.comptesqrseventsourcing.commonApi.queries.GetAllProductsQuery;
import org.sid.comptesqrseventsourcing.commonApi.queries.GetProductByIdQuery;
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
@RequestMapping("/query/product")
public class ProductQueryController {
    private QueryGateway queryGateway;
    @GetMapping("/allProducts")
            public List<Product> productsList(){
        List<Product> response=queryGateway.query(new GetAllProductsQuery(), ResponseTypes.multipleInstancesOf(Product.class)).join();
        return response;
    }
    @GetMapping(path = "/products/{id}")
    public Product getCustomer(@PathVariable String id){
        Product response=queryGateway.query(new GetProductByIdQuery(id), ResponseTypes.instanceOf(Product.class)).join();
        return response;
    }

}
