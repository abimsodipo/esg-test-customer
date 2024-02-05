package com.esg.test.customer.service;


import com.esg.test.customer.vo.CustomerVO;
import com.opencsv.bean.CsvToBeanBuilder;
import com.esg.test.customer.domain.Customer;
import com.esg.test.customer.exception.CustomerNotFound;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.esg.test.customer.repositories.CustomerRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    final private ModelMapper mapper;
    final private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(final ModelMapper mapper, final CustomerRepository customerRepository) {
        this.mapper = mapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<Customer> addCustomer(final CustomerVO customerVO) {

        //Map VO to entity
        final Customer customer = mapper.map(customerVO, Customer.class);

        //Save entity
        return ResponseEntity.ok(customerRepository.save(customer));
    }

    @Override
    public void addCustomers(final List<CustomerVO> customerListVO) {
        customerListVO.forEach(this::addCustomer);
    }

    @Override
    public List<CustomerVO> getCsvData() throws FileNotFoundException {

        final String fileName = "src/main/resources/dataLoad/customer.csv";

        List<Customer> customers=  new CsvToBeanBuilder<Customer>(new FileReader(fileName))
                .withType(Customer.class)
                .build()
                .parse();

        // entity to VO
        return mapper.map(customers, new TypeToken<List<CustomerVO>>() {}.getType());
    }

    @Override
    public List<CustomerVO> findAllCustomers() {
        final List<Customer> customers = customerRepository.findAll();
        // map customers above to CustomerVO
        return mapper.map(customers, new TypeToken<List<CustomerVO>>() {}.getType());
    }

    @Override
    public CustomerVO findByCustomerRef(final String customerRef) {
        final Customer customer = customerRepository.findByCustomerRef(customerRef);
        if (null == customer) {
            throw new CustomerNotFound("Customer not found for customer ref " + customerRef);
        }
        return mapper.map(customer, CustomerVO.class);
    }

}
