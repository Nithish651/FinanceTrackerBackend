package com.financetracker.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financetracker.userservice.modal.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
