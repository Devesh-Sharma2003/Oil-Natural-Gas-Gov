package com.ongc.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ongc.model.ValidityMstModel;

@Repository
public interface validityRepo extends JpaRepository<ValidityMstModel, Long> {

}
