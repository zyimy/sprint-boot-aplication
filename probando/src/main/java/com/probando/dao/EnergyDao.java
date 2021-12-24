package com.probando.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.probando.entity.Energy;
public interface EnergyDao extends JpaRepository<Energy, Long> {

}
