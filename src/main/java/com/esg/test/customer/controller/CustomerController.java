package com.esg.test.customer.controller;

import com.esg.test.customer.domain.Customer;
import com.esg.test.customer.vo.CustomerVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.esg.test.customer.service.CustomerService;

import java.io.FileNotFoundException;
import java.util.List;


@RestController
@RequestMapping("esg/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //Add a new Customer
    @PostMapping("/add")
    public ResponseEntity<ResponseEntity<Customer>> addCustomer(@RequestBody CustomerVO customerVO) {
        return ResponseEntity.ok(customerService.addCustomer(customerVO));
    }

    //Add list of customer
    @PostMapping("/listCustomers")
    public void addCustomers(@RequestBody List<CustomerVO> customerListVO) {
        customerService.addCustomers(customerListVO);
    }

    //Get all customer by customer Ref
    @GetMapping("/{customerRef}")
    public CustomerVO findByCustomerRef(@PathVariable String customerRef) {
        return customerService.findByCustomerRef(customerRef);
    }

    //Get all customers
    @GetMapping("/")
    public List<CustomerVO> findAll() {
        return customerService.findAllCustomers();
    }

    // Load data from CSV file
    @GetMapping("/csvData")
    public List<CustomerVO> getCsvData() throws FileNotFoundException {
        return customerService.getCsvData();
    }


    }
