package org.sid.comptesqrseventsourcing.commands.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.sid.comptesqrseventsourcing.commonApi.commands.CreateCustomerCommand;
import org.sid.comptesqrseventsourcing.commonApi.commands.UpdateCustomerCommand;
import org.sid.comptesqrseventsourcing.commonApi.commands.DeleteCustomerCommand;
import org.sid.comptesqrseventsourcing.commonApi.events.CustomerCreatedEvent;
import org.sid.comptesqrseventsourcing.commonApi.events.CustomerUpdatedEvent;
import org.sid.comptesqrseventsourcing.commonApi.events.CustomerDeletedEvent;
import org.sid.comptesqrseventsourcing.commonApi.exceptions.AmountNegativeException;
import org.sid.comptesqrseventsourcing.commonApi.exceptions.BalanceNotSufficientException;

@Aggregate
public class CustomerAggregate {
    @AggregateIdentifier
    private String accountId;
    private String nom;
    private String adresse;
    private String email;
    private String telephone;

    public CustomerAggregate() {
        //required by AXON
    }
    @CommandHandler //pour écouter le bus
    public CustomerAggregate(CreateCustomerCommand command) {
        AggregateLifecycle.apply(new CustomerCreatedEvent(
                command.getId(),
                command.getNom(),
                command.getAdress(),
                command.getEmail(),
                command.getTelephone()
        ));
    }
    @EventSourcingHandler
    public void on(CustomerCreatedEvent event){
        this.accountId= event.getId();
        this.nom=event.getNom();
        this.adresse=event.getAdress();
        this.email=event.getEmail();
        this.telephone=event.getTelephone();
    }

    @CommandHandler
    public void handle(UpdateCustomerCommand command){
AggregateLifecycle.apply(new CustomerUpdatedEvent(
        command.getId(),
        command.getNom(),
        command.getAdress(),
        command.getEmail(),
        command.getTelephone()
));
    }

    @CommandHandler
    public void handler(DeleteCustomerCommand command){
        AggregateLifecycle.apply(new CustomerDeletedEvent(
                command.getId()
        ));
    }

}
