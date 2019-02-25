package com.abhijith.test.controller;

import com.abhijith.test.dao.PriceHistoryDAO;
import com.abhijith.test.entity.ResponseElement;
import com.abhijith.test.entity.ResponseObject;
import com.abhijith.test.helper.BestBetCalculator;
import com.abhijith.test.entity.PriceHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceHistoryController {

    @Autowired
    private PriceHistoryDAO dao;
    private static final int MINIMUM_REQUIRED_ENTRIES = 2;

    @GetMapping(value = "/priceHistory/{date}")
    public List<PriceHistory> findByDate(@PathVariable final String date) {
        return dao.findByDate(date);
    }

    @PostMapping(value = "/priceHistory/add", headers = "Accept=application/json")
    public ResponseEntity addEntry(@RequestBody final List<PriceHistory> priceHistories) {
        dao.save(priceHistories);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/priceHistory/{date}/bestbet")
    public ResponseObject bestBet(@PathVariable final String date) {
        List<PriceHistory> histories = dao.findByDate(date);
        ResponseObject responseObject = new ResponseObject();

        if (null == histories || histories.size() <= MINIMUM_REQUIRED_ENTRIES) {
            responseObject.setStatus(HttpStatus.BAD_REQUEST);
            responseObject.setStatusDescription("Not enough data for the day");
            System.out.println("Not enough data for the day");
        } else {
            try {
                List<ResponseElement> response = BestBetCalculator.find(histories);
                responseObject.setData(response);
                responseObject.setStatus(HttpStatus.OK);
                responseObject.setStatusDescription("Success");
            } catch (Exception e) {
                e.printStackTrace();
                responseObject.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                responseObject.setStatusDescription(e.getMessage());
            }
        }

        return responseObject;
    }

}
