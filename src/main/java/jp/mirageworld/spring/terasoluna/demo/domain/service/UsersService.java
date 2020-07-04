package jp.mirageworld.spring.terasoluna.demo.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import jp.mirageworld.spring.terasoluna.demo.domain.model.Users;
import jp.mirageworld.spring.terasoluna.demo.domain.repository.UsersRepository;

@Service
public class UsersService extends BaseService<Users, UsersRepository, Integer> {

	public Optional<Users> findById(Integer id) {
		// コーディングミスのチェック
		Assert.notNull(id, "arguments error");

		// 有効なアカウントから検索をする
		return getRepository().findByIdAndEnabledNot(id);
	}

	public Optional<Users> findByEmail(String email) {
		// コーディングミスのチェック
		Assert.notNull(email, "arguments error");

		// 有効なアカウントから検索をする
		return getRepository().findByEmailAndEnabledNot(email);
	}

	public Optional<Users> findByUsername(String username) {
		// コーディングミスのチェック
		Assert.notNull(username, "arguments error");

		// 有効なアカウントから検索をする
		return getRepository().findByUsernameAndEnabledNot(username);
	}

	public Page<Users> findAll(int page, int size, Sort sort) {
		// コーディングミスのチェック
		Assert.notNull(sort, "arguments error");

		// 有効なアカウントから検索をする
		return getRepository().findAllByEnabled(PageRequest.of(page, size, sort));
	}

	public List<Users> findAll() {
		// 有効なアカウントから検索をする
		return getRepository().findAllByEnabled();
	}

}
