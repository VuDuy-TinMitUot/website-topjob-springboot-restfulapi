package com.vuduy.restfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuduy.restfulapi.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
