package com.springapp.mvc;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.params.HttpConnectionParams;
import org.junit.Test;

/**
 * Created by papadimos on 5/12/2014.
 */
public class MultiThreadedHttpRequestsTest {

    public MultiThreadedHttpRequestsTest() {
        super();
    }

    public static void main(String[] args) throws InterruptedException {

        // Create an HttpClient with the MultiThreadedHttpConnectionManager.
        // This connection manager must be used if more than one thread will
        // be using the HttpClient.
        HttpClient httpClient = new HttpClient(
                new MultiThreadedHttpConnectionManager());

        // Set the default host/protocol for the methods to connect to.
        // This value will only be used if the methods are not given
        // an absolute URI

        //http://api.themoviedb.org/3/movie/307231?append_to_response=credits,reviews&api_key=186b266209c2da50f898b7977e2a44dd
        //http://api.themoviedb.org/3/movie/307720?append_to_response=credits,reviews&api_key=186b266209c2da50f898b7977e2a44dd
        //http://api.themoviedb.org/3/movie/307721?append_to_response=credits,reviews&api_key=186b266209c2da50f898b7977e2a44dd

        httpClient.getHostConfiguration().setHost(
                "api.themoviedb.org", 80, "http");

        httpClient.getParams().setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 30000);
        httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT, 30000);

//        httpClient.getHostConfiguration().setHost(
//                "jakarta.apache.org", 80, "http");

        // create an array of URIs to perform GETs on
        String[] urisToGet = {
                "http://api.themoviedb.org/3/movie/307231?append_to_response=credits,reviews&api_key=186b266209c2da50f898b7977e2a44dd",
                "http://api.themoviedb.org/3/movie/307720?append_to_response=credits,reviews&api_key=186b266209c2da50f898b7977e2a44dd",
                "http://api.themoviedb.org/3/movie/307721?append_to_response=credits,reviews&api_key=186b266209c2da50f898b7977e2a44dd"
        };

//        String[] urisToGet = {
//                "/",
//                "/commons/",
//                "/commons/httpclient/",
//                "http://cvs.apache.org/viewcvs.cgi/jakarta-commons/httpclient/"
//        };

        // create a thread for each URI
        GetThread[] threads = new GetThread[urisToGet.length];

        for (int i = 0; i < threads.length; i++) {
            GetMethod get = new GetMethod(urisToGet[i]);
            get.setFollowRedirects(true);
            threads[i] = new GetThread(httpClient, get, i + 1);
        }

        // start the threads
        for (int j = 0; j < threads.length; j++) {
            threads[j].start();
        }

        for (int j = 0; j < threads.length; j++) {
            threads[j].join();
        }

    }

    /**
     * A thread that performs a GET.
     */
    static class GetThread extends Thread {

        private HttpClient httpClient;
        private GetMethod method;
        private int id;

        public GetThread(HttpClient httpClient, GetMethod method,
                         int id) {

            this.httpClient = httpClient;
            this.method = method;
            this.id = id;
        }

        /**
         * Executes the GetMethod and prints some satus information.
         */
        @Override
        public void run() {

            try {

                System.out.println(Thread.currentThread().getName() + " - about to get something from "
                        + method.getURI());

                // execute the method
                int result = httpClient.executeMethod(method);

                System.out.println(result);
                System.out.println(id + " - get executed");

                // get the response body as an array of bytes
                byte[] bytes = method.getResponseBody();

                System.out.println(method.getResponseBodyAsString());

                System.out.println(id + " - " + bytes.length +
                        " bytes read");

            } catch (Exception e) {
                System.out.println(id + " - error: " + e);
            } finally {
                // always release the connection after we're done
                method.releaseConnection();
                System.out.println(id + " - connection released");
            }
        }

    }
}
