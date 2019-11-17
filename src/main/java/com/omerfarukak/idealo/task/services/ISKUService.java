package com.omerfarukak.idealo.task.services;

import com.omerfarukak.idealo.task.SKU;
import java.util.Collection;

public interface ISKUService {
  Collection<SKU> getAllSKUs();
  SKU findSKUByName(String name);
}
