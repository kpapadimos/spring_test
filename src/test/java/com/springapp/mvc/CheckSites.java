package com.springapp.mvc;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by papadimos on 6/12/2014.
 */
public final class CheckSites {

    /** Run this tool. */
    public static final void main(String... aArgs) {
        CheckSites checker = new CheckSites();
        try {
//            log("Parallel, report each as it completes:");
//            checker.pingAndReportEachWhenKnown();

            log("Parallel, report all at end:");
            checker.pingAndReportAllAtEnd();

//            log("Sequential, report each as it completes:");
//            checker.pingAndReportSequentially();
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
        catch(ExecutionException ex){
            log("Problem executing worker: " + ex.getCause());
        }
        log("Done.");
    }

    /**
     Check N sites, in parallel, using up to 4 threads.
     Report the result of each 'ping' as it comes in.
     (This is likely the style most would prefer.)
     */
    void pingAndReportEachWhenKnown()  throws InterruptedException, ExecutionException {
        int numThreads = URLs.size() > 4 ? 4 : URLs.size(); //max 4 threads
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        CompletionService<PingResult> compService = new ExecutorCompletionService<PingResult>(executor);
        for(String url : URLs){
            Task task = new Task(url);
            compService.submit(task);
        }
        for(String url : URLs){
            Future<PingResult> future = compService.take();
            log(future.get());
        }
        executor.shutdown(); //always reclaim resources
    }

    /**
     Check N sites, in parallel, using up to 4 threads.
     Report the results only when all have completed.
     */
    void pingAndReportAllAtEnd() throws InterruptedException, ExecutionException {
        Collection<Callable<PingResult>> tasks = new ArrayList<Callable<PingResult>>();
        for(String url : URLs){
            tasks.add(new Task(url));
        }
        int numThreads = URLs.size() > 4 ? 4 : URLs.size(); //max 4 threads
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<PingResult>> results = executor.invokeAll(tasks);
        for(Future<PingResult> result : results){
            PingResult pingResult = result.get();
            log(pingResult);
        }
        executor.shutdown(); //always reclaim resources
    }

    /**
     Check N sites, but sequentially, not in parallel.
     Does not use multiple threads at all.
     */
    void pingAndReportSequentially() throws MalformedURLException {
        for(String url : URLs){
            PingResult pingResult = pingAndReportStatus(url);
            log(pingResult);
        }
    }

    // PRIVATE

    private static final List<String> URLs = Arrays.asList(
            "http://api.themoviedb.org/3/movie/307231?append_to_response=credits,reviews&api_key=186b266209c2da50f898b7977e2a44dd",
            "http://api.themoviedb.org/3/movie/307720?append_to_response=credits,reviews&api_key=186b266209c2da50f898b7977e2a44dd",
            "http://api.themoviedb.org/3/movie/307721?append_to_response=credits,reviews&api_key=186b266209c2da50f898b7977e2a44dd"
    );

    private static void log(Object aMsg){
        System.out.println(String.valueOf(aMsg));
    }

    /** Try to ping a URL. Return true only if successful. */
    private final class Task implements Callable<PingResult> {
            Task(String aURL){
                fURL = aURL;
            }
            /** Access a URL, and see if you get a healthy response. */
            @Override public PingResult call() throws Exception {
                return pingAndReportStatus(fURL);
            }
            private final String fURL;
    }

    private PingResult pingAndReportStatus(String aURL) throws MalformedURLException {
        PingResult result = new PingResult();
        result.URL = aURL;
        long start = System.currentTimeMillis();
        URL url = new URL(aURL);
        try {
//            URLConnection connection = url.openConnection();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int FIRST_LINE = 0;
            String firstLine = connection.getHeaderField(FIRST_LINE);
            result.SUCCESS = true;
            long end = System.currentTimeMillis();
            result.TIMING = end - start;

            InputStream is = null;
            is = connection.getInputStream();
            int ch;
            StringBuffer sb = new StringBuffer();
            while ((ch = is.read()) != -1) {
                sb.append((char) ch);
            }
            result.BODY = sb.toString();

        }
        catch(IOException ex){
            //ignore - fails
        }
        return result;
    }

    /** Simple struct to hold all the date related to a ping. */
    private static final class PingResult {
        String URL;
        Boolean SUCCESS;
        Long TIMING;
        String BODY;
        @Override public String toString(){
            return "Result:" + SUCCESS + " " +TIMING + " msecs " + URL + " body " + BODY;
        }
    }
}
