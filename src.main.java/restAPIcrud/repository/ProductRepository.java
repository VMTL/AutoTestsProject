package restAPIcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restAPIcrud.jpaEntity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}