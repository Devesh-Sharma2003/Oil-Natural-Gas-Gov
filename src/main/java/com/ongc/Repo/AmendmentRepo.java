package com.ongc.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ongc.model.AmendmentMstModel;

public interface AmendmentRepo extends JpaRepository<AmendmentMstModel, Long> {

}
