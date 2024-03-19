package com.adesso.bank.data.model.data.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public String readOneCustomer() {
        return customerRepository.retrieveOneCustomer();
    }

    public List<String> readMoreCustomer() {
        return customerRepository.retrieveMoreCustomer();
    }

}
