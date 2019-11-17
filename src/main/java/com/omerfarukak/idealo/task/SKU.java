package com.omerfarukak.idealo.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SKU {
  String name;
  @EqualsAndHashCode.Exclude int unitPrice;
}
