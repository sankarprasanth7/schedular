package com.incl.content.schedular.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.incl.content.schedular.bean.Job;

/**
 * Created by naresh u.
 */
/**
 * All operations that are required to query database
 */
public interface JobRepository extends JpaRepository<Job, Long> {
	Job findByUsername(String username);

	Job findById(int dropboxId);

	Job findBySender(int sender);

	/*
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	@Query("select d from Job d where d.group_id= :group_id")
	List<Job> findAllByGroup(@Param("group_id") int group);
}
