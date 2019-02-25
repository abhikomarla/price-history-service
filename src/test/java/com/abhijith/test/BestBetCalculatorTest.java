package com.abhijith.test;

import com.abhijith.test.entity.PriceHistory;
import com.abhijith.test.entity.ResponseElement;
import com.abhijith.test.helper.BestBetCalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class BestBetCalculatorTest {

    @Test
    public void test_scenario1() {
        List<PriceHistory> input = new ArrayList<>();

        PriceHistory price1 = new PriceHistory("BTC", "20180507",
                "0915", new BigDecimal(34.98));
        input.add(price1);
        PriceHistory price2 = new PriceHistory("BTC", "20180507",
                "1045", new BigDecimal(36.13));
        input.add(price2);
        PriceHistory price3 = new PriceHistory("BTC", "20180507",
                "1230", new BigDecimal(37.01));
        input.add(price3);
        PriceHistory price4 = new PriceHistory("BTC", "20180507",
                "1400", new BigDecimal(35.98));
        input.add(price4);
        PriceHistory price5 = new PriceHistory("BTC", "20180507",
                "1530", new BigDecimal(33.56));
        input.add(price5);
        List<ResponseElement> response = BestBetCalculator.find(input);
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(2.03,response.get(0).getProfit().doubleValue(), 0.001);

    }

    @Test
    public void test_scenario2() {
        List<PriceHistory> input = new ArrayList<>();

        PriceHistory price1 = new PriceHistory("BTC", "20180507",
                "0915", new BigDecimal(100));
        input.add(price1);
        PriceHistory price2 = new PriceHistory("BTC", "20180507",
                "1045", new BigDecimal(35));
        input.add(price2);
        PriceHistory price3 = new PriceHistory("BTC", "20180507",
                "1230", new BigDecimal(65));
        input.add(price3);
        List<ResponseElement> response = BestBetCalculator.find(input);
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(30,response.get(0).getProfit().doubleValue(), 0.001);

    }

    @Test
    public void test_scenario3() {
        List<PriceHistory> input = new ArrayList<>();

        PriceHistory price1 = new PriceHistory("BTC", "20180507",
                "0915", new BigDecimal(100));
        input.add(price1);
        PriceHistory price2 = new PriceHistory("BTC", "20180507",
                "1045", new BigDecimal(35));
        input.add(price2);
        PriceHistory price3 = new PriceHistory("BTC", "20180507",
                "1230", new BigDecimal(65));
        input.add(price3);
        PriceHistory price4 = new PriceHistory("BTC", "20180507",
                "1231", new BigDecimal(66));
        input.add(price4);
        List<ResponseElement> response = BestBetCalculator.find(input);
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(31,response.get(0).getProfit().doubleValue(), 0.001);
    }

    @Test
    public void test_scenario4() {
        List<PriceHistory> input = new ArrayList<>();

        PriceHistory price1 = new PriceHistory("BTC", "20180507",
                "0915", new BigDecimal(100));
        input.add(price1);
        PriceHistory price2 = new PriceHistory("BTC", "20180507",
                "1045", new BigDecimal(35));
        input.add(price2);
        PriceHistory price3 = new PriceHistory("BTC", "20180507",
                "1230", new BigDecimal(65));
        input.add(price3);
        PriceHistory price4 = new PriceHistory("BTC", "20180507",
                "1231", new BigDecimal(66));
        input.add(price4);
        PriceHistory price5 = new PriceHistory("BTC", "20180507",
                "1232", new BigDecimal(25));
        input.add(price5);
        PriceHistory price6 = new PriceHistory("BTC", "20180507",
                "1233", new BigDecimal(26));
        input.add(price6);

        List<ResponseElement> response = BestBetCalculator.find(input);
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(31,response.get(0).getProfit().doubleValue(), 0.001);
    }

    @Test
    public void test_scenario5() {
        List<PriceHistory> input = new ArrayList<>();

        PriceHistory price1 = new PriceHistory("BTC", "20180507",
                "0915", new BigDecimal(100));
        input.add(price1);
        PriceHistory price2 = new PriceHistory("BTC", "20180507",
                "1045", new BigDecimal(99));
        input.add(price2);
        PriceHistory price3 = new PriceHistory("BTC", "20180507",
                "1230", new BigDecimal(98));
        input.add(price3);
        PriceHistory price4 = new PriceHistory("BTC", "20180507",
                "1231", new BigDecimal(97));
        input.add(price4);
        PriceHistory price5 = new PriceHistory("BTC", "20180507",
                "1232", new BigDecimal(96));
        input.add(price5);
        PriceHistory price6 = new PriceHistory("BTC", "20180507",
                "1233", new BigDecimal(95));
        input.add(price6);

        List<ResponseElement> response = BestBetCalculator.find(input);
        assertNotNull(response);
        assertEquals(1, response.size());
        assertNull(response.get(0).getProfit());
    }

    @Test
    public void test_scenario6() {
        List<PriceHistory> input = new ArrayList<>();

        PriceHistory price1 = new PriceHistory("BTC", "20180507",
                "0915", new BigDecimal(100));
        input.add(price1);
        PriceHistory price2 = new PriceHistory("BTC", "20180507",
                "1045", new BigDecimal(110));
        input.add(price2);
        PriceHistory price3 = new PriceHistory("BTC", "20180507",
                "1230", new BigDecimal(120));
        input.add(price3);
        PriceHistory price4 = new PriceHistory("BTC", "20180507",
                "1231", new BigDecimal(130));
        input.add(price4);
        PriceHistory price5 = new PriceHistory("BTC", "20180507",
                "1232", new BigDecimal(140));
        input.add(price5);
        PriceHistory price6 = new PriceHistory("BTC", "20180507",
                "1233", new BigDecimal(150));
        input.add(price6);

        List<ResponseElement> response = BestBetCalculator.find(input);
        assertEquals(50, response.get(0).getProfit().doubleValue(),0.1);
    }

    @Test
    public void test_scenario7() {
        List<PriceHistory> input = new ArrayList<>();

        PriceHistory price1 = new PriceHistory("BTC", "20180507",
                "0915", new BigDecimal(60));
        input.add(price1);
        PriceHistory price2 = new PriceHistory("BTC", "20180507",
                "1045", new BigDecimal(36));
        input.add(price2);
        PriceHistory price3 = new PriceHistory("BTC", "20180507",
                "1230", new BigDecimal(21));
        input.add(price3);
        PriceHistory price4 = new PriceHistory("BTC", "20180507",
                "1231", new BigDecimal(50));
        input.add(price4);
        PriceHistory price5 = new PriceHistory("BTC", "20180507",
                "1232", new BigDecimal(67));
        input.add(price5);
        PriceHistory price6 = new PriceHistory("BTC", "20180507",
                "1233", new BigDecimal(30));
        input.add(price6);
        PriceHistory price7 = new PriceHistory("BTC", "20180507",
                "1333", new BigDecimal(20));
        input.add(price7);
        PriceHistory price8 = new PriceHistory("BTC", "20180507",
                "1233", new BigDecimal(82));
        input.add(price8);

        List<ResponseElement> response = BestBetCalculator.find(input);
        assertEquals(61, response.get(0).getProfit().doubleValue(),0.1);
    }

    @Test
    public void test_scenario8() {
        List<PriceHistory> input = new ArrayList<>();

        PriceHistory price1 = new PriceHistory("BTC", "20180507",
                "0915", new BigDecimal(6));
        input.add(price1);
        PriceHistory price2 = new PriceHistory("BTC", "20180507",
                "1045", new BigDecimal(36));
        input.add(price2);

        List<ResponseElement> response = BestBetCalculator.find(input);
        assertEquals(30, response.get(0).getProfit().doubleValue(),0.1);
    }
}
