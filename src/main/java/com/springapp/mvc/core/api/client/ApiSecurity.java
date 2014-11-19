package com.springapp.mvc.core.api.client;

import org.apache.log4j.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by papadimos on 19/11/2014.
 */
public class ApiSecurity {

    private Logger log = Logger.getLogger(ApiSecurity.class);
    private Mac encryptor;

    public ApiSecurity() {

    }

    public ApiSecurity(String privateKey) {
        try {
            encryptor = Mac.getInstance("HmacMD5");
            SecretKeySpec secret = new SecretKeySpec(privateKey.getBytes(), "HmacMD5");
            encryptor.init(secret);
        } catch (Exception e) {
            log.error("Error initializing ApiSecurity", e);
        }
    }

    protected String addHashKeyParamToUrl(String url) {
        if (url.contains("?")) {
            url += "&t=" + System.currentTimeMillis();
        } else {
            url += "?t=" + System.currentTimeMillis();
        }
        String hash = hashString(url);
        return url + "&hashKey=" + hash;
    }

    protected String hashString(String s) {
        byte[] digest = encryptor.doFinal(s.getBytes());
        return byteArrayToHex(digest);
    }

    public static String byteArrayToHex(byte[] a) {
        int hn, ln, cx;
        String hexDigitChars = "0123456789abcdef";
        StringBuffer buf = new StringBuffer(a.length * 2);
        for (cx = 0; cx < a.length; cx++) {
            hn = ((int) (a[cx]) & 0x00ff) / 16;
            ln = ((int) (a[cx]) & 0x000f);
            buf.append(hexDigitChars.charAt(hn));
            buf.append(hexDigitChars.charAt(ln));
        }
        return buf.toString();
    }

    public boolean isUrlExecutionAllowed(HttpServletRequest request) {
        String url = request.getRequestURL() + "?" + request.getQueryString();

        int idx = url.indexOf("hashKey=");
        if (idx > 0) {
            String baseUrl = url.substring(0, url.indexOf("hashKey=") - 1);
            String hashKey = url.substring(url.indexOf("hashKey=") + 8);
            String newHash = hashString(baseUrl);
            if (newHash.equals(hashKey)) {
                return true;
            }
        }
        return false;
    }

}
