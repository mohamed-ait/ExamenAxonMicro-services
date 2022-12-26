package org.sid.comptesqrseventsourcing.commands.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.sid.comptesqrseventsourcing.commonApi.commands.CreateProductCommand;
import org.sid.comptesqrseventsourcing.commonApi.commands.UpdateProductCommand;
import org.sid.comptesqrseventsourcing.commonApi.commands.DeleteProductCommand;
import org.sid.comptesqrseventsourcing.commonApi.enums.ProductStatus;
import org.sid.comptesqrseventsourcing.commonApi.events.ProductCreatedEvent;
import org.sid.comptesqrseventsourcing.commonApi.events.ProductUpdatedEvent;
import org.sid.comptesqrseventsourcing.commonApi.events.ProductDeletedEvent;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String productId;
    private String nom;
    private double prix;
    private double quantite;
    private ProductStatus status;
    public ProductAggregate() {
        //required by AXON
    }
    @CommandHandler //pour écouter le bus
    public ProductAggregate(CreateProductCommand command) {
        AggregateLifecycle.apply(new ProductCreatedEvent(
                command.getId(),
                command.getNom(),
                command.getPrix(),
                command.getQuantite(),
                ProductStatus.DISPONIBLE,
                command.getCategorie()
        ));
    }
    @EventSourcingHandler
    public void on(ProductCreatedEvent event){
        this.productId= event.getId();
        this.nom=event.getNom();
        this.prix=event.getPrix();
        this.quantite=event.getQuantite();
        this.status=ProductStatus.DISPONIBLE;
    }

    @CommandHandler
    public void handle(UpdateProductCommand command){
AggregateLifecycle.apply(new ProductUpdatedEvent(
        command.getId(),
        command.getNom(),
        command.getPrix(),
        command.getQuantite(),
        ProductStatus.DISPONIBLE,
        command.getCategorie()
));
    }

    @CommandHandler
    public void handler(DeleteProductCommand command){
        AggregateLifecycle.apply(new ProductDeletedEvent(
                command.getId(),
                ProductStatus.ABANDON
        ));
    }

}
