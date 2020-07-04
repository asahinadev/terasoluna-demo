package jp.mirageworld.spring.terasoluna.demo.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import jp.mirageworld.spring.terasoluna.demo.domain.model.Countries;
import jp.mirageworld.spring.terasoluna.demo.domain.repository.CountriesRepository;

import lombok.Getter;
import lombok.Setter;

@Service
public class CountriesService extends BaseService<Countries, CountriesRepository, Integer> {

	@Setter
	int size = 20;

	@Getter
	@Autowired
	CountriesRepository repository;

	@Override
	public List<Countries> findAll() {
		// 検索をする
		return getRepository().findAll();
	}

	public Page<Countries> findAll(int page, int size, Sort sort) {
		// 検索をする
		return getRepository().findAll(PageRequest.of(page, size, sort));
	}

	public Optional<Countries> findByCode(String code) {
		// コーディングミスのチェック
		Assert.notNull(code, "arguments error");

		// 検索をする
		return getRepository().findByCode(code);
	}

	public Optional<Countries> findByCode2(String code) {
		// コーディングミスのチェック
		Assert.notNull(code, "arguments error");

		// 検索をする
		return getRepository().findByCode2(code);
	}

	public Optional<Countries> findByCode3(String code) {
		// コーディングミスのチェック
		Assert.notNull(code, "arguments error");

		// 検索をする
		return getRepository().findByCode3(code);
	}

	public Optional<Countries> findByCodeAny(String code) {
		// コーディングミスのチェック
		Assert.notNull(code, "arguments error");

		// 検索をする
		Optional<Countries> item = getRepository().findByCode(code);
		if (!item.isPresent()) {

			// 検索対象を変更
			item = getRepository().findByCode3(code);
			if (!item.isPresent()) {

				// 検索対象を変更
				item = getRepository().findByCode2(code);
			}
		}
		return item;
	}
}
