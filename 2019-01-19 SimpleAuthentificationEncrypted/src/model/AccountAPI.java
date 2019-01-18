package model;

import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import entities.Account;
import entities.Role;
import repo.AccountJpaRepository;
import repo.RoleJpaRepository;

@Service
public class AccountAPI implements IAccount{
	
	@Autowired
	AccountJpaRepository accounts;
	@Autowired
	RoleJpaRepository roles;
	
	@Autowired
	PasswordEncoder bCryptEncoder;

	@Override
	public boolean addAccount(String login, String password) {
		if (accounts.existsById(login)) return false;
		accounts.save(new Account(login, bCryptEncoder.encode(password)));
		return true;
	}

	@Override
	@Transactional
	public boolean addRole(String login, String roleString) {
		if (!accounts.existsById(login)) return false;
		
		Account account = accounts.findById(login).get();
		Role role = roles.existsById(roleString) ? roles.findById(roleString).get() : new Role(roleString);
		
		role.getAccounts().add(account);
		roles.save(role);
		return true;
	}

	@Override
	public String getPassword(String login) {
		Account acc = accounts.findById(login).orElse(null);
		return acc == null ? null :acc.getPassword();
	}

	@Override
	@Transactional
	public String[] getRoles(String login) {
		Account acc = accounts.findById(login).orElse(null);
		if (acc != null){
			Set<Role> rls = acc.getRoles();
			return  rls.stream().map(Role::getRole).collect(Collectors.toList()).toArray(new String[rls.size()]);		
		}
		return new String[0];
	}
	
	@Override
	public String[] getAccounts(String roleString) {
		Role role = roles.findById(roleString).orElse(null);
		if (role != null){
			Set<Account> acc = role.getAccounts();
			return  acc.stream().map(Account::getLogin).collect(Collectors.toList()).toArray(new String[acc.size()]);		
		}
		return new String[0];
	}
	
	

}
