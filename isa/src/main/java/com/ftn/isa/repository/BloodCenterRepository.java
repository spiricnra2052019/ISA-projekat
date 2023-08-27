package com.ftn.isa.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.hibernate.LockMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.ftn.isa.model.BloodCenter;

public interface BloodCenterRepository extends JpaRepository<BloodCenter, Long> {
	@Query("select c from BloodCenter c WHERE " +
			"UPPER(c.name) LIKE CONCAT('%' , :query, '%')" +
			"Or UPPER(c.address.city) LIKE CONCAT('%' , :query, '%')")
	public List<BloodCenter> searchCenters(String query);

	@Query("select bc from BloodCenter bc " +
			"where bc.averageRate = :filterQuery and bc in (select c from BloodCenter c WHERE " +
			"UPPER(c.name) LIKE CONCAT('%' , :searchQuery, '%')" +
			"Or UPPER(c.address.city) LIKE CONCAT('%' , :searchQuery, '%')) " +
			"")
	public List<BloodCenter> filterCenters(String searchQuery, float filterQuery);

	@Query("select bc from BloodCenter bc where bc.id = :id")
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({ @QueryHint(name = "javax.persistence.lock.timeout", value = "0") })
	Optional<BloodCenter> findByIdForSchedule(Long id);

	public BloodCenter findOneByBloodCenterAdministratorId(Long id);

}