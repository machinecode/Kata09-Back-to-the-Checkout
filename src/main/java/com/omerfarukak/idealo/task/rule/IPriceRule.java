package com.omerfarukak.idealo.task.rule;

import com.omerfarukak.idealo.task.Basket;

public interface IPriceRule {
  int calculateDiscountToTotalPrice(Basket basket);
}
