package com.Ayman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.support.NullValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SpringBootApplication
@RequestMapping("api/v1/customers")

public class Main {
    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main (String [] args){
        SpringApplication.run(Main.class, args);
    }
    private final CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ){}
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);


    }
    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer  id){
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@RequestBody NewCustomerRequest request,@PathVariable("customerId") Integer  id){
        Customer customer = customerRepository.findById(id).get();
        if (request.name()!=null){
            customer.setName(request.name());
        }
        if (request.email()!=null){
            customer.setEmail(request.email());
        }
        if (request.age()!=null){
            customer.setAge(request.age());
        }

        customerRepository.save(customer);
    }


}
