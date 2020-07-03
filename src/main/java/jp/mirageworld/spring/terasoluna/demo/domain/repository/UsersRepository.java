package jp.mirageworld.spring.terasoluna.demo.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.mirageworld.spring.terasoluna.demo.domain.model.Users;

@Repository
public interface UsersRepository
		extends JpaRepository<Users, Integer> {

	public Optional<Users> findByIdAndEnabledNot(Integer id);

	public Optional<Users> findByUsernameAndEnabledNot(String username);

	public Optional<Users> findByEmailAndEnabledNot(String email);

	public List<Users> findAllByEnabled();

	public Page<Users> findAllByEnabled(Pageable pageable);

}
