package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String str = "";
        try {
            str = bis.readLine();
            if (str.equalsIgnoreCase("EXIT")) {
                throw new InterruptOperationException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage("Введите код валюты");
        String code;
        while (true) {
            code = readString();
            if (code.length() != 3) writeMessage("Вы ввели неверный код");
            else break;
        }
        return code.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] cash;
        while (true) {
            writeMessage("Пожалуйста введите два целых положительных числа!");

            try {
                cash = readString().split(" ");
                if (Integer.parseInt(cash[0]) > 0 && Integer.parseInt(cash[1]) > 0 && cash.length == 2) break;
            } catch (Exception e) {
                writeMessage("Код должен содержать 2 положительных числа!");
                continue;
            }
            writeMessage("Код должен содержать 2 положительных числа!");

        }
        return cash;
    }

    public static Operation askOperation() throws InterruptOperationException {
        try {
            return Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
        } catch (IllegalArgumentException e) {
            writeMessage("Incorrect operation. Try again");
            return askOperation();
        }
    }


    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
}


