package jp.mirageworld.spring.terasoluna.demo.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import jp.mirageworld.spring.terasoluna.demo.domain.model.Roles;
import jp.mirageworld.spring.terasoluna.demo.domain.repository.RolesRepository;

@Service
public class RolesService extends BaseService<Roles, RolesRepository, Integer> {

	public Optional<Roles> findByCode(String code) {
		// コーディングミスのチェック
		Assert.notNull(code, "arguments error");

		// 検索をする
		return getRepository().findByCode(code);
	}

	@Override
	public List<Roles> findAll() {
		// 検索をする
		return getRepository().findAll();
	}

	public Page<Roles> findAll(int page, int size, Sort sort) {
		// 検索をする
		return getRepository().findAll(PageRequest.of(page, size, sort));
	}
}
