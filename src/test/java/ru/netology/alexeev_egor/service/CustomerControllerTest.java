package ru.netology.alexeev_egor.service;

import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.alexeev_egor.OperationHistoryApiApplicationTest;
import ru.netology.alexeev_egor.controller.CustomerController;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import ru.netology.alexeev_egor.controller.dto.CustomerDTO;
import ru.netology.alexeev_egor.controller.dto.CustomersGetResponse;

class CustomerControllerTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private CustomerController customerController;

    @Test
    @Order(4)
    public void getClientsTest(){
        CustomersGetResponse customers = customerController.getCustomers();
        CustomerDTO customer1 = customers.getCustomers().get(0);
        CustomerDTO customer2 = customers.getCustomers().get(1);

        assertEquals(1, customer1.getId());
        assertEquals("Spring", customer1.getName());
        assertEquals(2, customer2.getId());
        assertEquals("Boot", customer2.getName());
    }

    @Test
    @Order(5)
    public void getCustomerTest() {
        CustomerDTO customer1 = customerController.getCustomer(1);
        CustomerDTO customer2 = customerController.getCustomer(2);

        assertEquals(1, customer1.getId());
        assertEquals("Spring", customer1.getName());
        assertEquals(2, customer2.getId());
        assertEquals("Boot", customer2.getName());
    }

    @Test
    @Order(6)
    public void addCustomerTest() {
        customerController.addCustomer(5, "Han");
        CustomerDTO customer5 = customerController.getCustomer(5);

        assertEquals(5, customer5.getId());
        assertEquals("Han", customer5.getName());
        customerController.deleteCustomer(5); // removing the customer for other tests
    }
}