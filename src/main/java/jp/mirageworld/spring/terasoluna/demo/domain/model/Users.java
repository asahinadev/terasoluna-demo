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
 * <pre>
 * `id`       INT          NOT NULL AUTO_INCREMENT            COMMENT '識別番号' ,
 * `username` VARCHAR(255) NOT NULL                           COMMENT 'アカウント' ,
 * `email`    VARCHAR(255) NOT NULL                           COMMENT 'メールアドレス' ,
 * `password` VARCHAR(255) NOT NULL                           COMMENT 'パスワード' ,
 * `enabled`  BIT(1)       NOT NULL DEFAULT '0'               COMMENT '有効フラグ' ,
 * `created`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時' ,
 * `updated`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日時' , *
 * </pre>
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
	Integer id;

	@NotBlank
	@UniqueElements
	@Username
	String username;

	@NotBlank
	@UniqueElements
	@Email
	String email;

	@NotBlank
	@Password
	String password;

	@Column(nullable = false)
	boolean enabled;

	@CreatedDate
	@Column(nullable = false)
	LocalDateTime created;

	@LastModifiedDate
	@Column(nullable = false)
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
