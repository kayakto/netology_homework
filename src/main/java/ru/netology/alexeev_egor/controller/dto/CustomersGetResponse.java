package ru.netology.alexeev_egor.controller.dto;

import lombok.Data;
import java.util.List;

@Data
public class CustomersGetResponse {
    private final List<CustomerDTO> Customers;
}
