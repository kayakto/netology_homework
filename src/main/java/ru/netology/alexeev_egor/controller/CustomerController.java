package ru.netology.alexeev_egor.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.alexeev_egor.controller.dto.CustomerDTO;
import ru.netology.alexeev_egor.controller.dto.CustomersGetResponse;
import ru.netology.alexeev_egor.domain.Customer;
import ru.netology.alexeev_egor.service.CustomerService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/customers/")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public CustomersGetResponse getCustomers(){
        List<Customer> customers = customerService.getCustomers();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(customer -> new CustomerDTO(customer.getId(), customer.getName()))
                .collect(Collectors.toList());
        return new CustomersGetResponse(customerDTOS);
    } // curl http://localhost:8080/api/customers/

    @GetMapping("{id}")
    public CustomerDTO getCustomer(@PathVariable("id") int id){
        Customer customer = customerService.getCustomer(id);
        return new CustomerDTO(customer.getId(), customer.getName());
    } // curl http://localhost:8080/api/customers/1

    @PostMapping
    public CustomerDTO addCustomer(int id, String name){
        customerService.addCustomer(id, name);
        Customer customer = customerService.getCustomer(id);
        return new CustomerDTO(customer.getId(),customer.getName());
    } // curl -X POST -d "id=3&name=Ivan" http://localhost:8080/api/customers/

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable("id") int id) {
        customerService.removeCustomer(id);
    }
    // curl -X DELETE http://localhost:8080/api/customers/delete/3
}
