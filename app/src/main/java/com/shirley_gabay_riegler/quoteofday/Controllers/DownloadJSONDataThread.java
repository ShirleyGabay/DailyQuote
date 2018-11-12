package com.shirley_gabay_riegler.quoteofday.Controllers;

import android.os.Handler;
import com.shirley_gabay_riegler.quoteofday.Models.Quote;
import com.shirley_gabay_riegler.quoteofday.QuotesPageActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by User on 5/31/2017.
 */

public class DownloadJSONDataThread extends Thread {

    private QuotesPageActivity activity;
    private Handler handler;
    private String link;
    private Quote quote;
    private ArrayList<Quote> quotes;
    private int counter = 0;

    public DownloadJSONDataThread(QuotesPageActivity activity, Handler handler, String link) {
        this.activity = activity;
        this.handler = handler;
        this.link = link;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(link);
            System.out.println(url.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            InputStream in = con.getInputStream();
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            quotes = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(sb.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                quotes.add(new Quote(object));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        activity.updateQuotes(quotes);
                    }
                });
            }

        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

   /* public ArrayList<Quote> quotesShuffle() {
        quotesList = quotes.get(counter);
        Random random = new Random();
        int randomIndex;
        String temp;
        for (int i = 0; i < 4; i++) {
            randomIndex = random.nextInt(4);
            temp = quotesList[i];
            quotesList[i] = quotesList[randomIndex];
            quotesList[randomIndex] = temp;
        }
        return quotesList;
    }*/
}
