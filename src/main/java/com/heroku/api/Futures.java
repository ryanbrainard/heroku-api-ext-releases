package com.heroku.api;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Ryan Brainard
 */
public class Futures {

    private Futures() {
    }

    public static interface Mapping<A, B> {
        B apply(A a);
    }

    public static <A, B> Future<B> map(final Future<A> a, final Mapping<A, B> m) {
        return new Future<B>() {
            public boolean cancel(boolean mayInterruptIfRunning) {
                return a.cancel(mayInterruptIfRunning);
            }

            public boolean isCancelled() {
                return a.isCancelled();
            }

            public boolean isDone() {
                return a.isDone();
            }

            public B get() throws InterruptedException, ExecutionException {
                return m.apply(a.get());
            }

            public B get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return m.apply(a.get(timeout, unit));
            }
        };
    }

    public static interface FlatMapping<A, B> {
        Future<B> apply(A a);
    }

    public static <A, B> Future<B> flatMap(final Future<A> a, final FlatMapping<A, B> t) throws ExecutionException, InterruptedException {
        return t.apply(a.get());
    }

    public static <A, B> Future<B> flatMap(final Future<A> a, int timeout, TimeUnit seconds, final FlatMapping<A, B> t) throws ExecutionException, InterruptedException, TimeoutException {
        return t.apply(a.get(timeout, seconds));
    }
}
