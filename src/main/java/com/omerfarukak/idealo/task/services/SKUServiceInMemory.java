package com.omerfarukak.idealo.task.services;

import com.omerfarukak.idealo.task.SKU;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class SKUServiceInMemory implements ISKUService {

  private static final Set<SKU> skuSet = new HashSet<>();

  static {
    skuSet.add(new SKU("A", 40));
    skuSet.add(new SKU("B", 50));
    skuSet.add(new SKU("C", 25));
    skuSet.add(new SKU("D", 20));
  }


  @Override
  public Collection<SKU> getAllSKUs() {
    return skuSet;
  }

  @Override
  public SKU findSKUByName(String name) {
    return skuSet.stream().filter(sku -> sku.getName().compareToIgnoreCase(name) == 0).findAny()
        .orElse(null);
  }
}
