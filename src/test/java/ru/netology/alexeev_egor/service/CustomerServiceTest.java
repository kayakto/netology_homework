package ru.netology.alexeev_egor.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
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
        assertEquals(2, customers.size());
    }

    @Test
    @Order(2)
    public void getCustomerTest() {
        Customer customer1 = customerService.getCustomer(1);
        assertEquals(1, customer1.getId());
        assertEquals("Spring", customer1.getName());

        Customer customer2 = customerService.getCustomer(2);
        assertEquals(2, customer2.getId());
        assertEquals("Boot", customer2.getName());
    }

    @Test
    @Order(3)
    public void addCustomerTest(){
        customerService.addCustomer(3, "Egor");
        List<Customer> customers = customerService.getCustomers();
        Customer customer = customers.get(customers.size()-1);
        assertEquals("Egor", customer.getName());
    }

    @Test
    @Order(4)
    public void removeCustomerTest(){
        List<Customer> customers = customerService.getCustomers();
        int sizeBefore = customers.size();
        customerService.addCustomer(4, "Mikhail");
        int sizeAfter = customers.size();
        assertEquals(sizeBefore + 1, sizeAfter);
    }
}
