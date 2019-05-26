package br.senaigo.mobile.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senaigo.mobile.entities.Order;
import br.senaigo.mobile.repositories.OrderRepository;
import br.senaigo.mobile.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	Logger orderServiceLog = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    public OrderRepository orderRepository;

    @Override
    @Transactional
    public Order post(Order order) {
        try {
        	
        	orderServiceLog.debug("\tPOST order service: post method invocated;");
        	orderServiceLog.debug("\tPOST order service: post method executed;");
        	orderServiceLog.debug(String.format("\tOrder value: %s;", order.toString()));
        	
        	orderRepository.save(order);
            return order;
		} catch (Exception e) {
			// TODO: handle exception
			orderServiceLog.error(String.format("POST order service: post method error. Method: %s", e.getMessage()));
		}
        return null;
    }

    @Override
    public Order get(Order order) {
    	try {
    		
    		orderServiceLog.debug("\tGET order service: get (single order) method invocated;");
        	orderServiceLog.debug("\tGET order service: get (single order) method executed;");
        	orderServiceLog.debug(String.format("\tOrder value: %s;", order.toString()));
        	
    		return orderRepository.getOne(order.getIdOrder());
		} catch (Exception e) {
			// TODO: handle exception
			orderServiceLog.error(String.format("GET order service: get (single order) method error. Method: %s", e.getMessage()));
		}
    	return null;
    }

    @Override
    @Transactional
    public void put(Order order) {
    	try {
    		
    		orderServiceLog.debug("\tPUT order service: put (single order) method invocated;");
        	orderServiceLog.debug("\tPUT order service: put (single order) method executed;");
        	orderServiceLog.debug(String.format("\tOrder value received: %s;", order.toString()));
        	
        	orderRepository.save(order);
        	
        	
			
		} catch (Exception e) {
			// TODO: handle exception
			orderServiceLog.error(String.format("PUT order service: put (single order) method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    @Transactional
    public void delete(Order order) {
    	try {
    		
    		orderServiceLog.debug("\tDELETE order service: delete (single order) method invocated;");
        	orderServiceLog.debug("\tDELETE order service: delete (single order) method executed;");
        	orderServiceLog.debug(String.format("\tOrder value received: %s;", order.toString()));
        	
        	orderRepository.delete(order);
        	
        	
			
		} catch (Exception e) {
			// TODO: handle exception
			orderServiceLog.error(String.format("DELETE order service: delete (single order) method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    @Transactional
    public void patch(Order order) {
    	try {
    		
    		orderServiceLog.debug("\tPATCH order service: patch (single order) method invocated;");
        	orderServiceLog.debug("\tPATCH order service: patch (single order) method executed;");
        	orderServiceLog.debug(String.format("\tOrder value received: %s;", order.toString()));
        	
        	orderRepository.save(order);
        	
        	
			
		} catch (Exception e) {
			// TODO: handle exception
			orderServiceLog.error(String.format("PATCH order service: patch (single order) method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    @Transactional
    public List<Order> post(List<Order> orders) {
    	try {
			List<Order> orderList = new ArrayList<Order>();
			
			orderServiceLog.debug("\tPOST order service: post List method invocated;");
        	orderServiceLog.debug("\tPOST order service: post List method executed;");
        	orderServiceLog.debug(String.format("\tOrder List value received: %s;", orders.toString()));
			
			orderList =  orderRepository.saveAll(orders);
			return orderList;
		} catch (Exception e) {
			orderServiceLog.error(String.format("POST order service: post List method error. Method: %s", e.getMessage()));
		}
    	return null;
    }

    @Override
    @Transactional
    public void put(List<Order> orders) {
    	try {
			
			orderServiceLog.debug("\tPUT order service: put List method invocated;");
        	orderServiceLog.debug("\tPUT order service: put List method executed;");
        	orderServiceLog.debug(String.format("\tOrder list value received: %s;", orders.toString()));
			
			orderRepository.saveAll(orders);
		} catch (Exception e) {
			orderServiceLog.error(String.format("PUT order service: put List method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    @Transactional
    public void delete(List<Order> orders) {
    	try {
			
			orderServiceLog.debug("\tDELETE order service: delete List method invocated;");
        	orderServiceLog.debug("\tDELETE order service: delete List method executed;");
        	orderServiceLog.debug(String.format("\tOrder list value received: %s;", orders.toString()));
			
			orderRepository.deleteAll(orders);
		} catch (Exception e) {
			orderServiceLog.error(String.format("DELETE order service: delete List method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    @Transactional
    public void patch(List<Order> orders) {
    	try {
			
			orderServiceLog.debug("\tPATCH order service: patch List method invocated;");
        	orderServiceLog.debug("\tPATCH order service: patch List method executed;");
        	orderServiceLog.debug(String.format("\tOrder list value received: %s;", orders.toString()));
			
			orderRepository.saveAll(orders);
		} catch (Exception e) {
			orderServiceLog.error(String.format("PATCH order service: patch List method error. Method: %s", e.getMessage()));
		}
    }

    @Override
    public List<Order> get() {
    	try {
    		
    		orderServiceLog.debug("\tGET order service: get list method invocated;");
        	orderServiceLog.debug("\tGET order service: get list method executed;");
        	
        	List<Order> orderList = orderRepository.findAll();
        	
        	orderServiceLog.debug(String.format("\tOrder list value: %s;", orderList.toString()));
        	
    		return orderList;
		} catch (Exception e) {
			// TODO: handle exception
			orderServiceLog.error(String.format("GET order service: get list method error. Method: %s", e.getMessage()));
		}
    	return null;
    }
}
