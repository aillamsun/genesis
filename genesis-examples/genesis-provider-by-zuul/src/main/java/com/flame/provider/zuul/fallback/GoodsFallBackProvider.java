//package com.flame.provider.zuul.fallback;
//
//import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.stereotype.Component;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.Charset;
//
///**
// * Created by sungang on 2017/9/19.
// */
//@Component
//public class GoodsFallBackProvider implements ZuulFallbackProvider {
//
//
//    @Override
//    public String getRoute() {
//        // 表明是哪个微服务提供回退
//        return "genesis-provider-goods";
//    }
//
//    @Override
//    public ClientHttpResponse fallbackResponse() {
//        return new ClientHttpResponse() {
//            @Override
//            public HttpHeaders getHeaders() {
//                // headers设定
//                HttpHeaders headers = new HttpHeaders();
//                MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
//                headers.setContentType(mt);
//                return headers;
//            }
//
//            @Override
//            public InputStream getBody() throws IOException {
//                // 响应体
//                return new ByteArrayInputStream("商品微服务不可用,请稍后再试。".getBytes());
//            }
//
//            @Override
//            public String getStatusText() throws IOException {
//                // 状态文本，此处返的OK，详见HttpStatus
//                return this.getStatusCode().getReasonPhrase();
//            }
//
//            @Override
//            public HttpStatus getStatusCode() throws IOException {
//                // fallback时的状态码
//                return HttpStatus.OK;
//            }
//
//            @Override
//            public int getRawStatusCode() throws IOException {
//                // 数字类型的状态码,此处返的200，详见HttpStatus
//                return this.getStatusCode().value();
//            }
//
//            @Override
//            public void close() {
//            }
//        };
//    }
//}
