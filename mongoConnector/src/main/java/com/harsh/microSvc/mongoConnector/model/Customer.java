package com.harsh.microSvc.mongoConnector.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	private String email;
	private String gender;
	private int age;
	private int satisfaction;
}
