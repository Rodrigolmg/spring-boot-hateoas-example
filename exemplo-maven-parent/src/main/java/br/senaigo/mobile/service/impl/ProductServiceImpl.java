package br.senaigo.mobile.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.senaigo.mobile.entities.Product;
import br.senaigo.mobile.repositories.ProductRepository;
import br.senaigo.mobile.service.ProductService;

public class ProductServiceImpl implements ProductService {

Logger productServiceLog = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductRepository productRepository;

	@Override
    @Transactional
    public Product post(Product product) {
        try {
        	
        	productServiceLog.debug("\tPOST product service: post method invocated;");
        	productServiceLog.debug("\tPOST product service: post method executed;");
        	productServiceLog.debug(String.format("\tProduct value: %s;", product.toString()));
        	
        	productRepository.save(product);
            return product;
		} catch (Exception e) {
			// TODO: handle exception
			productServiceLog.error(String.format("POST product service: post method error. Method: %s", e.getMessage()));
		}
        return null;
    }

    @Override
    public Product get(Product product) {
    	try {
    		
    		productServiceLog.debug("\tGET product service: get (single product) method invocated;");
        	productServiceLog.debug("\tGET product service: get (single product) method executed;");
        	productServiceLog.debug(String.format("\tProduct value: %s;", product.toString()));
        	
    		return productRepository.getOne(product.getIdProduct());
		} catch (Exception e) {
			// TODO: handle exception
			productServiceLog.error(String.format("GET product service: get (single product) method error. Method: %s", e.getMessage()));
		}
    	return null;
    }

    @Override
    @Transactional
    public void put(Product product) {
    	try {
    		
    		productServiceLog.debug("\tPUT product service: put (single product) method invocated;");
        	productServiceLog.debug("\tPUT product service: put (single product) method executed;");
        	productServiceLog.debug(String.format("\tProduct value received: %s;", product.toString()));
        	
        	productRepository.save(product);
        	
        	
			
		} catch (Exception e) {
			// TODO: handle exception
			productServiceLog.error(String.format("PUT product service: put (single product) method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    @Transactional
    public void delete(Product product) {
    	try {
    		
    		productServiceLog.debug("\tDELETE product service: delete (single product) method invocated;");
        	productServiceLog.debug("\tDELETE product service: delete (single product) method executed;");
        	productServiceLog.debug(String.format("\tProduct value received: %s;", product.toString()));
        	
        	productRepository.delete(product);
        	
        	
			
		} catch (Exception e) {
			// TODO: handle exception
			productServiceLog.error(String.format("DELETE product service: delete (single product) method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    @Transactional
    public void patch(Product product) {
    	try {
    		
    		productServiceLog.debug("\tPATCH product service: patch (single product) method invocated;");
        	productServiceLog.debug("\tPATCH product service: patch (single product) method executed;");
        	productServiceLog.debug(String.format("\tProduct value received: %s;", product.toString()));
        	
        	productRepository.save(product);
        	
        	
			
		} catch (Exception e) {
			// TODO: handle exception
			productServiceLog.error(String.format("PATCH product service: patch (single product) method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    @Transactional
    public List<Product> post(List<Product> products) {
    	try {
			List<Product> productList = new ArrayList<Product>();
			
			productServiceLog.debug("\tPOST product service: post List method invocated;");
        	productServiceLog.debug("\tPOST product service: post List method executed;");
        	productServiceLog.debug(String.format("\tProduct List value received: %s;", products.toString()));
			
			productList =  productRepository.saveAll(products);
			return productList;
		} catch (Exception e) {
			productServiceLog.error(String.format("POST product service: post List method error. Method: %s", e.getMessage()));
		}
    	return null;
    }

    @Override
    @Transactional
    public void put(List<Product> products) {
    	try {
			
			productServiceLog.debug("\tPUT product service: put List method invocated;");
        	productServiceLog.debug("\tPUT product service: put List method executed;");
        	productServiceLog.debug(String.format("\tProduct list value received: %s;", products.toString()));
			
			productRepository.saveAll(products);
		} catch (Exception e) {
			productServiceLog.error(String.format("PUT product service: put List method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    @Transactional
    public void delete(List<Product> products) {
    	try {
			
			productServiceLog.debug("\tDELETE product service: delete List method invocated;");
        	productServiceLog.debug("\tDELETE product service: delete List method executed;");
        	productServiceLog.debug(String.format("\tProduct list value received: %s;", products.toString()));
			
			productRepository.deleteAll(products);
		} catch (Exception e) {
			productServiceLog.error(String.format("DELETE product service: delete List method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    @Transactional
    public void patch(List<Product> products) {
    	try {
			
			productServiceLog.debug("\tPATCH product service: patch List method invocated;");
        	productServiceLog.debug("\tPATCH product service: patch List method executed;");
        	productServiceLog.debug(String.format("\tProduct list value received: %s;", products.toString()));
			
			productRepository.saveAll(products);
		} catch (Exception e) {
			productServiceLog.error(String.format("PATCH product service: patch List method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    public List<Product> get() {
    	try {
    		
    		productServiceLog.debug("\tGET product service: get list method invocated;");
        	productServiceLog.debug("\tGET product service: get list method executed;");
        	
        	List<Product> productList = productRepository.findAll();
        	
        	productServiceLog.debug(String.format("\tProduct list value: %s;", productList.toString()));
        	
    		return productList;
		} catch (Exception e) {
			// TODO: handle exception
			productServiceLog.error(String.format("GET product service: get list method error. Method: %s", e.getMessage()));
		}
    	return null;
    }
}
