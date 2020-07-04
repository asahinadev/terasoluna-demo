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
@Entity
@SuppressWarnings("serial")
public class Countries implements BaseModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@NotBlank
	@Code(max = 2)
	@UniqueElements
	@Column(nullable = false, length = 2, name = "code_2",
			insertable = true, updatable = false,
			columnDefinition = "char(2)")
	String code2;

	@NotBlank
	@Code(max = 3)
	@UniqueElements
	@Column(nullable = false, length = 3, name = "code_3",
			insertable = true, updatable = false,
			columnDefinition = "char(3)")
	String code3;

	@NotBlank
	@Code(max = 3)
	@UniqueElements
	@Column(nullable = false, length = 3,
			insertable = true, updatable = false,
			columnDefinition = "char(3)")
	String code;

	@NotBlank
	@Size(max = 50)
	@Column(nullable = false, name = "name", length = 50, insertable = true, updatable = true)
	String name;

}
