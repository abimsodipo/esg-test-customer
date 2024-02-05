package com.esg.test.customer.repositories;

import com.esg.test.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Finds a customer by specified customer ref
     * @param customerRef Reference for the Customer
     * @return Customer
     */
    Customer findByCustomerRef(final String customerRef);


}
