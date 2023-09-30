package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.ChargeCoin;

public interface ChargeCoinRepository extends JpaRepository<ChargeCoin, Long> {

}
