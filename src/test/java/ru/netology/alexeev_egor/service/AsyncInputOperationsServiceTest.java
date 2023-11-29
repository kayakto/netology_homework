package ru.netology.alexeev_egor.service;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.netology.alexeev_egor.OperationHistoryApiApplicationTest;
import ru.netology.alexeev_egor.domain.Customer;
import ru.netology.alexeev_egor.domain.operation.Operation;
import java.util.Queue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AsyncInputOperationsServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private AsyncInputOperationService asyncInputOperationService;

    @Value("${operation.processing.sleepMilliSeconds}")
    public int sleepMilliSeconds;

    /**
     * checks that the process has started
     * and that a thread has started to process the queue
     */
    @Test
    @Order(1)
    public void startProcessingTest() {
        asyncInputOperationService.startProcessing();
        assertTrue(asyncInputOperationService.getOperations().isEmpty());
    }

    @Test
    @Order(2)
    public void getOperationsTest(){
        Operation operation = new Operation(18405, "EUR", "Sugar", 2, new Customer(2,"Maxim"));
        asyncInputOperationService.addOperation(operation);
        Queue<Operation> operations = asyncInputOperationService.getOperations();
        assertEquals("[Operation{sum=18405, currency='EUR', merchant='Sugar', id=2}]", operations.toString());
    }

    @Test
    @Order(3)
    public void addOperationTest(){
        Operation operation = new Operation(9999, "RUB", "Tea shop",1 , new Customer(1,"Mikhail"));
        int sizeBefore = asyncInputOperationService.getOperations().size();
        asyncInputOperationService.addOperation(operation);
        int sizeAfter = asyncInputOperationService.getOperations().size();
        assertEquals(sizeBefore + 1, sizeAfter);
    }
}
