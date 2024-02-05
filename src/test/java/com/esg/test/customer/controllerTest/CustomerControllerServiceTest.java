package com.esg.test.customer.controllerTest;


import com.esg.test.customer.domain.Customer;
import com.esg.test.customer.service.CustomerService;
import com.esg.test.customer.vo.CustomerVO;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ComponentScan(basePackages = "com.esg.test.customer.controller")
@WebMvcTest(controllers = Customer.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class CustomerControllerServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private CustomerVO customerVO;

    private Customer customer;

    private Customer customer2;

    private List<CustomerVO> customerVOList;

    @BeforeEach
    public void init() {
        customer = Customer.builder().id(10L).customerRef("20003-01").customerName("John Walker").addressLine1("10 Willow Street").build();
        customer2 = Customer.builder().id(20L).customerRef("20003-02").customerName("Janet Williams").addressLine1("10 Willow Street").build();
        customerVO = CustomerVO.builder().customerRef("20003-01").customerName("John Walker").addressLine1("10 Willow Street").build();
        CustomerVO customerVO2 = CustomerVO.builder().customerRef("20003-02").customerName("Janet Williams").addressLine1("10 Willow Street").build();
        customerVOList = Arrays.asList(customerVO, customerVO2);

    }


    @Test
    public void findCustomerByCustomerRef() throws Exception {
        when(customerService.findByCustomerRef(customer.getCustomerRef())).thenReturn(customerVO);

        ResultActions response = mockMvc.perform(get("/esg/customer/" +customerVO.getCustomerRef())
                .accept(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerRef", CoreMatchers.is(customerVO.getCustomerRef())));
    }

    @Test
    public void getCsvData() throws Exception {
        when(customerService.getCsvData()).thenReturn(customerVOList);

        ResultActions response = mockMvc.perform(get("/esg/customer/csvData")
                .accept(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
         .andExpect(MockMvcResultMatchers.jsonPath("$[0].addressLine1",
                        CoreMatchers.is(customerVO.getAddressLine1())));

        // In the above test addressLine1 is the same for both records, regardless of which record is displayed first,
        // the vale will still be same. Ideally a sort should have been done

    }

}
