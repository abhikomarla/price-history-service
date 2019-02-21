package com.abhijith.test.helper;

import com.abhijith.test.entity.PriceHistory;
import com.abhijith.test.entity.ResponseElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BestBetCalculator {

    private static final Comparator<PriceHistory> comparator = Comparator.comparing( PriceHistory::getPrice);

    public static List<ResponseElement> find(List<PriceHistory> historyList) {

        Map<String, List<PriceHistory>> stringListMap = new HashMap<>();
        List<ResponseElement> response = new ArrayList<>();

        //prepare a map of
        for (PriceHistory h : historyList) {
            List<PriceHistory> list = stringListMap.get(h.getCurrency());
            if (null == list) {
                list = new ArrayList<>();
                stringListMap.put(h.getCurrency(), list);
            }
            list.add(h);
        }

        Iterator<Map.Entry<String,List<PriceHistory>>> it = stringListMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, List<PriceHistory>> pair = it.next();

            //sort the list based on time
            List<PriceHistory> currencyList = pair.getValue().stream().sorted(
                    (o1, o2)-> Integer.compare(Integer.parseInt(o1.getTime()), Integer.parseInt(o2.getTime()))).
                    collect(Collectors.toList());

            //process each currency
            ResponseElement responseElement = processCurrency(currencyList);
            response.add(responseElement);
        }
        return response;
    }

    private static ResponseElement processCurrency(List<PriceHistory> currencyList) {

        if (currencyList.size() > 2) {
            // Get Min or Max Object
            PriceHistory minObject = currencyList.stream().min(comparator).get();
            PriceHistory maxObject = currencyList.stream().max(comparator).get();

            // check if the combination is the best
            // if min buy out time is earlier than the maximum buy out time, then that is the best bet
            // otherwise remove one item from the list
            if (Integer.parseInt(minObject.getTime()) > Integer.parseInt(maxObject.getTime())) {
                // minimum and maximum amount are not in order... remove one from the list
                if (currencyList.indexOf(minObject) == currencyList.size()-1) {
                    currencyList.remove(minObject);
                } else if (currencyList.indexOf(maxObject) == 0) {
                    currencyList.remove(maxObject);
                } else {
                    currencyList.remove(maxObject);
                }

                return processCurrency(currencyList);
            } else {
                ResponseElement element = new ResponseElement();
                element.setCurrency(minObject.getCurrency());
                element.setExchangeDate(minObject.getCurrencyDate());
                element.setMinimumPrice(minObject.getPrice());
                element.setMaximumPrice(maxObject.getPrice());
                element.setProfit(maxObject.getPrice().subtract(minObject.getPrice()));
                return element;
            }
        } else {
            ResponseElement element = new ResponseElement();
            element.setCurrency(currencyList.get(0).getCurrency());
            element.setExchangeDate(currencyList.get(0).getCurrencyDate());
            return element;
        }
    }
}
