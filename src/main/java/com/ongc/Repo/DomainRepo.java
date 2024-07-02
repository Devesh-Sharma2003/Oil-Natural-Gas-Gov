package com.ongc.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ongc.model.DomainMstModel;

public interface DomainRepo extends JpaRepository<DomainMstModel, Long> {

}
