package com.appegate.task.operarions.infraestructure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appegate.task.operarions.domain.OperandData;

/**
 * 
 * @author daniel.sarmiento
 *
 */
@Repository
public interface OperandDataRepository extends CrudRepository<OperandData,String>{

}
