package com.abhijith.test.controller;

import com.abhijith.test.dao.PriceHistoryDAO;
import com.abhijith.test.helper.BestBetCalculator;
import com.abhijith.test.entity.PriceHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceHistoryController {

    @Autowired
    private PriceHistoryDAO dao;

    @RequestMapping(value="/priceHistory/{date}", method= RequestMethod.GET)
    public @ResponseBody
    List<PriceHistory> findByDate(@PathVariable String date) {
        return dao.findByDate(date);
    }

    @RequestMapping(value="/priceHistory/add", method=RequestMethod.POST, headers="Accept=application/json")
    public @ResponseBody String addCatalogEntry(@RequestBody List<PriceHistory> priceHistories) {

        dao.save(priceHistories);
        return "success";
    }

    @RequestMapping(value = "/priceHistory/{date}/bestbet", method=RequestMethod.GET)
    public void bestbet(@PathVariable String date) {
        List<PriceHistory> histories = dao.findByDate(date);
        BestBetCalculator.find(histories);
    }

}
