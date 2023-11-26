package com.m2olie.userDataService.repository;

import com.m2olie.userDataService.model.HumanName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanNameRepository extends JpaRepository<HumanName, Long> {
}
