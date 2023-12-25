package ru.netology.alexeev_egor.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.alexeev_egor.controller.dto.OperationsDTO;
import ru.netology.alexeev_egor.controller.dto.OperationsGetResponse;
import ru.netology.alexeev_egor.domain.Customer;
import ru.netology.alexeev_egor.domain.operation.Operation;
import ru.netology.alexeev_egor.service.AsyncInputOperationService;
import ru.netology.alexeev_egor.service.CustomerService;
import ru.netology.alexeev_egor.service.StatementService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/operations/")
public class OperationsController {
    private final AsyncInputOperationService asyncInputOperationService;

    private final StatementService statementService;

    private final CustomerService customerService;

    public OperationsController(AsyncInputOperationService asyncInputOperationService, StatementService statementService, CustomerService customerService) {
        this.statementService = statementService;
        this.asyncInputOperationService = asyncInputOperationService;
        this.customerService = customerService;
    }

    @GetMapping("{id}")
    public OperationsGetResponse getOperationsByCustomerId(@PathVariable("id") int id){
        List<Operation> operations = statementService.getOperationsOnCustomerId(id);
        List<OperationsDTO> operationsDTOS = operations.stream()
                .map(operation ->
                        new OperationsDTO(operation.getCustomerId(), operation.getId(), operation.getSum(),operation.getCurrency(), operation.getMerchant())).collect(Collectors.toList());
        return new OperationsGetResponse(operationsDTOS);
    } // curl http://localhost:8080/api/operations/456

    @PostMapping()
    public OperationsDTO addOperation(int id, int sum, String currency, String merchant, int customerId, String customerName){
        customerService.addCustomer(customerId, customerName);
        Operation operation = new Operation(sum, currency, merchant, id, customerService.getCustomer(customerId));

        asyncInputOperationService.addOperation(operation);
        return new OperationsDTO(operation.getCustomerId(), operation.getId(), operation.getSum(), operation.getCurrency(), operation.getMerchant());
    } // curl -X POST -d "id=122&sum=100&currency=USD&merchant=SomeMerchant&customerId=456&customerName=CustomerName" http://localhost:8080/api/operations/

    @DeleteMapping("/delete/{id}")
    public void deleteOperation(@PathVariable("id") int id){
        statementService.removeOperation(id);
    }
    // curl -X DELETE http://localhost:8080/api/operations/delete/122
}
