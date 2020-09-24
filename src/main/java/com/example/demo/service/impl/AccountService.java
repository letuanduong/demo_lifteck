package com.example.demo.service.impl;

import com.example.demo.model.Account;
import com.example.demo.model.Role;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Service
public class AccountService implements IAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void save(Account account) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if(account == null) {
            throw new UsernameNotFoundException("username" + username);
        }
        return new CustomerUserDetail(account);
//        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<GrantedAuthority>();
//        List<Role> roles = new ArrayList<Role>();
//        roles = account.getRoles();
//        for (Role role: roles) {
//            grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        UserDetails userDetails = new User(
//            account.getUsername(),
//            account.getPassword(),
//            grantedAuthoritySet
//        );
//        return userDetails;
    }

    public UserDetails loadUserById(Long id){
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            return new CustomerUserDetail(account.get());
        } else return (UserDetails) new UsernameNotFoundException("User not found with id : " + id);
    }

}
