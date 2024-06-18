package com.fernandopaiva.appfinal.service;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class IdInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String originalUrl = request.url().toString();

        // Supondo que a ID está no final da URL como uma string.
        String modifiedUrl = originalUrl.replaceAll("/idString/", "/id/");

        Request modifiedRequest = request.newBuilder()
                .url(modifiedUrl)
                .build();

        return chain.proceed(modifiedRequest);
    }
}

