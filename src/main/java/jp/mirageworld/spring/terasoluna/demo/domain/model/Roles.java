package jp.mirageworld.spring.terasoluna.demo.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Table(name = "roles", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "name" }),
})
@Entity(name = "roles")
@SuppressWarnings("serial")
public class Roles implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(nullable = false, name = "code")
	String authority;

	@Column(nullable = false, name = "name")
	String name;

	@ManyToMany
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "role_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	List<Users> users;

}
