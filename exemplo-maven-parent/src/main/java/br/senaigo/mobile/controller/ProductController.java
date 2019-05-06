package br.senaigo.mobile.controller;

import br.senaigo.mobile.entities.Product;
import br.senaigo.mobile.interfaces.GenericOperationsController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RestController;

@RestController("/products")
public class ProductController implements GenericOperationsController<Product> {

    @Override
    public Resource<Product> post(Product entity) {
        return null;
    }

    @Override
    public void put(Product product) {

    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public Resources<Product> get() {
        return null;
    }

    @Override
    public Resource<Product> get(Integer id) {
        return null;
    }

    @Override
    public void patch(Product product) {

    }
}
