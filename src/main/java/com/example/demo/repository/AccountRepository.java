package com.example.demo.repository;

import com.example.demo.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountEntity,Long> {
    AccountEntity findBySessionId(String sessionId);

    AccountEntity findByAccountNumber(String toAccountNumber);
}
