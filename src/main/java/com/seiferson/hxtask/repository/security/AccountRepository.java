package com.seiferson.hxtask.repository.security;

import com.seiferson.hxtask.model.security.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {

    Boolean existsByNickname(String nickname);

    Boolean existsByEmail(String email);

    Optional<Account> findByNickname(String nickname);
}
