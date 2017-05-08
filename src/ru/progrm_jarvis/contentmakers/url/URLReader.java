package ru.progrm_jarvis.contentmakers.url;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to getInstance data from URL
 */

public final class URLReader {
    public static List<String> read (String urlString) throws Exception {
        List<String> result = new ArrayList<String>();
        URL url = new URL(urlString);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            result.add(inputLine);
            System.out.println(result);
        }
        in.close();
        return result;
    }
}