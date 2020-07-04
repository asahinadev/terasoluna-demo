package jp.mirageworld.spring.terasoluna.demo.domain.model;

import java.io.Serializable;

/**
 * ベースモデル.
 *
 * @param <ID>
 *                 {@link javax.persistence.Id}
 */
public interface BaseModel<ID> extends Serializable {

	ID getId();
}
