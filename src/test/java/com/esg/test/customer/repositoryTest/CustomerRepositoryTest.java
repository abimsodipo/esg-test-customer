package com.esg.test.customer.repositoryTest;

import com.esg.test.customer.domain.Customer;
import com.esg.test.customer.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void save_Customer() {
        Customer customer = Customer.builder()
                .customerName("Jack Jones")
                .customerRef("1000-03")
                .town("Egham")
                .build();

        final Customer savedCustomer = customerRepository.save(customer);

        assertThat("Customer should not be null", savedCustomer, notNullValue());
        assertThat("Id should not be null", savedCustomer.getId(), notNullValue());

    }

    @Test
    public void findByCustomerRef() {
        final String customerRef = "1000-03";

        //Create customer
        Customer customer = Customer.builder()
                .customerName("Jack Jones")
                .customerRef(customerRef)
                .town("Egham")
                .build();

        // Save customer to database
        final Customer savedCustomer = customerRepository.save(customer);

        //Retrieve data saved above
        final Customer customerFromDb = customerRepository.findByCustomerRef(customerRef);


        assertThat("Customer should not be null", customerFromDb, notNullValue());
        assertThat("Id should not be null", savedCustomer.getCustomerRef(), equalTo(customerRef));

    }

}
