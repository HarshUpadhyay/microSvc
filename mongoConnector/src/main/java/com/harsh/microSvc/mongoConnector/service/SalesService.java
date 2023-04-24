/**
 * 
 */
package com.harsh.microSvc.mongoConnector.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsh.microSvc.mongoConnector.model.Sale;
import com.harsh.microSvc.mongoConnector.repository.SalesRepository;
import com.harsh.microSvc.mongoConnector.service.SalesService;

/**
 * @author Harsh
 *
 */
@Service
public class SalesService {
	
	@Autowired
	private SalesRepository salesRepository;

	// CREATE
	public Sale addSale(Sale sale) {
		sale.setId(ObjectId.get());
		return salesRepository.save(sale);
	}
	
	// READ
	public List<Sale> findAllSales() {
		return salesRepository.findAll();
	}
	
	public Optional<Sale> getSaleById(String id) {
		return salesRepository.findById(new ObjectId(id));
	}
	
	public List<Sale> getSaleByStoreLocation(String storeLocation) {
		return salesRepository.findByStoreLocation(storeLocation);
	}

	public List<Sale> getSalesByCouponUsed(Boolean couponUsed) {
		return salesRepository.findByCouponUsed(couponUsed);
	}
	
	public List<Sale> getSalesByDateBetween(Date from, Date to){
		return salesRepository.findBySaleDateBetween(from, to);
	}
	
	// UPDATE
	public Sale updateSale(Sale saleRequest) {
		try {
			Sale sale = salesRepository.findById(saleRequest.getId()).get();
			salesRepository.findById(saleRequest.getId());
			sale.setCouponUsed(saleRequest.getCouponUsed());
			sale.setCustomer(saleRequest.getCustomer());
			sale.setItems(saleRequest.getItems());
			sale.setPurchaseMethod(saleRequest.getPurchaseMethod());
			sale.setSaleDate(saleRequest.getSaleDate());
			sale.setStoreLocation(saleRequest.getStoreLocation());
			return salesRepository.save(sale);
		} catch (Exception e) {
			return null;
		}
	}
	
	// DELETE
	// AVOID USING THIS METHOD: sales should be voided or cancelled, not deleted
	public void deleteSale(String id) {
		salesRepository.deleteById(new ObjectId(id));
	}
}
