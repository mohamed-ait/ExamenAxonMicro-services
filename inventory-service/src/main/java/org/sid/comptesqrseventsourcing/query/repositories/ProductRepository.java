package org.sid.comptesqrseventsourcing.query.repositories;

import org.sid.comptesqrseventsourcing.query.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
