/**
 * 
 */
package com.harsh.microSvc.mongoConnector.repository;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.harsh.microSvc.mongoConnector.model.Sale;

/**
 * @author Harsh
 *
 */
public interface SalesRepository extends MongoRepository<Sale, ObjectId> {

	List<Sale> findByStoreLocation(String storeLocation);

	List<Sale> findByCouponUsed(Boolean couponUsed);
	
	List<Sale> findBySaleDateBetween(Date from, Date to);
}