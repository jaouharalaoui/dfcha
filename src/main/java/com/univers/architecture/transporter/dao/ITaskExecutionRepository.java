/**
 * 
 */
package com.univers.architecture.transporter.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.univers.architecture.transporter.model.TaskExecution;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author sabir
 *
 */
public interface ITaskExecutionRepository extends PagingAndSortingRepository<TaskExecution, String> , JpaSpecificationExecutor<TaskExecution> {

}
