package com.example.demo.currencyConvert;

import java.math.BigDecimal;

public interface CurrencyConverter {
    BigDecimal getCurrencyValue(String fromCurrency, String toCurrency);
}
