package org.sid.comptesqrseventsourcing.query.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.sid.comptesqrseventsourcing.commonApi.events.CustomerCreatedEvent;
import org.sid.comptesqrseventsourcing.commonApi.events.CustomerUpdatedEvent;
import org.sid.comptesqrseventsourcing.commonApi.events.CustomerDeletedEvent;
import org.sid.comptesqrseventsourcing.commonApi.queries.GetAllCustomersQuery;
import org.sid.comptesqrseventsourcing.query.entities.Customer;
import org.sid.comptesqrseventsourcing.query.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class AccountServiceHandler {
    CustomerRepository customerRepository;
    @EventHandler
    public void on(CustomerCreatedEvent event){
        log.info("*************************");
        log.info("CustomerCreatedEvent received");
        Customer customer=new Customer();
        customer.setId(event.getId());
        customer.setNom(event.getNom());
        customer.setAdresse(event.getAdress());
        customer.setTelephone(event.getTelephone());
        customerRepository.save(customer);

    }

    //debit operation
    @EventHandler
    public void on(CustomerDeletedEvent event){
        log.info("*************************");
        log.info("CustomerDeletedEvent received");
        //supprimer le customer
        customerRepository.deleteById(event.getId());
    }
    //credit operation
    @EventHandler
    public void on(CustomerUpdatedEvent event){
        log.info("*************************");
        log.info("CustomerUpdatedEvent received");
        Customer customer= customerRepository.findById(event.getId()).get();
        customer.setEmail(event.getEmail());
        customer.setNom(event.getNom());
        customer.setEmail(event.getEmail());
        customer.setTelephone(event.getTelephone());
        customer.setAdresse(event.getAdress());
        //sauvegarder le customer modifié
        customerRepository.save(customer);
    }
    //retourner la liste des customers
    @QueryHandler
    public List<Customer> on(GetAllCustomersQuery query){
        return customerRepository.findAll();
    }
}
