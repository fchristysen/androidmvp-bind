package org.greenfroyo.androidmvp_bind.driver.api;

import org.greenfroyo.androidmvp_bind.R;

import rx.Observable;

/**
 * Created by fchristysen on 7/27/16.
 */

public interface ApiDriver {

    <R> Observable<R> get(String url, Class<R> responseClass);

    <P, R> Observable<R> post(final String url, final P requestBody, final Class<P> requestClass, final Class<R> responseClass);
}
