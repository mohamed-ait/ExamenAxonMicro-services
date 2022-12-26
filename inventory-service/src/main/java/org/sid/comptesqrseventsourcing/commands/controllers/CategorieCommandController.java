package org.sid.comptesqrseventsourcing.commands.controllers;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.sid.comptesqrseventsourcing.commonApi.commands.*;
import org.sid.comptesqrseventsourcing.commonApi.dtos.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@AllArgsConstructor
@RestController
@RequestMapping("/commands/categorie")
public class CategorieCommandController {
    EventStore eventStore;
    private CommandGateway commandGateway;
    @PostMapping("/create")
    public CompletableFuture<String> createProduct(@RequestBody CreateCategorieRequestDTO request){
        CompletableFuture<String> commandResponse = commandGateway.send(new CreateCategorieCommand(
        UUID.randomUUID().toString(),
        request.getNom(),
                request.getDescription()));
        return commandResponse;
    }
    @PutMapping("/update")
    public CompletableFuture<String> updateCustomer(@RequestBody UpdateCategorieRequestDTO request){
        CompletableFuture<String> commandResponse = commandGateway.send(new UpdateCategorieCommand(
                request.getId(),
                request.getNom(),
                request.getDescription()));
        return commandResponse;
    }
    @PutMapping("/delete")
    public CompletableFuture<String> deleteCustomer(@RequestBody DeleteCategorieRequestDTO request){
        CompletableFuture<String> commandResponse = commandGateway.send(new DeleteCategorieCommand(
                request.getCategoriId()));
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
    @GetMapping("/eventStore/{categorieId}")
    public Stream eventStore(@PathVariable String categorieId){
     return eventStore.readEvents(categorieId).asStream();
    }
}
