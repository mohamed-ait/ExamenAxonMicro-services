package org.sid.comptesqrseventsourcing.query.controllers;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.sid.comptesqrseventsourcing.commonApi.queries.GetAllCustomersQuery;
import org.sid.comptesqrseventsourcing.commonApi.queries.GetCustomerByIdQuery;
import org.sid.comptesqrseventsourcing.query.entities.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Slf4j
@Transactional
@RequestMapping("/query/customer")
public class AccountQueryController {
    private QueryGateway queryGateway;
    @GetMapping("/allCustomers")
            public List<Customer> customerList(){
        List<Customer> response=queryGateway.query(new GetAllCustomersQuery(), ResponseTypes.multipleInstancesOf(Customer.class)).join();
        return response;
    }
    @GetMapping(path = "/customers/{id}")
    public Customer getCustomer(@PathVariable String id){
        Customer response=queryGateway.query(new GetCustomerByIdQuery(id), ResponseTypes.instanceOf(Customer.class)).join();
        return response;
    }

}
