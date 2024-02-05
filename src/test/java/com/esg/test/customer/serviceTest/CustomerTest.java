package com.esg.test.customer.serviceTest;

import com.esg.test.customer.domain.Customer;
import com.esg.test.customer.repositories.CustomerRepository;
import com.esg.test.customer.service.CustomerServiceImpl;
import com.esg.test.customer.vo.CustomerVO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;


import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ModelMapper mapper = new ModelMapper();
    @InjectMocks
    private CustomerServiceImpl customerService;

    private CustomerVO customerVO;
    private CustomerVO customerVO2;

    private Customer customer;
    private Customer customer2;

    @BeforeEach
    public void init() {
        customer = Customer.builder()
                .id(10L)
                .customerRef("20003-01")
                .customerName("John Walker")
                .build();

        customer2 = Customer.builder()
                .id(20L)
                .customerRef("20003-02")
                .customerName("Janet Williams")
                .build();

        customerVO = CustomerVO.builder()
                .customerRef("20003-01")
                .customerName("John Walker")
                .build();

        customerVO2 = CustomerVO.builder()
                .id(20L)
                .customerRef("20003-02")
                .customerName("Janet Williams")
                .build();

    }

    @Test
    public void findByCustomerRef() {
        when(mapper.map(eq(customer), eq(CustomerVO.class))).thenReturn(customerVO);
        when(customerRepository.findByCustomerRef(customer.getCustomerRef())).thenReturn(customer);

        final CustomerVO savedCustomerVO = customerService.findByCustomerRef(customer.getCustomerRef());

        Assertions.assertThat(savedCustomerVO.getCustomerRef()).isNotNull();
        Assertions.assertThat(savedCustomerVO.getCustomerRef()).isEqualTo(customer.getCustomerRef());
    }

    @Test
    public void addCustomer() {
        when(mapper.map(eq(customerVO), eq(Customer.class))).thenReturn(customer);
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

        final Customer savedCustomer = customerService.addCustomer(customerVO).getBody();

        Assertions.assertThat(savedCustomer).isNotNull();
        Assertions.assertThat(savedCustomer.getCustomerRef()).isEqualTo(customer.getCustomerRef());
    }

//    @Test
//    public void getCsvData() throws FileNotFoundException {
//        final List<Customer> customers = Arrays.asList(customer, customer2);
//        final List<CustomerVO> customerListVO = Arrays.asList(customerVO, customerVO2);
//
//       // when(mapper.map(anyString(), anyString())).thenReturn(customerListVO);
//     //   when(mapper.map(anyList(), eq(new TypeToken<List<CustomerVO>>() {}.getType()))).thenReturn(customerListVO);
//     //   when(mockedService.getUserById(any())).thenReturn(new User());
//      //  when(mapper.map(eq(customer), eq(Customer.class))).thenReturn(customerListVO);
//        when(mapper.map(anyList())).thenReturn(customer);
//        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
//
//        when(customerService.getCsvData()).thenReturn(customerListVO);
//
//       Assertions.assertThat(customerListVO).hasSize(2);
//    }

    //TODO
    // getCsvData
    // findAllCustomers


}
