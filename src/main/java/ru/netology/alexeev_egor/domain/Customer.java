package ru.netology.alexeev_egor.domain;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Customer implements ConsolePrintable{
    private int id;
    private String name;

    @Override
    public void printToConsole() {
        System.out.println(id + name);
    }
}

