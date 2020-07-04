package jp.mirageworld.spring.terasoluna.demo.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.UserDetails;

import jp.mirageworld.spring.terasoluna.demo.validation.constraints.Password;
import jp.mirageworld.spring.terasoluna.demo.validation.constraints.Username;

import lombok.Data;

/**
 * ユーザ管理テーブル。
 */
@Data
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "username" }),
		@UniqueConstraint(columnNames = { "email" }),
})
@Entity(name = "users")
@EntityListeners(AuditingEntityListener.class)
@SuppressWarnings("serial")
public class Users implements UserDetails, BaseModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, insertable = true, updatable = false)
	Integer id;

	@NotBlank
	@UniqueElements
	@Username
	@Column(nullable = false, length = 255, insertable = true, updatable = false)
	String username;

	@NotBlank
	@UniqueElements
	@Email
	@Column(nullable = false, length = 255, insertable = true, updatable = true)
	String email;

	@NotBlank
	@Password
	@Column(nullable = false, length = 255, insertable = true, updatable = true)
	String password;

	@Column(nullable = false, length = 1, insertable = true, updatable = true)
	boolean enabled = false;

	@CreatedDate
	@Column(nullable = false, insertable = true, updatable = false)
	LocalDateTime created;

	@LastModifiedDate
	@Column(nullable = false, insertable = true, updatable = false)
	LocalDateTime updated;

	@ManyToMany(mappedBy = "users")
	List<Roles> authorities;

	@Transient
	final boolean accountNonExpired = false;

	@Transient
	final boolean accountNonLocked = false;

	@Transient
	final boolean credentialsNonExpired = false;

}
