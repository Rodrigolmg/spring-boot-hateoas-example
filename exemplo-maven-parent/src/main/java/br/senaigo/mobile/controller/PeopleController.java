package br.senaigo.mobile.controller;

import br.senaigo.mobile.entities.People;
import br.senaigo.mobile.interfaces.GenericOperationsController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

public class PeopleController implements GenericOperationsController<People> {
    @Override
    public Resource<People> post(People people) {
        return null;
    }

    @Override
    public void put(People people) {

    }

    @Override
    public void delete(People people) {

    }

    @Override
    public Resources<People> get() {
        return null;
    }

    @Override
    public Resource<People> get(Integer id) {
        return null;
    }

    @Override
    public void patch(People people) {

    }
}
