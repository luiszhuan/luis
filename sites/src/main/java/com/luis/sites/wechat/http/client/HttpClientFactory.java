package com.luis.sites.wechat.http.client;


import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpClientFactory {
    private static Logger LOGGER = LogManager.getLogger(HttpClientFactory.class);
    private static HttpClientFactory ourInstance = new HttpClientFactory();
    private CloseableHttpClient client;

    private HttpClientFactory() {
        try {
            client = getIgnoreSSLClient();
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    public static HttpClientFactory getInstance() {
        return ourInstance;
    }

    public CloseableHttpClient getIgnoreSSLClient() throws Exception {
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        }).build();

        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(400);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(50);
        //创建httpClient
        HttpClientBuilder builder = HttpClients.custom().setSSLContext(sslContext).setSSLHostnameVerifier(new NoopHostnameVerifier());
        builder.setConnectionManager(poolingHttpClientConnectionManager);
        return builder.build();
    }
}
