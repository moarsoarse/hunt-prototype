package com.hunt.worker.camunda.handler.function;

import com.hunt.common.model.Entry;

@FunctionalInterface
public interface CreateNewEntry {
  void put(Entry entry);
}
