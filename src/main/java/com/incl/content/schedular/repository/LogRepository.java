/**
 * 
 */
package com.incl.content.schedular.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.incl.content.schedular.bean.Log;

/**
 * @author prasanth
 *
 */
/**
 * All operations that are required to query database
 */
public interface LogRepository extends JpaRepository<Log, Long> {

	Log findById(int id);

	List<Log> findByDropboxId(int dropBoxId, Pageable pageable);
}
