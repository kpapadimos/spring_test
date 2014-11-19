package com.springapp.mvc.core.api.client;

import com.google.common.collect.Multimap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * Created by papadimos on 19/11/2014.
 */
public class ApiWebClient {
    private Logger log = Logger.getLogger(ApiWebClient.class);
    private ApiSecurity apiSecurity;
    private String scheme;
    private int port;
    private String host;
    private HttpClient httpClient;

    public ApiWebClient() {
        PoolingClientConnectionManager cm = new PoolingClientConnectionManager();
        cm.setMaxTotal(1000);
        cm.setDefaultMaxPerRoute(1000);
        httpClient = new DefaultHttpClient(cm);
        HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 10000);
        HttpConnectionParams.setSoTimeout(httpClient.getParams(), 10000);
    }

    public ApiWebClient(int connectionTimeout, int maxConnections, int maxConnectionsPerHost) {
        PoolingClientConnectionManager cm = new PoolingClientConnectionManager();
        cm.setMaxTotal(maxConnections);
        cm.setDefaultMaxPerRoute(maxConnectionsPerHost);
        httpClient = new DefaultHttpClient(cm);
        HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), connectionTimeout);
        HttpConnectionParams.setSoTimeout(httpClient.getParams(), connectionTimeout);
    }

    public String get(String uri) {
        return get(uri, null);
    }


    public String get(String uri, Multimap<String, String> requestParameters) {
        String url = null;
        HttpGet request = null;
        try {

            UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromPath(uri).scheme(scheme).host(host);
            if (port != 80 && port != 443 && port > 0) {
                urlBuilder = urlBuilder.port(port);
            }

            if (requestParameters != null) {
                for (Map.Entry<String, String> entry : requestParameters.entries()) {
                    urlBuilder = urlBuilder.queryParam(entry.getKey(), entry.getValue());
                }
            }

            url = urlBuilder.build().encode().toUriString();

            if (apiSecurity != null) {
                url = apiSecurity.addHashKeyParamToUrl(url);
            }

            request = new HttpGet("https://" + url);
            HttpResponse response = httpClient.execute(request);




            if (response.getStatusLine().getStatusCode() != 200) {
                log.debug("Error calling url: " + url + ".  Status Code: " + response.getStatusLine().getStatusCode() + ".  Reason: " + response.getStatusLine().getReasonPhrase());
                request.abort();
                return "ERROR";
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String s = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
                return s;
            }

        } catch (Exception e) {
            log.error("Error calling url: " + url, e);
            if(request!=null){
                request.abort();
            }
        }

        return "ERROR";
    }

    public ApiSecurity getApiSecurity() {
        return apiSecurity;
    }

    public void setApiSecurity(ApiSecurity apiSecurity) {
        this.apiSecurity = apiSecurity;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        if (scheme != null) scheme = scheme.toLowerCase();
        this.scheme = scheme;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
