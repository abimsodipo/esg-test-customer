package com.esg.test.customer.service;

import com.esg.test.customer.domain.Customer;
import com.esg.test.customer.vo.CustomerVO;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.util.List;

public interface CustomerService {

    /**
     * Add new customer
     * @param customerVO Customer to be added
     * @return Customer
     */
    ResponseEntity<Customer> addCustomer(CustomerVO customerVO);


    /**
     * Adds a list of customer
     * @param customerListVO Value object of list of customers to be added
     */
    void addCustomers(List<CustomerVO> customerListVO);

    /**
     *  Loads a CSV data into a Customer entity
     * @return Customer List
     * @throws FileNotFoundException
     */
    List<CustomerVO>  getCsvData() throws FileNotFoundException;

    /**
     * Retrieves all Customers
     * @return List of Customers
     */
    List<CustomerVO> findAllCustomers();

    /**
     * Finds a Customer by customerRef
     * @param customerRef Reference number for the Customer
     * @return CustomerDetailsVO
     */
    //CustomerDetailsVO findByCustomerRef(final String customerRef);
    CustomerVO findByCustomerRef(String customerRef);


}
