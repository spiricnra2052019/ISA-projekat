package com.ftn.isa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ftn.isa.model.Administrator;
import com.ftn.isa.repository.AdministratorRepository;

@Service
public class AdministratorService {
	@Autowired
	private AdministratorRepository administratorRepository;

	public Administrator findOne(Long id) {
		return administratorRepository.findById(id).orElseGet(null);
	}

	public List<Administrator> findAll() {
		return administratorRepository.findAll();
	}

	public Administrator save(Administrator Administrator) {
		return administratorRepository.save(Administrator);
	}

	public void remove(Long id) {
		administratorRepository.deleteById(id);
	}

	public List<Administrator> findByLastname(String lastname) {
		return administratorRepository.findAllByLastname(lastname);
	}

	public List<Administrator> findByNameAndLastname(String firstName, String lastName) {
		return administratorRepository.findByNameAndLastnameAllIgnoringCase(firstName, lastName);
	}

	public Optional<Administrator> findOneByUsername(String username) {
		return administratorRepository.findOneByUsername(username);
	}
}
