package br.senaigo.mobile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senaigo.mobile.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
