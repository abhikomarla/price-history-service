package com.abhijith.test.helper;

import com.abhijith.test.entity.PriceHistory;
import com.abhijith.test.entity.ProcessItem;
import com.abhijith.test.entity.ResponseElement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class BestBetCalculator {

    public static List<ResponseElement> find(final List<PriceHistory> historyList) {

        System.out.println("Trying to find the best bet");

        System.out.println("Trying to categorize the data of the day based on crypto currency");
        Map<String, List<PriceHistory>> stringListMap = new HashMap<>();
        List<ResponseElement> response = new ArrayList<>();

        //separate all entries of the day based on the crypto currency
        for (PriceHistory h : historyList) {
            List<PriceHistory> list = stringListMap.get(h.getCurrency());
            if (null == list) {
                list = new ArrayList<>();
                stringListMap.put(h.getCurrency(), list);
            }
            list.add(h);
        }
        System.out.println("Categorizing the data of the day based on crypto currency is done");
        Iterator<Map.Entry<String, List<PriceHistory>>> it = stringListMap.entrySet().iterator();
        while (it.hasNext()) {

            System.out.println("Sorting the data by time");
            Map.Entry<String, List<PriceHistory>> pair = it.next();

            //sort the list based on time
            List<PriceHistory> currencyList = pair.getValue().stream().sorted(
                    (o1, o2)-> Integer.compare(Integer.parseInt(o1.getTime()), Integer.parseInt(o2.getTime()))).
                    collect(Collectors.toList());

            //process each currency to find the best possible outcome
            System.out.println("Now executing the best bet");
            ResponseElement responseElement = processCurrency(currencyList);
            response.add(responseElement);
        }
        return response;
    }

    private static ResponseElement processCurrency(final List<PriceHistory> currencyList) {

        ResponseElement responseElement = new ResponseElement();
        responseElement.setCurrency(currencyList.get(0).getCurrency());
        responseElement.setExchangeDate(currencyList.get(0).getCurrencyDate());

        if (null != currencyList && currencyList.size() >= 2) {

            List<ProcessItem> processItems = new ArrayList<>();
            for (int index1 = currencyList.size() - 1; index1 > 0; index1--) {
                PriceHistory itemBig = currencyList.get(index1);
                for (int index2 = index1 - 1; index2 >= 0; index2--) {
                    PriceHistory itemSmall = currencyList.get(index2);
                    ProcessItem processItem = new ProcessItem(itemSmall.getTime(), itemBig.getTime(),
                            itemBig.getPrice().subtract(itemSmall.getPrice()));
                    processItems.add(processItem);
                }
            }

            try {
                ProcessItem item = processItems.
                        stream().
                        max(Comparator.comparing(ProcessItem::getProfit)).
                        orElseThrow(NoSuchElementException::new);

                System.out.println("Best profit found by the algorithm :: " + item.getProfit());

                if (item.getProfit().doubleValue() > 0) {
                    responseElement.setPurchaseTime(item.getPurchaseTime());
                    responseElement.setSellTime(item.getSellTime());
                    responseElement.setProfit(item.getProfit());
                }
            } catch (NoSuchElementException nse) {
                System.out.println("No such element as profit");
            }
        }

        return responseElement;
    }
}
