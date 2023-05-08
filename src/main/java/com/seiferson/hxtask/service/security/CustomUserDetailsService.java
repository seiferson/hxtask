package com.seiferson.hxtask.service.security;

import com.seiferson.hxtask.config.security.CustomUserDetails;
import com.seiferson.hxtask.model.security.Account;
import com.seiferson.hxtask.repository.security.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByNickname(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return new CustomUserDetails(account);
    }
}
