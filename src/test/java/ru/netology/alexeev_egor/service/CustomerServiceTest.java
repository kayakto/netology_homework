package ru.netology.alexeev_egor.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.alexeev_egor.OperationHistoryApiApplicationTest;
import ru.netology.alexeev_egor.domain.Customer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    CustomerService customerService;

    @Test
    @Order(1)
    public void getCustomersTest(){
        List<Customer> customers = customerService.getCustomers();
        Customer customer1 = customers.get(0);
        Customer customer2 = customers.get(1);
        assertEquals(1, customer1.getId());
        assertEquals("Spring", customer1.getName());
        assertEquals(2, customer2.getId());
        assertEquals("Boot", customer2.getName());
        assertEquals(2,customers.size());
    }

    @Test
    @Order(2)
    public void addCustomerTest(){
        customerService.addUser(3, "Egor");
        List<Customer> customers = customerService.getCustomers();
        Customer customer = customers.get(customers.size()-1);
        assertEquals("Egor", customer.getName());
    }
}
