package org.example;

import java.io.IOException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
        CurrencyParser parser = new CurrencyParser();
        Currency valuteresult = parser.ValuteCource(new Date(2002, 02, 03), "USD");
        System.out.println(valuteresult.getId() + "(" + valuteresult.getName() + ")" + valuteresult.getValue());
    }
}
