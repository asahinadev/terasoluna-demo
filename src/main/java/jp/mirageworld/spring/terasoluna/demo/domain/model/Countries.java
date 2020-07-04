package jp.mirageworld.spring.terasoluna.demo.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

import jp.mirageworld.spring.terasoluna.demo.validation.constraints.Code;

import lombok.Data;

/**
 * 国管理テーブル。
 */
@Data
@Table(name = "countries", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "code" }),
		@UniqueConstraint(columnNames = { "code_2" }),
		@UniqueConstraint(columnNames = { "code_3" }),
})
@Entity(name = "countries")
@SuppressWarnings("serial")
public class Countries implements BaseModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, insertable = true, updatable = false)
	Integer id;

	@NotBlank
	@Code
	@UniqueElements
	@Column(nullable = false, name = "code_2", length = 2, insertable = true, updatable = false)
	String code2;

	@NotBlank
	@Code
	@UniqueElements
	@Column(nullable = false, name = "code_3", length = 3, insertable = true, updatable = false)
	String code3;

	@NotBlank
	@Code
	@UniqueElements
	@Column(nullable = false, name = "code", length = 3, insertable = true, updatable = false)
	String code;

	@NotBlank
	@Size(max = 50)
	@Column(nullable = false, name = "name", length = 50, insertable = true, updatable = true)
	String name;

}
