package com.hunt.worker.common.functions.handlers;

import java.util.function.Function;

@FunctionalInterface
public interface Before<T, R> extends Function<T, R> {

}
