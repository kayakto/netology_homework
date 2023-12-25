package ru.netology.alexeev_egor.service;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import ru.netology.alexeev_egor.domain.operation.Operation;
import java.util.*;

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

    public List<Operation> getOperationsOnCustomerId(int customerId){
        return storage.get(customerId);
    }

    public void removeOperation(int operationId) {
        Iterator<Map.Entry<Integer, List<Operation>>> storageIterator = storage.entrySet().iterator();

        while (storageIterator.hasNext()) {

            Map.Entry<Integer, List<Operation>> customerOperations = storageIterator.next();
            List<Operation> operations = customerOperations.getValue();

            for (Iterator<Operation> operationIterator = operations.iterator(); operationIterator.hasNext(); ) {
                Operation operation = operationIterator.next();

                if (operation.getId() == operationId) {
                    operationIterator.remove();
                    if (operations.isEmpty()) {
                        storageIterator.remove();
                    }
                }
            }
        }
    }
}
