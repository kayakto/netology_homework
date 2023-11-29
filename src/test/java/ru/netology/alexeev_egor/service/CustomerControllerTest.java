package ru.netology.alexeev_egor.service;

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
    public void getClientsTest(){
        CustomersGetResponse customers = customerController.getCustomers();
        CustomerDTO customer1 = customers.getCustomers().get(0);
        CustomerDTO customer2 = customers.getCustomers().get(1);

        assertEquals(1, customer1.getId());
        assertEquals("Spring", customer1.getName());
        assertEquals(2, customer2.getId());
        assertEquals("Boot", customer2.getName());
    }
}