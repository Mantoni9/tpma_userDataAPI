package com.m2olie.userDataService.repository;

import com.m2olie.userDataService.model.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PractitionerRepository extends JpaRepository<Practitioner, Long> {

}
