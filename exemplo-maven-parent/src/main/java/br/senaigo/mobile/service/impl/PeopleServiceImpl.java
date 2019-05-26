package br.senaigo.mobile.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.senaigo.mobile.entities.People;
import br.senaigo.mobile.entities.People;
import br.senaigo.mobile.repositories.PeopleRepository;
import br.senaigo.mobile.service.PeopleService;

public class PeopleServiceImpl implements PeopleService {
	
	Logger peopleServiceLog = LoggerFactory.getLogger(PeopleServiceImpl.class);
	
	@Autowired
	PeopleRepository peopleRepository;

	@Override
    @Transactional
    public People post(People people) {
        try {
        	
        	peopleServiceLog.debug("\tPOST people service: post method invocated;");
        	peopleServiceLog.debug("\tPOST people service: post method executed;");
        	peopleServiceLog.debug(String.format("\tPeople value: %s;", people.toString()));
        	
        	peopleRepository.save(people);
            return people;
		} catch (Exception e) {
			// TODO: handle exception
			peopleServiceLog.error(String.format("POST people service: post method error. Method: %s", e.getMessage()));
		}
        return null;
    }

    @Override
    public People get(People people) {
    	try {
    		
    		peopleServiceLog.debug("\tGET people service: get (single people) method invocated;");
        	peopleServiceLog.debug("\tGET people service: get (single people) method executed;");
        	peopleServiceLog.debug(String.format("\tPeople value: %s;", people.toString()));
        	
    		return peopleRepository.getOne(people.getIdPeople());
		} catch (Exception e) {
			// TODO: handle exception
			peopleServiceLog.error(String.format("GET people service: get (single people) method error. Method: %s", e.getMessage()));
		}
    	return null;
    }

    @Override
    @Transactional
    public void put(People people) {
    	try {
    		
    		peopleServiceLog.debug("\tPUT people service: put (single people) method invocated;");
        	peopleServiceLog.debug("\tPUT people service: put (single people) method executed;");
        	peopleServiceLog.debug(String.format("\tPeople value received: %s;", people.toString()));
        	
        	peopleRepository.save(people);
        	
        	
			
		} catch (Exception e) {
			// TODO: handle exception
			peopleServiceLog.error(String.format("PUT people service: put (single people) method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    @Transactional
    public void delete(People people) {
    	try {
    		
    		peopleServiceLog.debug("\tDELETE people service: delete (single people) method invocated;");
        	peopleServiceLog.debug("\tDELETE people service: delete (single people) method executed;");
        	peopleServiceLog.debug(String.format("\tPeople value received: %s;", people.toString()));
        	
        	peopleRepository.delete(people);
        	
        	
			
		} catch (Exception e) {
			// TODO: handle exception
			peopleServiceLog.error(String.format("DELETE people service: delete (single people) method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    @Transactional
    public void patch(People people) {
    	try {
    		
    		peopleServiceLog.debug("\tPATCH people service: patch (single people) method invocated;");
        	peopleServiceLog.debug("\tPATCH people service: patch (single people) method executed;");
        	peopleServiceLog.debug(String.format("\tPeople value received: %s;", people.toString()));
        	
        	peopleRepository.save(people);
        	
        	
			
		} catch (Exception e) {
			// TODO: handle exception
			peopleServiceLog.error(String.format("PATCH people service: patch (single people) method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    @Transactional
    public List<People> post(List<People> peoples) {
    	try {
			List<People> peopleList = new ArrayList<People>();
			
			peopleServiceLog.debug("\tPOST people service: post List method invocated;");
        	peopleServiceLog.debug("\tPOST people service: post List method executed;");
        	peopleServiceLog.debug(String.format("\tPeople List value received: %s;", peoples.toString()));
			
			peopleList =  peopleRepository.saveAll(peoples);
			return peopleList;
		} catch (Exception e) {
			peopleServiceLog.error(String.format("POST people service: post List method error. Method: %s", e.getMessage()));
		}
    	return null;
    }

    @Override
    @Transactional
    public void put(List<People> peoples) {
    	try {
			
			peopleServiceLog.debug("\tPUT people service: put List method invocated;");
        	peopleServiceLog.debug("\tPUT people service: put List method executed;");
        	peopleServiceLog.debug(String.format("\tPeople list value received: %s;", peoples.toString()));
			
			peopleRepository.saveAll(peoples);
		} catch (Exception e) {
			peopleServiceLog.error(String.format("PUT people service: put List method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    @Transactional
    public void delete(List<People> peoples) {
    	try {
			
			peopleServiceLog.debug("\tDELETE people service: delete List method invocated;");
        	peopleServiceLog.debug("\tDELETE people service: delete List method executed;");
        	peopleServiceLog.debug(String.format("\tPeople list value received: %s;", peoples.toString()));
			
			peopleRepository.deleteAll(peoples);
		} catch (Exception e) {
			peopleServiceLog.error(String.format("DELETE people service: delete List method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    @Transactional
    public void patch(List<People> peoples) {
    	try {
			
			peopleServiceLog.debug("\tPATCH people service: patch List method invocated;");
        	peopleServiceLog.debug("\tPATCH people service: patch List method executed;");
        	peopleServiceLog.debug(String.format("\tPeople list value received: %s;", peoples.toString()));
			
			peopleRepository.saveAll(peoples);
		} catch (Exception e) {
			peopleServiceLog.error(String.format("PATCH people service: patch List method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    public List<People> get() {
    	try {
    		
    		peopleServiceLog.debug("\tGET people service: get list method invocated;");
        	peopleServiceLog.debug("\tGET people service: get list method executed;");
        	
        	List<People> peopleList = peopleRepository.findAll();
        	
        	peopleServiceLog.debug(String.format("\tPeople list value: %s;", peopleList.toString()));
        	
    		return peopleList;
		} catch (Exception e) {
			// TODO: handle exception
			peopleServiceLog.error(String.format("GET people service: get list method error. Method: %s", e.getMessage()));
		}
    	return null;
    }
}
