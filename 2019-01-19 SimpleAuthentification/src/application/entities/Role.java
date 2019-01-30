package application.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(of="role")
@Getter @Setter

@Entity
@Table(name="roles")
public class Role {
	
	@Id
	@Column(length=100)
	private String role;
	
	@ManyToMany
	@JsonBackReference // why roles should know about the accounts? omitted;
	private Set<Account> accounts = new HashSet<>();
	
	public Role(String role) {
		this.role = role;
	}	
}
