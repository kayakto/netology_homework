package ru.netology.alexeev_egor.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.alexeev_egor.OperationHistoryApiApplicationTest;
import ru.netology.alexeev_egor.domain.Customer;
import ru.netology.alexeev_egor.domain.operation.Operation;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StatementServiceTest extends OperationHistoryApiApplicationTest{
    @Autowired
    StatementService statementService;

    @Test
    @Order(1)
    public void removeOperationTest(){
        // removing operations from AssyncInputOperationsServiseTest
        statementService.removeOperation(1);
        statementService.removeOperation(2);
        assertEquals("{}", statementService.getOperations());

        Operation operation = new Operation(9874, "CNY", "poizon", 2, new Customer(2,"Vlad"));
        statementService.saveOperation(operation);
        statementService.removeOperation(2);
        assertEquals("{}", statementService.getOperations());
    }

    @Test
    @Order(2)
    public void saveOperationTest(){
        Operation operation = new Operation(100, "RUB", "molotov", 1, new Customer(1,"Mikhail"));
        statementService.saveOperation(operation);

        String operations = statementService.getOperations();
        assertEquals("{1=[Operation{sum=100, currency='RUB', merchant='molotov', id=1}]}", operations);
    }

    @Test
    @Order(3)
    public void getOperationsTest(){
        statementService.removeOperation(1); // removing the operation from the second test
        Operation operation = new Operation(9090, "GBP","Eastpak" , 3, new Customer(3,"Nikita"));
        statementService.saveOperation(operation);

        String operations = statementService.getOperations();
        assertEquals("{3=[Operation{sum=9090, currency='GBP', merchant='Eastpak', id=3}]}", operations);
    }

    @Test
    @Order(4)
    public void getOperationsOnCustomerIdTest() {
        Operation operation = new Operation(777, "GEMS","Eastpak" , 4, new Customer(7,"Vladimir"));
        statementService.saveOperation(operation);
        List<Operation> clientOperations = statementService.getOperationsOnCustomerId(7);
        assertEquals("[Operation{sum=777, currency='GEMS', merchant='Eastpak', id=4}]", clientOperations.toString());
    }
}
