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

/**
 * @param <E>
 *                 {@link javax.persistence.Entity}
 * @param <R>
 *                 {@link org.springframework.stereotype.Repository}
 * @param <ID>
 *                 {@link javax.persistence.Id}
 */
public abstract class BaseService<E extends BaseModel<ID>, R extends JpaRepository<E, ID>, ID> {

	@Setter
	int size = 20;

	@Autowired
	Validator validator;

	abstract public R getRepository();

	abstract public Page<E> findAll(int page, int size, Sort sort);

	abstract public List<E> findAll();

	/**
	 * インサート.
	 * 
	 * @param entry
	 *                  {@link javax.persistence.Entity}
	 * 
	 * @return {@link Optional} in {@link javax.persistence.Entity}
	 * 
	 * @throws BindException
	 *                           チェックエラー
	 */
	public Optional<E> insert(E entry) throws BindException {
		// コーディングミスのチェック
		Assert.notNull(entry, "arguments error");
		Assert.isNull(entry.getId(), "arguments error");

		// バリデーター
		validate(entry);

		// 登録
		return Optional.of(getRepository().save(entry));
	}

	/**
	 * アップデート.
	 * 
	 * @param entry
	 *                  {@link javax.persistence.Entity}
	 * 
	 * @return {@link Optional} in {@link javax.persistence.Entity}
	 * 
	 * @throws BindException
	 *                           チェックエラー
	 */
	public Optional<E> update(E entry) throws BindException {
		// コーディングミスのチェック
		Assert.notNull(entry, "arguments error");
		Assert.notNull(entry.getId(), "arguments error");

		// バリデーター
		validate(entry);

		// 登録
		return Optional.of(getRepository().save(entry));
	}

	/**
	 * デリート.
	 * 
	 * @param entry
	 *                  {@link javax.persistence.Entity}
	 * 
	 * @return {@link Optional} in {@link javax.persistence.Entity}
	 * 
	 * @throws BindException
	 *                           チェックエラー
	 */
	public void delete(E entry) {
		// コーディングミスのチェック
		Assert.notNull(entry, "arguments error");
		Assert.notNull(entry.getId(), "arguments error");

		// 削除
		getRepository().delete(entry);
	}

	/**
	 * セレクト.
	 * 
	 * @param id
	 *               {@link javax.persistence.Id}
	 * 
	 * @return {@link Optional} in {@link javax.persistence.Entity}
	 */
	public Optional<E> findById(ID id) {
		// コーディングミスのチェック
		Assert.notNull(id, "arguments error");

		// 検索
		return getRepository().findById(id);
	}

	/**
	 * バリデーション.
	 * 
	 * @param entry
	 *                  {@link javax.persistence.Entity}
	 * 
	 * @return {@link Optional} in {@link javax.persistence.Entity}
	 */
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

	/**
	 * 一覧検索.
	 * 
	 * @param page
	 *                 ページ番号（最初のページは0）
	 * 
	 * @return {@link Page} in {@link javax.persistence.Entity}
	 */
	public Page<E> findAll(int page) {
		// 検索
		return findAll(page, size, "id");
	}

	/**
	 * 一覧検索.
	 * 
	 * @param page
	 *                 ページ番号（最初のページは0）
	 * @param size
	 *                 リストサイズ
	 * 
	 * @return {@link Page} in {@link javax.persistence.Entity}
	 */
	public Page<E> findAll(int page, int size) {
		// 検索
		return findAll(page, size, "id");
	}

	/**
	 * 一覧検索.
	 * 
	 * @param page
	 *                 ページ番号（最初のページは0）
	 * @param size
	 *                 リストサイズ
	 * @param sort
	 *                 ソート対象
	 * 
	 * @return {@link Page} in {@link javax.persistence.Entity}
	 */
	public Page<E> findAll(int page, String... sort) {
		// コーディングミスのチェック
		Assert.notNull(sort, "arguments error");
		Assert.noNullElements(sort, "arguments error");

		// 検索
		return findAll(page, size, Sort.by(sort));
	}

	/**
	 * 一覧検索.
	 * 
	 * @param page
	 *                 ページ番号（最初のページは0）
	 * @param size
	 *                 リストサイズ
	 * @param sort
	 *                 ソート対象
	 * 
	 * @return {@link Page} in {@link javax.persistence.Entity}
	 */
	public Page<E> findAll(int page, int size, String... sort) {
		// コーディングミスのチェック
		Assert.notNull(sort, "arguments error");
		Assert.noNullElements(sort, "arguments error");

		// 検索
		return findAll(page, size, Sort.by(sort));
	}

	/**
	 * 一覧検索.
	 * 
	 * @param page
	 *                 ページ番号（最初のページは0）
	 * @param sort
	 *                 ソート対象
	 * 
	 * @return {@link Page} in {@link javax.persistence.Entity}
	 */
	public Page<E> findAll(int page, Sort sort) {
		// コーディングミスのチェック
		Assert.notNull(sort, "arguments error");

		// 検索
		return findAll(page, size, sort);
	}

}
