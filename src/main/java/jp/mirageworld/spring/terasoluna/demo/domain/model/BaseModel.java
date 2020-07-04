package jp.mirageworld.spring.terasoluna.demo.domain.model;

/**
 * ベースモデル.
 *
 * @param <ID>
 *                 {@link javax.persistence.Id}
 */
public interface BaseModel<ID> {

	ID getId();
}
