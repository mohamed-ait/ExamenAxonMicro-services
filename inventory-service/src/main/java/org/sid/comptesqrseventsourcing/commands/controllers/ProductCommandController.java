package org.sid.comptesqrseventsourcing.commands.controllers;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.sid.comptesqrseventsourcing.commonApi.commands.CreateProductCommand;
import org.sid.comptesqrseventsourcing.commonApi.commands.UpdateProductCommand;
import org.sid.comptesqrseventsourcing.commonApi.commands.DeleteProductCommand;
import org.sid.comptesqrseventsourcing.commonApi.dtos.CreateProductRequestDTO;
import org.sid.comptesqrseventsourcing.commonApi.dtos.UpdateProductRequestDTO;
import org.sid.comptesqrseventsourcing.commonApi.dtos.DeleteProductRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@AllArgsConstructor
@RestController
@RequestMapping("/commands/product")
public class ProductCommandController {
    EventStore eventStore;
    private CommandGateway commandGateway;
    @PostMapping("/create")
    public CompletableFuture<String> createProduct(@RequestBody CreateProductRequestDTO request){
        CompletableFuture<String> commandResponse = commandGateway.send(new CreateProductCommand(
        UUID.randomUUID().toString(),
        request.getNom(),
                request.getPrix(),
                request.getQuantite(),
                request.getCategorie()));
        return commandResponse;
    }
    @PutMapping("/update")
    public CompletableFuture<String> updateCustomer(@RequestBody UpdateProductRequestDTO request){
        CompletableFuture<String> commandResponse = commandGateway.send(new UpdateProductCommand(
                request.getId(),
                request.getNom(),
                request.getPrix(),
                request.getQuantite(),
                request.getCategorie()));
        return commandResponse;
    }
    @PutMapping("/delete")
    public CompletableFuture<String> deleteCustomer(@RequestBody DeleteProductRequestDTO request){
        CompletableFuture<String> commandResponse = commandGateway.send(new DeleteProductCommand(
                request.getCustomerId()));
        return commandResponse;
    }
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception exception){
     ResponseEntity<String> entity= new ResponseEntity<>(
            exception.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR
    );
    return entity.toString();
    }
    @GetMapping("/eventStore/{productId}")
    public Stream eventStore(@PathVariable String productId){
     return eventStore.readEvents(productId).asStream();
    }
}
