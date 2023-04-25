/**
 *
 */
package com.harsh.microSvc.mongoConnector.controller;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.microSvc.mongoConnector.model.Sale;
import com.harsh.microSvc.mongoConnector.service.SalesService;

/**
 * @author Harsh
 *
 */
@RestController
@RequestMapping("/sales")
public class SalesRestController {
    @Autowired
    private SalesService salesService;
    private static final Logger _logger = Logger.getLogger(SalesRestController.class.getName());

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sale addSale(@RequestBody Sale sale) {
        return salesService.addSale(sale);
    }

    // READ
    @GetMapping
    public List<Sale> getAllSales() {
        return salesService.findAllSales();
    }

    @GetMapping("/{id}")
    public Optional<Sale> getSale(@PathVariable String id) {
        return salesService.getSaleById(id);
    }

    @GetMapping("/storeLocation/{storeLocation}")
    public List<Sale> getSalesByStoreLocation(@PathVariable String storeLocation) {
        return salesService.getSaleByStoreLocation(storeLocation);
    }

    @GetMapping("/couponUsed/{couponUsed}")
    public List<Sale> getSalessByCouponUsed(@PathVariable Boolean couponUsed) {
        return salesService.getSalesByCouponUsed(couponUsed);
    }

    @GetMapping("/saleDateBetween")
    public List<Sale> getSalesByDateBetween(@RequestParam(required = false) String from, @RequestParam(required = false) String to) {
        Date end, start;
        DateFormatter fmt = new DateFormatter("yyyy-MM-dd HH:mm:ss");
        try {
            end = fmt.parse(to, Locale.US);
        } catch (Exception e) {
            _logger.log(Level.WARNING, e.getMessage());
            end = new Date();
        }

        try {
            start = fmt.parse(from, Locale.US);
        } catch (Exception e) {
            _logger.log(Level.WARNING, e.getMessage());
            start = Date.from(end.toInstant().minus(24, ChronoUnit.HOURS));
        }
        return salesService.getSalesByDateBetween(start, end);
    }

    // UPDATE
    @PutMapping
    public Sale updateSale(@RequestBody Sale saleRequest) {
        return salesService.updateSale(saleRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSale(@PathVariable String id) {
        salesService.deleteSale(id);
    }
}
