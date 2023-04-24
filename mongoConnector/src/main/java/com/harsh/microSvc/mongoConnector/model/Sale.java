/**
 * 
 */
package com.harsh.microSvc.mongoConnector.model;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Harsh
 * 
 *
 */
@Document(collection = "sales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
	@Id
	private ObjectId id;
	private Date saleDate;
	private String storeLocation;
	private Boolean couponUsed;
	private String purchaseMethod;
	private List<Item> items;
	private Customer customer;
}
