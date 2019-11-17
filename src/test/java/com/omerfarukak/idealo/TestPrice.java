package com.omerfarukak.idealo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.omerfarukak.idealo.task.CheckOut;
import com.omerfarukak.idealo.task.rule.DiscountToA;
import com.omerfarukak.idealo.task.rule.DiscountToB;
import com.omerfarukak.idealo.task.rule.IPriceRule;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestPrice {

  @Autowired
  CheckOut co;

  int calculatePrice(String goods) {
    Collection<IPriceRule> rules = new ArrayList<>();
    rules.add(new DiscountToA());
    rules.add(new DiscountToB());
    co.setRules(rules);
    for (int i = 0; i < goods.length(); i++) {
      co.scan(String.valueOf(goods.charAt(i)));
    }
    int result = co.total();
    co.emptyBasket();
    return result;
  }

  @Test
  void totals() {
    assertEquals(0, calculatePrice(""));
    assertEquals(40, calculatePrice("A"));
    assertEquals(90, calculatePrice("AB"));
    assertEquals(135, calculatePrice("CDBA"));
    assertEquals(80, calculatePrice("AA"));
    assertEquals(100, calculatePrice("AAA"));
    assertEquals(140, calculatePrice("AAAA"));
    assertEquals(180, calculatePrice("AAAAA"));
    assertEquals(200, calculatePrice("AAAAAA"));
    assertEquals(150, calculatePrice("AAAB"));
    assertEquals(180, calculatePrice("AAABB"));
    assertEquals(200, calculatePrice("AAABBD"));
    assertEquals(200, calculatePrice("DABABA"));
  }

  @Test
  void incremental() {
    Collection<IPriceRule> rules = new ArrayList<>();
    rules.add(new DiscountToA());
    rules.add(new DiscountToB());
    co.setRules(rules);
    assertEquals(0, co.total());
    co.scan("A");
    assertEquals(40, co.total());
    co.scan("B");
    assertEquals(90, co.total());
    co.scan("A");
    assertEquals(130, co.total());
    co.scan("A");
    assertEquals(150, co.total());
    co.scan("B");
    assertEquals(180, co.total());
  }
}
