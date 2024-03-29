package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

class InfoCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");

    @Override
    public void execute() {
        {
            ConsoleHelper.writeMessage(res.getString("before"));
            boolean money = false;
            for (CurrencyManipulator cur : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
                if (cur.hasMoney()) {
                    if (cur.getTotalAmount() > 0) {
                        ConsoleHelper.writeMessage(cur.getCurrencyCode() + " - " + cur.getTotalAmount());
                        money = true;
                    }
                }
            }
            if (!money) {
                ConsoleHelper.writeMessage(res.getString("no.money"));
            }
        }
    }
}
