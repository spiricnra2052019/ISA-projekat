package com.ftn.isa.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import com.ftn.isa.model.User;


public class InMemoryUserRepository implements UserRepository {
	
	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, User> users = new ConcurrentHashMap<Long, User>();

	@Override
	public Collection<User> findAll() {
		return this.users.values();
	}

	@Override
	public User create(User user) {
		Long id = user.getId();

		if (id == null) {
			id = counter.incrementAndGet();
			user.setId(id);
		}

		this.users.put(id, user);
		return user;
	}

	@Override
	public User findOne(Long id) {
		return this.users.get(id);
	}

	@Override
	public User delete(Long id) {
		User user = this.users.remove(id);
		return user;
	}

	@Override
	public User update(User user) {
		Long id = user.getId();

		if (id != null) {
			this.users.put(id, user);
		}

		return user;
	}

}
