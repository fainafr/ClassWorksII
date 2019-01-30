package application.model;

import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.Account;
import application.entities.Role;
import application.repo.AccountJpaRepository;
import application.repo.RoleJpaRepository;

@Service
public class AdminImpl implements IAdmin {

	@Autowired
	AccountJpaRepository accounts;

	@Autowired
	RoleJpaRepository roles;

	@Override
	public boolean addAccount(String login, String password) {
		if (accounts.existsById(login)) {
			return false;
		}
		accounts.save(new Account(login, password));
		return true;
	}

	 /*
	 * if there's new Role, you have to save it. Really? Transactional doesn't save new?
	 */
	@Override
	@Transactional
	public boolean addRole(String login, String roleName) {
		if(!accounts.existsById(login)){
			return false; 
		}
		Account account = accounts.findById(login).get();
		Role role = roles.findById(roleName).orElse(new Role(roleName));
//		accounts.findById(login).get().getRoles().add(role);
		role.getAccounts().add(account);
		roles.save(role);
		return true;
	}

	@Override
	public String getPassword(String login) {
		Account acc = accounts.findById(login).orElse(null);
		return acc == null ? null : acc.getPassword();
	}

	/*
	 * Why @Transactional on observer method? 
	 */
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
