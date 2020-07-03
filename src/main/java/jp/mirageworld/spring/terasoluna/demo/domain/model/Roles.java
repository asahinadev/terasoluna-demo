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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;

import jp.mirageworld.spring.terasoluna.demo.validation.constraints.Code;
import lombok.Data;

@Data
@Table(name = "roles", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "name" }),
		@UniqueConstraint(columnNames = { "code" }),
})
@Entity(name = "roles")
@SuppressWarnings("serial")
public class Roles implements GrantedAuthority, BaseModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@NotBlank
	@Code
	@UniqueElements
	@Column(nullable = false, name = "code")
	String authority;

	@NotBlank
	@Size(max = 50)
	@UniqueElements
	@Column(nullable = false, name = "name")
	String name;

	@ManyToMany
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "role_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	List<Users> users;

}
