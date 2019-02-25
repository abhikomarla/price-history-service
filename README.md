This is a Springboot application which exposes 3 rest endpoints.

1. POST /priceHistory/add                   ::: To add data 
2. GET  /priceHistory/YYYYMMDD              ::: To retrieve data of a day
3. GET  /priceHistory/YYYYMMDD/bestbet      ::: To fins the best possible profit

This application connects to DynamnoDB for data interactions

Sample request data for POST /priceHistory/add

[
	{
		"currency": "LTC",
		"currencyDate": "20180507",
		"time": "0930",
		"price": 14.32
	},
	{
		"currency": "LTC",
		"currencyDate": "20180507",
		"time": "1115",
		"price": 14.87
	},
	{
		"currency": "LTC",
		"currencyDate": "20180507",
		"time": "1245",
		"price": 15.03
	},
	{
		"currency": "LTC",
		"currencyDate": "20180507",
		"time": "1400",
		"price": 14.76
	},
	{
		"currency": "LTC",
		"currencyDate": "20180507",
		"time": "1700",
		"price": 14.15
	}
]

Assumptions
- The best profit can only be calculated if there are at least two entries for a currency in a day
- Otherwise the profit will be calculated as 0  


Sample response for the best bet calculation endpoint GET /priceHistory/YYYYMMDD/bestbet

{
    "status": "OK",
    "statusDescription": "Success",
    "data": [
        {
            "currency": "BTC",
            "exchangeDate": "20180507",
            "purchaseTime": "0915",
            "sellTime": "1230",
            "profit": 2.03
        },
        {
            "currency": "ETC",
            "exchangeDate": "20180507",
            "purchaseTime": "1245",
            "sellTime": "1700",
            "profit": 0.6
        },
        {
            "currency": "LTC",
            "exchangeDate": "20180507",
            "purchaseTime": "0930",
            "sellTime": "1245",
            "profit": 0.71
        }
    ]
}

Sample error response
{
    "status": "BAD_REQUEST",
    "statusDescription": "Not enough data for the day",
    "data": null
}