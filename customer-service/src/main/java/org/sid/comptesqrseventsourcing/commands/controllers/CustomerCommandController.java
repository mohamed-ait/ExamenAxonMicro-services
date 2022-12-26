package org.sid.comptesqrseventsourcing.commands.controllers;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.sid.comptesqrseventsourcing.commonApi.commands.CreateCustomerCommand;
import org.sid.comptesqrseventsourcing.commonApi.commands.UpdateCustomerCommand;
import org.sid.comptesqrseventsourcing.commonApi.commands.DeleteCustomerCommand;
import org.sid.comptesqrseventsourcing.commonApi.dtos.CreateCustomerRequestDTO;
import org.sid.comptesqrseventsourcing.commonApi.dtos.UpdateCustomerRequestDTO;
import org.sid.comptesqrseventsourcing.commonApi.dtos.DeleteCustomerRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@AllArgsConstructor
@RestController
@RequestMapping("/commands/customer")
public class CustomerCommandController {
    EventStore eventStore;
    private CommandGateway commandGateway;
    @PostMapping("/create")
    public CompletableFuture<String> createCustomer(@RequestBody CreateCustomerRequestDTO request){
        CompletableFuture<String> commandResponse = commandGateway.send(new CreateCustomerCommand(
        UUID.randomUUID().toString(),
        request.getNom(),
                request.getAdress(),
                request.getEmail(),
                request.getTelephone()));
        return commandResponse;
    }
    @PutMapping("/update")
    public CompletableFuture<String> updateCustomer(@RequestBody UpdateCustomerRequestDTO request){
        CompletableFuture<String> commandResponse = commandGateway.send(new UpdateCustomerCommand(
                request.getCustomerId(),
                request.getNom(),
                request.getAdress(),
                request.getEmail(),
                request.getTelephone()));
        return commandResponse;
    }
    @PutMapping("/delete")
    public CompletableFuture<String> deleteCustomer(@RequestBody DeleteCustomerRequestDTO request){
        CompletableFuture<String> commandResponse = commandGateway.send(new DeleteCustomerCommand(
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
    @GetMapping("/eventStore/{customerId}")
    public Stream eventStore(@PathVariable String customerId){
     return eventStore.readEvents(customerId).asStream();
    }
}
