package com.test.testdemo.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, Integer> {

}
