package com.abhijith.test.controller;

import com.abhijith.test.dao.PriceHistoryDAO;
import com.abhijith.test.entity.ResponseElement;
import com.abhijith.test.helper.BestBetCalculator;
import com.abhijith.test.entity.PriceHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceHistoryController {

    @Autowired
    private PriceHistoryDAO dao;

    @GetMapping(value="/priceHistory/{date}")
    public @ResponseBody
    List<PriceHistory> findByDate(@PathVariable String date) {
        return dao.findByDate(date);
    }

    @PostMapping(value="/priceHistory/add", headers="Accept=application/json")
    public @ResponseBody
    ResponseEntity addCatalogEntry(@RequestBody List<PriceHistory> priceHistories) {
        dao.save(priceHistories);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/priceHistory/{date}/bestbet")
    public List<ResponseElement> bestbet(@PathVariable String date) {
        List<PriceHistory> histories = dao.findByDate(date);
        return BestBetCalculator.find(histories);
    }

}
