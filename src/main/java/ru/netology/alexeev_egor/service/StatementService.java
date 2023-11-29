package ru.netology.alexeev_egor.service;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import ru.netology.alexeev_egor.domain.operation.Operation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class StatementService {
    private final Map<Integer, List<Operation>> storage = new HashMap<>();

    public void saveOperation(Operation operation) {

        List<Operation> operations = storage.get(operation.getCustomerId());
        if (operations == null) {

            List<Operation> customerOperations = new ArrayList<>();

            customerOperations.add(operation);

            storage.put(operation.getCustomerId(), customerOperations);
        } else {
            operations.add(operation);
        }
    }

    public String getOperations() {

        return storage.toString();
    }

    public List<Operation> getOperationOnId(int operationId){
        return storage.get(operationId);
    }

    public void removeOperationsOnCustomerId(int customerId){
        for(int id : storage.keySet()){
            if (id == customerId){
                storage.remove(id);
            }
        }
    }
}
