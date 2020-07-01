package jp.mirageworld.spring.terasoluna.demo.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.mirageworld.spring.terasoluna.demo.domain.model.Users;

@Repository
public interface UsersRepository
		extends JpaRepository<Users, Long> {

	public Optional<Users> findByIdAndEnabledNot(Long id);

	public Optional<Users> findByUsernameAndEnabledNot(String username);

	public Optional<Users> findByEmailAndEnabledNot(String email);

	public Page<Users> findAllByEnabled(Pageable pageable);

}
