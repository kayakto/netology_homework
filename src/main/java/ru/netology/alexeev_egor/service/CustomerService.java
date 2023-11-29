package ru.netology.alexeev_egor.service;

import org.springframework.stereotype.Component;
import ru.netology.alexeev_egor.domain.Customer;
import javax.annotation.PostConstruct;

import java.util.List;

@Component
public class CustomerService {
    private final List<Customer> storage;

    public CustomerService(List<Customer> storage) {
        this.storage = storage;
    }

    public List<Customer> getCustomers() {
        return storage;
    }

    public Customer getCustomer(int customerId) {
        return storage.get(customerId);
    }

    public void addUser(int id, String name){
        storage.add(new Customer(id, name));
    }

    @PostConstruct
    public void init(){
        storage.add(new Customer(1, "Spring"));
        storage.add(new Customer(2, "Boot"));
    }
}
