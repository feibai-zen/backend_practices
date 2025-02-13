package com.feibai.spring.study.dao;

import com.feibai.spring.study.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Customer findOneByUsername(String username);
}
