/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crud.test.controller;

import com.mycompany.crud.test.mapper.CustomerRepository;
import com.mycompany.crud.test.models.Customer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gurame
 */

@RestController
@RequestMapping("/testapi")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepos;
    
    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerRepos.findAll();
    }
    
    @PostMapping("/customer")
    public Customer addCustomer(@RequestBody Customer customer) {
        if(customerRepos.findById(customer.getId()) == null) {
            int id = customerRepos.insert(customer);
            return customerRepos.findById(customer.getId());
        } else {
            return CustomerIdAlreadyExistException();
        }
    }
    
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getUserById(@PathVariable int id) {
        Customer customer = customerRepos.findById(id);
        return ResponseEntity.ok(customer);
    }
    
    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customerData) {
        if(customerRepos.update(new Customer(id, customerData.getName(), customerData.getEmail(), customerData.getAddress())) == 0) {
            throw new UserIdNotFound("customer not found!");
        }
    }
    
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser (@PathVariable Long id) {
        customerRepos.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("customer deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    private Customer CustomerIdAlreadyExistException() {
        throw new UnsupportedOperationException("Customer already existed"); //To change body of generated methods, choose Tools | Templates.
    }
}
