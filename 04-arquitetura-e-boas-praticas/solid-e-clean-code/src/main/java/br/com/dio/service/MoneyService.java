package br.com.dio.service;

import br.com.dio.dao.MoneyDAO;

import java.math.BigDecimal;

public class MoneyService {

    private final MoneyDAO dao;

    public MoneyService(MoneyDAO dao) {
        this.dao = dao;
    }

    public BigDecimal add(final BigDecimal money){
        return dao.add(money);
    }

    public BigDecimal dec(final BigDecimal money){
        return dao.add(money.multiply(new BigDecimal("-1")));
    }

    public BigDecimal getMoney(){
        return dao.getMoney();
    }
}
