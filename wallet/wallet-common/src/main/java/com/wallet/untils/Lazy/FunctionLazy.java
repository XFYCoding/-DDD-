package com.wallet.untils.Lazy;

import java.util.function.Function;

public class FunctionLazy<T, R> extends BaseLazy<R> {
    private final Function<T, R> function;
    private final T arg;

    FunctionLazy(Function<T, R> function, T arg) {
        this.function = function;
        this.arg = arg;
    }

    @Override
    protected R init() {
        return function.apply(arg);
    }


}
