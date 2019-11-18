package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.jpaEntity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}