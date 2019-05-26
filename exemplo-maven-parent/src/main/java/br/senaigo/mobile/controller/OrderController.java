package br.senaigo.mobile.controller;

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

import br.senaigo.mobile.entities.Order;
import br.senaigo.mobile.entities.People;
import br.senaigo.mobile.interfaces.GenericOperationsController;
import br.senaigo.mobile.service.OrderService;

@RestController("/orders")
public class OrderController implements GenericOperationsController<Order> {
	
	Logger orderLogger = LoggerFactory.getLogger(OrderController.class);
	

    @Autowired
    public OrderService orderService;

    @Override
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaTypes.HAL_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Resource<Order> post(Order order) {
    	
    	try {
	        orderService.post(order);
	        
	        orderLogger.info("POST - order controller: order registered;");
	        Link link = linkTo(Order.class).slash(order.getId()).withSelfRel();
	        Resource<Order> result = new Resource<Order>(order,link);
	        return result;
    	}catch(Exception e) {
    		orderLogger.error(String.format("POST - order controller error: \nMessage: %s", e.getMessage()));
    	}
    	
    	return null;
    }

    @Override
    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void put(Order order) {
        // TODO Auto-generated method stub
    	try {
    		orderService.put(order);
    		orderLogger.info("PUT - order controller: order updated;");
    	}catch(Exception e) {
    		orderLogger.error(String.format("PUT - order controller error. Message: %s ", e.getMessage()));
    	}
    }

    @Override
    @DeleteMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Order order) {
    	
    	try {
			orderService.delete(order);
			orderLogger.info("DELETE - order controller: order deleted;");
		} catch (Exception e) {
			orderLogger.error(String.format("DELETE - order controller error. Message: %s", e.getMessage()));
		}
    }

    @Override
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaTypes.HAL_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Resources<Order> get() {
        // TODO Auto-generated method stub
    	try {
    		List<Order> orders = orderService.get();
        	
        	orderLogger.info(String.format("GET - order controller: order list getted;", orders.toString()));
        	
        	orders.forEach(order ->{
        		Link self = linkTo(Order.class)
        				.slash(order.getIdOrder())
        				.withSelfRel();
        		
        		Link selfPeople = linkTo(People.class)
        				.slash(order.getPeople().getIdPeople())
        				.withSelfRel();
        		
        		order.getPeople().add(selfPeople);
        		order.add(self);
        	});
        	
        	Link link = linkTo(Order.class).withSelfRel();
        	Resources<Order> orderResult = new Resources<>(orders, link);
        	return orderResult;
		} catch (Exception e) {
			// TODO: handle exception
			orderLogger.error(String.format("GET - order controller error (list). Message: %s", e.getMessage()));
		}
    	
        return null;
    }

    @Override
    @GetMapping(value="/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaTypes.HAL_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Resource<Order> get(@PathVariable("id") Long id) {
        // TODO Auto-generated method stub
    	
    	try {
    		Order order = orderService.get(Order.builder().idOrder(id).build());
    		
    		orderLogger.info("GET - order controller: single order getted;");
    		
        	Link self = linkTo(Order.class).slash(order.getIdOrder()).withSelfRel();
        	order.add(self);
        	
        	
        	Resource<Order> orderResult = new Resource<Order>(order, self);
        	
        	return orderResult;
		} catch (Exception e) {
			// TODO: handle exception
			orderLogger.error(String.format("GET - order controller error (single order). Message: %s", e.getMessage()));
		}
        return null;
    }

    @Override
    @PatchMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patch(Order order) {
        // TODO Auto-generated method stub
    	try {
			orderService.patch(order);
			orderLogger.info(String.format("PATCH - order controller: order update - %s", order.toString()));
		} catch (Exception e) {
			// TODO: handle exception
			orderLogger.error(String.format("PATCH - order controller execute error. Message: %s", e.getMessage()));
		}
    }

}
