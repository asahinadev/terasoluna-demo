package jp.mirageworld.spring.terasoluna.demo.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.mirageworld.spring.terasoluna.demo.domain.model.Countries;

@Repository
public interface CountriesRepository
	extends
	JpaRepository<Countries, Integer> {

	public Optional<Countries> findByName(String name);

	public Optional<Countries> findByCode(String code);

	public Optional<Countries> findByCode2(String code2);

	public Optional<Countries> findByCode3(String code3);

}
