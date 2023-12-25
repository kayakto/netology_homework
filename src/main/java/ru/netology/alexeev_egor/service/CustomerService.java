package ru.netology.alexeev_egor.service;

import org.springframework.stereotype.Component;
import ru.netology.alexeev_egor.domain.Customer;
import javax.annotation.PostConstruct;
import java.util.*;

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

        for (Customer customer : storage) {

            if (customer.getId() == customerId){
                return customer;
            }
        }
        return null;
    }

    public void addCustomer(int customerId, String name){
        Customer customer = new Customer(customerId, name);

        if (storage.contains(customer)) {
            return;
        }

        storage.add(new Customer(customerId, name));
    }

    public void removeCustomer(int customerId) {
        Iterator<Customer> customerIterator = storage.iterator();

        while (customerIterator.hasNext()) {
            Customer customer = customerIterator.next();

            if (customer.getId() == customerId) {
                customerIterator.remove();
            }
        }
    }

    @PostConstruct
    public void init(){
        storage.add(new Customer(1, "Spring"));
        storage.add(new Customer(2, "Boot"));
    }
}
