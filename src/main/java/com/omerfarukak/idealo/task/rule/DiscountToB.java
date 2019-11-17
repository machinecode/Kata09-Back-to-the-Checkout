package com.omerfarukak.idealo.task.rule;

import com.omerfarukak.idealo.task.Basket;
import com.omerfarukak.idealo.task.SKU;

public class DiscountToB implements IPriceRule {

  @Override
  public int calculateDiscountToTotalPrice(Basket basket) {
    int discountAmount = 20;
    int discountQuantity = 2;

    SKU sampleSKU = new SKU();
    sampleSKU.setName("B");
    int availableDiscount =  (int) Math.floor((double)basket.getOrDefault(sampleSKU, 0)  / discountQuantity);
    return availableDiscount * discountAmount;
  }
}
