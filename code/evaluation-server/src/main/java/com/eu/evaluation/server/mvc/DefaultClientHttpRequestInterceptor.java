/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.mvc;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

/**
 *
 * @author dell
 */
public class DefaultClientHttpRequestInterceptor implements ClientHttpRequestInterceptor{
   
    private List<MediaType> acceptyTypes;

    public List<MediaType> getAcceptyTypes() {
        return acceptyTypes;
    }

    public void setAcceptyTypes(List<MediaType> acceptyTypes) {
        this.acceptyTypes = acceptyTypes;
    }
    
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution execution) throws IOException {
        HttpRequestWrapper wrapper = new HttpRequestWrapper(httpRequest);
        wrapper.getHeaders().setAccept(acceptyTypes);
        
        List<Charset> chars = new ArrayList<Charset>();
        chars.add(Charset.forName("GBK"));
        wrapper.getHeaders().setAcceptCharset(chars);
        wrapper.getHeaders().setContentType(MediaType.parseMediaType("application/xml;GBK"));
        return execution.execute(httpRequest, bytes);
    }
    
}
