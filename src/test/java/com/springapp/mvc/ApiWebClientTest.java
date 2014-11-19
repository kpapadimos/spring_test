package com.springapp.mvc;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.springapp.mvc.core.api.client.ApiWebClient;
import org.junit.Test;

/**
 * Created by papadimos on 19/11/2014.
 */
public class ApiWebClientTest {

    @Test
    public void apiWebClient() throws Exception {
        ApiWebClient wc = new ApiWebClient();

        String[] params = new String[]{"api_key"};

        String[] values = new String[]{"186b266209c2da50f898b7977e2a44dd"};

        //String response = wc.get("https://api.themoviedb.org/3/movie/550?api_key=186b266209c2da50f898b7977e2a44dd", createParametersMap(params, values));
        //String response = wc.get("https://api.themoviedb.org/3/movie/550", createParametersMap(params, values));
        String response = wc.get("api.themoviedb.org/3/movie/550", createParametersMap(params, values));

        System.out.println(response);
    }

    protected Multimap<String, String> createParametersMap(String[] keys, String[] values) {
        Multimap<String, String> parameters = ArrayListMultimap.create();
        for (int i = 0; i < keys.length; i++) {
            if (values[i] != null && values[i].trim().length() > 0) {
                parameters.put(keys[i], values[i]);
            }
        }
        return parameters;
    }
}
