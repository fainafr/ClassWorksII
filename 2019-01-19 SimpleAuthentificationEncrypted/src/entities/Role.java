package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="roles_enc")
public class Role {
	
	@Id
	@Column(length=100)
	private String role;
	
	@ManyToMany
	@JsonBackReference
	private Set<Account> accounts = new HashSet<>();
	
	public Role() {}
	public Role(String role) {
		this.role = role;
	}

	public String getRole() {return role;}
	public void setRole(String role) {this.role = role;}

	public Set<Account> getAccounts() {return accounts;}
	public void setAccounts(Set<Account> accounts) {this.accounts = accounts;}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		Role other = (Role) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
	
	
	
	
}
