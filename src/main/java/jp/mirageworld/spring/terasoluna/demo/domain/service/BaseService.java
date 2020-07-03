package jp.mirageworld.spring.terasoluna.demo.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;

import jp.mirageworld.spring.terasoluna.demo.domain.model.BaseModel;
import lombok.Setter;

public abstract class BaseService<E extends BaseModel<ID>, R extends JpaRepository<E, ID>, ID> {

	@Setter
	int size = 20;

	@Autowired
	Validator validator;

	abstract public R getRepository();

	abstract public Page<E> findAll(int page, int size, Sort sort);

	abstract public List<E> findAll();

	public Optional<E> insert(E entry) throws BindException {
		// コーディングミスのチェック
		Assert.notNull(entry, "arguments error");
		Assert.isNull(entry.getId(), "arguments error");

		// バリデーター
		validate(entry);

		// 登録
		return Optional.of(getRepository().save(entry));
	}

	public Optional<E> update(E entry) throws BindException {
		// コーディングミスのチェック
		Assert.notNull(entry, "arguments error");
		Assert.notNull(entry.getId(), "arguments error");

		// バリデーター
		validate(entry);

		// 登録
		return Optional.of(getRepository().save(entry));
	}

	public void delete(E entry) {
		// コーディングミスのチェック
		Assert.notNull(entry, "arguments error");
		Assert.notNull(entry.getId(), "arguments error");

		// 削除
		getRepository().delete(entry);
	}

	public Optional<E> findById(ID id) {
		// コーディングミスのチェック
		Assert.notNull(id, "arguments error");

		// 検索
		return getRepository().findById(id);
	}

	public void validate(E entity) throws BindException {
		// コーディングミスのチェック
		Assert.notNull(entity, "arguments error");

		// バリデータ
		BindException result = new BindException(entity, "entity");
		validator.validate(entity, result);

		if (result.hasErrors()) {
			throw result;
		}
	}

	public Page<E> findAll(int page) {
		// 検索
		return findAll(page, size, "id");
	}

	public Page<E> findAll(int page, int size) {
		// 検索
		return findAll(page, size, "id");
	}

	public Page<E> findAll(int page, String... sort) {
		// コーディングミスのチェック
		Assert.notNull(sort, "arguments error");
		Assert.noNullElements(sort, "arguments error");

		// 検索
		return findAll(page, size, Sort.by(sort));
	}

	public Page<E> findAll(int page, int size, String... sort) {
		// コーディングミスのチェック
		Assert.notNull(sort, "arguments error");
		Assert.noNullElements(sort, "arguments error");

		// 検索
		return findAll(page, size, Sort.by(sort));
	}

	public Page<E> findAll(int page, Sort sort) {
		// コーディングミスのチェック
		Assert.notNull(sort, "arguments error");

		// 検索
		return findAll(page, size, sort);
	}

}
