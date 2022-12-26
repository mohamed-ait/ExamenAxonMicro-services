package org.sid.comptesqrseventsourcing.commands.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.sid.comptesqrseventsourcing.commonApi.commands.*;
import org.sid.comptesqrseventsourcing.commonApi.enums.ProductStatus;
import org.sid.comptesqrseventsourcing.commonApi.events.*;
import org.sid.comptesqrseventsourcing.query.service.CategorieServiceHandler;

@Aggregate
public class CategorieAggregate {
    @AggregateIdentifier
    private String categorieId;
    private String nom;
    private String description;
    public CategorieAggregate() {
        //required by AXON
    }
    @CommandHandler //pour écouter le bus
    public CategorieAggregate(CreateCategorieCommand command) {
        AggregateLifecycle.apply(new CategorieCreatedEvent(
                command.getId(),
                command.getNom(),
                command.getDescription()
        ));
    }
    @EventSourcingHandler
    public void on(CategorieCreatedEvent event){
        this.categorieId= event.getId();
        this.nom=event.getNom();
        this.description=event.getDescription();
    }

    @CommandHandler
    public void handle(UpdateCategorieCommand command){
AggregateLifecycle.apply(new CategorieUpdatedEvent(
        command.getId(),
        command.getNom(),
        command.getDescription()));
    }

    @CommandHandler
    public void handler(DeleteCategorieCommand command){
        AggregateLifecycle.apply(new CategorieDeletedEvent(
                command.getId()
        ));
    }

}
