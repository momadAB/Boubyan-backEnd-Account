package com.example.demo.repository;

import com.example.demo.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface BankRepository extends JpaRepository<BankAccountEntity, Long> {
        //    List<UserEntity> findByStatus(Status status);
        // Find by username, ignoring case
//        Optional<UserEntity> findByUsernameIgnoreCase(String username);
    }