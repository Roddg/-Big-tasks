package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        this.denominations = new HashMap<>();
    }

    public void addAmount(int denomination, int count) {
        denominations.put(denomination, denominations.getOrDefault(denomination, 0) + count);
    }

    public int getTotalAmount(){
        int sum = 0;
        for (Map.Entry e: denominations.entrySet()
        ) {
            sum += ((Integer) e.getKey())* ((Integer) e.getValue());
        }
        return sum;
    }

    public boolean hasMoney(){
        return denominations.size() > 0 && getTotalAmount() > 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        int sum = expectedAmount;
        HashMap<Integer, Integer> temp = new HashMap<>();
        temp.putAll(denominations);
        ArrayList<Integer> nominals = new ArrayList<>();
        for (Map.Entry<Integer, Integer> pair : temp.entrySet())
            nominals.add(pair.getKey());

        Collections.sort(nominals);
        Collections.reverse(nominals);

        TreeMap<Integer, Integer> result = new TreeMap<>(
                new Comparator<Integer>()
                {
                    @Override
                    public int compare(Integer o1, Integer o2)
                    {
                        return o2.compareTo(o1);
                    }
                });

        for (Integer nominal : nominals) {
            int key = nominal;
            int value = temp.get(key);
            while (true) {
                if (sum < key || value <= 0) {
                    temp.put(key, value);
                    break;
                }
                sum -= key;
                value--;

                if (result.containsKey(key))
                    result.put(key, result.get(key) + 1);
                else
                    result.put(key, 1);
            }
        }

        if (sum > 0)
            throw new NotEnoughMoneyException();
        else
        {

            denominations.clear();
            denominations.putAll(temp);

        }
        return result;
    }
}
