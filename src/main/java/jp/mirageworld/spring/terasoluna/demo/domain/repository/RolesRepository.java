package jp.mirageworld.spring.terasoluna.demo.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.mirageworld.spring.terasoluna.demo.domain.model.Roles;

@Repository
public interface RolesRepository
		extends JpaRepository<Roles, Integer> {

	public Optional<Roles> findByName(String name);

	public Optional<Roles> findByCode(String code);

}
