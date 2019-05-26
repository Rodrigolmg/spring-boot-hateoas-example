package br.senaigo.mobile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senaigo.mobile.entities.People;

public interface PeopleRepository extends JpaRepository<People, Long> {

}
