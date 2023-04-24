/**
 * 
 */
package com.harsh.microSvc.mongoConnector.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Harsh
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	private String name;
	private List<String> tags;
	private float price;
	private int quantity;
}
