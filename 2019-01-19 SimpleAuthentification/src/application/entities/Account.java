package application.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter

@Entity
@Table(name="accounts")
public class Account {
	
	/*
	 * Only on ID the length must be explicit. JPA feature. 
	 */
	@Id
	@Column(length=100) 
	private String login;
	private String password;
	
	@ManyToMany(mappedBy="accounts", cascade=CascadeType.ALL) 
	private Set<Role> roles; // why not one2many?
	
	public Account(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public Set<Role> addRoleToSet(Role role){
		roles.add(role);
		return roles;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}	
	
	
}
