package com.omerfarukak.idealo.task;

import com.omerfarukak.idealo.task.rule.IPriceRule;
import com.omerfarukak.idealo.task.services.ISKUService;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckOut {

  private final ISKUService skuService;
  // because of this is a console application, setting an attribute in a component won't affect us.
  @Setter
  @Getter
  private Collection<IPriceRule> rules;
  // again having only one basket is not a problem in our case. For multi user applications we may define it as session scope
  private Basket basket;

  @Autowired
  public CheckOut(ISKUService skuService) {
    this.skuService = skuService;
    this.basket = new Basket();
  }

  public void scan(String good) {
    SKU scannedSKU = skuService.findSKUByName(good);
    if (scannedSKU != null) {
      basket.put(scannedSKU, basket.containsKey(scannedSKU) ? basket.get(scannedSKU) + 1 : 1);
    }
  }

  public int total() {
    int total = 0;
    // sum all basket
    total = basket.entrySet().stream().reduce(total,
        (subtotal, item) -> subtotal + (item.getValue() * item.getKey().getUnitPrice()),
        Integer::sum);
    // apply rules
    total = rules.stream()
        .reduce(total, (subtotal, rule) -> subtotal - rule.calculateDiscountToTotalPrice(basket),
            Integer::sum);
    return total;
  }

  public void emptyBasket() {
    this.basket = new Basket();
  }
}
