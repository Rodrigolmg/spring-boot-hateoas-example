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

import br.senaigo.mobile.entities.Order;
import br.senaigo.mobile.entities.People;
import br.senaigo.mobile.interfaces.GenericOperationsController;
import br.senaigo.mobile.service.PeopleService;

public class PeopleController implements GenericOperationsController<People> {
	
	Logger peopleLogger = LoggerFactory.getLogger(PeopleController.class);
	

    @Autowired
    public PeopleService peopleService;

    @Override
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaTypes.HAL_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Resource<People> post(People people) {
    	
    	try {
	        peopleService.post(people);
	        
	        peopleLogger.info("POST - people controller: people registered;");
	        Link link = linkTo(People.class).slash(people.getId()).withSelfRel();
	        Resource<People> result = new Resource<People>(people,link);
	        return result;
    	}catch(Exception e) {
    		peopleLogger.error(String.format("POST - people controller error: \nMessage: %s", e.getMessage()));
    	}
    	
    	return null;
    }

    @Override
    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void put(People people) {
        // TODO Auto-generated method stub
    	try {
    		peopleService.put(people);
    		peopleLogger.info("PUT - people controller: people updated;");
    	}catch(Exception e) {
    		peopleLogger.error(String.format("PUT - people controller error. Message: %s ", e.getMessage()));
    	}
    }

    @Override
    @DeleteMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(People people) {
    	
    	try {
			peopleService.delete(people);
			peopleLogger.info("DELETE - people controller: people deleted;");
		} catch (Exception e) {
			peopleLogger.error(String.format("DELETE - people controller error. Message: %s", e.getMessage()));
		}
    }

    @Override
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaTypes.HAL_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Resources<People> get() {
        // TODO Auto-generated method stub
    	try {
    		List<People> peoples = peopleService.get();
        	
        	peopleLogger.info(String.format("GET - people controller: people list getted;", peoples.toString()));
        	
        	peoples.forEach(people ->{
        		Link self = linkTo(People.class)
        				.slash(people.getIdPeople())
        				.withSelfRel();
        		
        		people.getOrders().forEach(order -> {
        			Link selfOrders = linkTo(Order.class)
        					.slash(order.getIdOrder())
        					.withSelfRel();
        			
        			people.getOrders()
        		});
        		
        		people.add(self);
        	});
        	
        	Link link = linkTo(People.class).withSelfRel();
        	Resources<People> peopleResult = new Resources<>(peoples, link);
        	return peopleResult;
		} catch (Exception e) {
			// TODO: handle exception
			peopleLogger.error(String.format("GET - people controller error (list). Message: %s", e.getMessage()));
		}
    	
        return null;
    }

    @Override
    @GetMapping(value="/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaTypes.HAL_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Resource<People> get(@PathVariable("id") Long id) {
        // TODO Auto-generated method stub
    	
    	try {
    		People people = peopleService.get(People.builder().idPeople(id).build());
    		
    		peopleLogger.info("GET - people controller: single people getted;");
    		
        	Link self = linkTo(People.class).slash(people.getIdPeople()).withSelfRel();
        	people.add(self);
        	
        	
        	Resource<People> peopleResult = new Resource<People>(people, self);
        	
        	return peopleResult;
		} catch (Exception e) {
			// TODO: handle exception
			peopleLogger.error(String.format("GET - people controller error (single people). Message: %s", e.getMessage()));
		}
        return null;
    }

    @Override
    @PatchMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patch(People people) {
        // TODO Auto-generated method stub
    	try {
			peopleService.patch(people);
			peopleLogger.info(String.format("PATCH - people controller: people update - %s", people.toString()));
		} catch (Exception e) {
			// TODO: handle exception
			peopleLogger.error(String.format("PATCH - people controller execute error. Message: %s", e.getMessage()));
		}
    }
}
