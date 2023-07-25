package org.example;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CurrencyParser {

    private String urlstring = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=";

    // установка соединения
    public InputStream getconnectioninputstream(Date date) throws IOException {
        date.setYear(date.getYear() - 1900);
        SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
        String dateform = dateformat.format(date);
        System.out.println(dateform);
        urlstring += dateform;
        URL url = new URL(urlstring);
        URLConnection connection = url.openConnection();
        InputStream input = connection.getInputStream();
        return input;
    }

    // вывод всех курсов валют на определенную дату
    public ArrayList<Currency> parseCurrency(Date date) throws IOException {

        InputStream input = getconnectioninputstream(date);
        // получение информации о курсах
        ArrayList<Currency> Valutes = new ArrayList<Currency>();
        try (BufferedReader datareader = new BufferedReader(new InputStreamReader(input))) {
            String line = datareader.readLine();
            System.out.println(line);

            while (line.indexOf("/Valute") != -1) {
                String currencyId = line.substring(line.indexOf("<CharCode>") + 10, line.indexOf("</CharCode>"));
                String currencyName = line.substring(line.indexOf("<Name>") + 6, line.indexOf("</Name>"));
                String currencyValue = line.substring(line.indexOf("<Value>") + 7, line.indexOf("</Value>"));
                Currency valute = new Currency(currencyId, currencyName, currencyValue);
                Valutes.add(valute);
            }

        }
        return Valutes;
    }

    public Currency ValuteCource(Date date, String currencyId) throws IOException {
        InputStream input = getconnectioninputstream(date);
        Currency valute = new Currency();
        try (BufferedReader datareader = new BufferedReader(new InputStreamReader(input))) {
            String line = datareader.readLine();
            System.out.println(line);
            if (line.indexOf(currencyId) != -1) {
                line = line.substring(line.indexOf(currencyId));
                String currencyName = line.substring(line.indexOf("<Name>") + 6, line.indexOf("</Name>"));
                String currencyValue = line.substring(line.indexOf("<Value>") + 7, line.indexOf("</Value>"));
                valute = new Currency(currencyId, currencyName, currencyValue);
                return valute;
            } else {
                System.out.println("неверный формат данных");

            }

        }
        return valute;
    }
}