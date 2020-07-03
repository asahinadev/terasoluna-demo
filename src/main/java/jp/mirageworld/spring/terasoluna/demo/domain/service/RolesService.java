package jp.mirageworld.spring.terasoluna.demo.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jp.mirageworld.spring.terasoluna.demo.domain.model.Roles;
import jp.mirageworld.spring.terasoluna.demo.domain.repository.RolesRepository;
import lombok.Getter;
import lombok.Setter;

@Service
public class RolesService extends BaseService<Roles, RolesRepository, Integer> {

	@Setter
	int size = 20;

	@Getter
	@Autowired
	RolesRepository repository;

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
