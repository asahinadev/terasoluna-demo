package jp.mirageworld.spring.terasoluna.demo.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jp.mirageworld.spring.terasoluna.demo.domain.model.Users;
import jp.mirageworld.spring.terasoluna.demo.domain.repository.UsersRepository;
import lombok.Setter;

@Service
public class UsersService {

	@Setter
	int size = 20;

	UsersRepository usersRepository;

	public Optional<Users> insert(Users users) {
		// ID が未指定であること
		if (Objects.isNull(users.getId())) {
			throw new IllegalArgumentException("id");
		}

		// 重複してないこと
		if (findByUsername(users.getUsername()).isPresent()) {
			throw new IllegalArgumentException("username");
		}

		// 重複してないこと
		if (findByEmail(users.getEmail()).isPresent()) {
			throw new IllegalArgumentException("email");
		}

		return Optional.of(usersRepository.save(users));
	}

	public Optional<Users> update(Users users) {
		// ID が指定されていること
		if (!Objects.isNull(users.getId())) {
			throw new IllegalArgumentException("id");
		}

		// 存在していること
		if (!findById(users.getId()).isPresent()) {
			throw new IllegalArgumentException("id");
		}

		// 重複してないこと（自分自身は除く）
		if (findByUsername(users.getUsername())
				.filter(e -> !Objects.equals(e.getId(), users.getId())).isPresent()) {
			throw new IllegalArgumentException("username");
		}

		// 重複してないこと（自分自身は除く）
		if (findByEmail(users.getEmail())
				.filter(e -> !Objects.equals(e.getId(), users.getId())).isPresent()) {
			throw new IllegalArgumentException("email");
		}

		return Optional.of(usersRepository.save(users));
	}

	public void delete(Users users) {
		// ID が指定されていること
		if (!Objects.isNull(users.getId())) {
			throw new IllegalArgumentException();
		}
		usersRepository.delete(users);
	}

	public Optional<Users> findById(Integer id) {
		// 有効なアカウントから検索をする
		return usersRepository.findByIdAndEnabledNot(id);
	}

	public Optional<Users> findByEmail(String email) {
		// 有効なアカウントから検索をする
		return usersRepository.findByEmailAndEnabledNot(email);
	}

	public Optional<Users> findByUsername(String username) {
		// 有効なアカウントから検索をする
		return usersRepository.findByUsernameAndEnabledNot(username);
	}

	public List<Users> findAll() {
		return usersRepository.findAll();
	}

	public Page<Users> findAll(int page) {
		return findAll(page, size, "id");
	}

	public Page<Users> findAll(int page, int size) {
		return findAll(page, size, "id");
	}

	public Page<Users> findAll(int page, String... sort) {
		return findAll(page, size, Sort.by(sort));
	}

	public Page<Users> findAll(int page, int size, String... sort) {
		return findAll(page, size, Sort.by(sort));
	}

	public Page<Users> findAll(int page, Sort sort) {
		return findAll(page, size, sort);
	}

	public Page<Users> findAll(int page, int size, Sort sort) {
		// 有効なアカウントから検索をする
		return usersRepository.findAllByEnabled(PageRequest.of(page, size, sort));
	}
}
