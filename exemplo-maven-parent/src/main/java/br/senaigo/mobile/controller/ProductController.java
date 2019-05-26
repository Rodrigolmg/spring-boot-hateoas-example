package br.senaigo.mobile.controller;

import br.senaigo.mobile.entities.Product;
import br.senaigo.mobile.interfaces.GenericOperationsController;
import br.senaigo.mobile.service.ProductService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("/products")
public class ProductController implements GenericOperationsController<Product> {

	Logger productLogger = LoggerFactory.getLogger(ProductController.class);
	

    @Autowired
    public ProductService productService;

    @Override
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaTypes.HAL_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Resource<Product> post(Product product) {
    	
    	try {
	        productService.post(product);
	        
	        productLogger.info("POST - product controller: product registered;");
	        Link link = linkTo(Product.class).slash(product.getId()).withSelfRel();
	        Resource<Product> result = new Resource<Product>(product,link);
	        return result;
    	}catch(Exception e) {
    		productLogger.error(String.format("POST - product controller error: \nMessage: %s", e.getMessage()));
    	}
    	
    	return null;
    }

    @Override
    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void put(Product product) {
        // TODO Auto-generated method stub
    	try {
    		productService.put(product);
    		productLogger.info("PUT - product controller: product updated;");
    	}catch(Exception e) {
    		productLogger.error(String.format("PUT - product controller error. Message: %s ", e.getMessage()));
    	}
    }

    @Override
    @DeleteMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Product product) {
    	
    	try {
			productService.delete(product);
			productLogger.info("DELETE - product controller: product deleted;");
		} catch (Exception e) {
			productLogger.error(String.format("DELETE - product controller error. Message: %s", e.getMessage()));
		}
    }

    @Override
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaTypes.HAL_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Resources<Product> get() {
        // TODO Auto-generated method stub
    	try {
    		List<Product> products = productService.get();
        	
        	productLogger.info(String.format("GET - product controller: product list getted;", products.toString()));
        	
        	products.forEach(product ->{
        		Link self = linkTo(Product.class)
        				.slash(product.getIdProduct())
        				.withSelfRel();
        		
        		product.add(self);
        	});
        	
        	
        	Link link = linkTo(Product.class).withSelfRel();
        	Resources<Product> productResult = new Resources<>(products, link);
        	return productResult;
		} catch (Exception e) {
			// TODO: handle exception
			productLogger.error(String.format("GET - product controller error (list). Message: %s", e.getMessage()));
		}
    	
        return null;
    }

    @Override
    @GetMapping(value="/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaTypes.HAL_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Resource<Product> get(@PathVariable("id") Long id) {
        // TODO Auto-generated method stub
    	
    	try {
    		Product product = productService.get(Product.builder().idProduct(id).build());
    		
    		productLogger.info("GET - product controller: single product getted;");
    		
        	Link self = linkTo(Product.class).slash(product.getIdProduct()).withSelfRel();
        	product.add(self);
        	
        	
        	Resource<Product> productResult = new Resource<Product>(product, self);
        	
        	return productResult;
		} catch (Exception e) {
			// TODO: handle exception
			productLogger.error(String.format("GET - product controller error (single product). Message: %s", e.getMessage()));
		}
        return null;
    }

    @Override
    @PatchMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patch(Product product) {
        // TODO Auto-generated method stub
    	try {
			productService.patch(product);
			productLogger.info(String.format("PATCH - product controller: product update - %s", product.toString()));
		} catch (Exception e) {
			// TODO: handle exception
			productLogger.error(String.format("PATCH - product controller execute error. Message: %s", e.getMessage()));
		}
    }
}
