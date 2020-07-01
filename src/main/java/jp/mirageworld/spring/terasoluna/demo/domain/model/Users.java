package jp.mirageworld.spring.terasoluna.demo.domain.model;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table
public class Users implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@NotBlank
	@UniqueElements
	@Size(min = 8, max = 32)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "半角英数で入力してください。")
	String username;

	@NotBlank
	@UniqueElements
	@Size(min = 8, max = 32)
	@Email
	String email;

	@NotBlank
	@Size(min = 8, max = 32)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "半角英数で入力してください。")
	String password;
	boolean enabled;

	@Transient
	@Override
	public Collection<SimpleGrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("USER"));
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}
