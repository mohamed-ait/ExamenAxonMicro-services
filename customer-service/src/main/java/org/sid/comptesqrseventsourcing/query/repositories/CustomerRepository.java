package org.sid.comptesqrseventsourcing.query.repositories;

import org.sid.comptesqrseventsourcing.query.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
