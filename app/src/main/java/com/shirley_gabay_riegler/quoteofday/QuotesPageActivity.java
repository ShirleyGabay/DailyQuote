package com.shirley_gabay_riegler.quoteofday;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shirley_gabay_riegler.quoteofday.Controllers.DownloadJSONDataThread;
import com.shirley_gabay_riegler.quoteofday.Models.Quote;

import java.util.ArrayList;

public class QuotesPageActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView quoteTV, categoryTV, authorTV;
    private TextView quote, category, author;

    private DownloadJSONDataThread downloadJSONDataThread;
    private String LINK = "https://talaikis.com/api/quotes/";
    private Handler handler;
    private ArrayList<Quote> quotes;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_page);

        handler = new Handler();
        quoteTV = findViewById(R.id.activity_quote_page_quote);
        categoryTV = findViewById(R.id.activity_quote_page_category);
        authorTV = findViewById(R.id.activity_quote_page_author);
        getQuotes();
        Button btn = findViewById(R.id.activity_quote_page_next_btn);
        Button back = findViewById(R.id.activity_quote_page_back_btn);
        btn.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    public void getQuotes() {
        downloadJSONDataThread = new DownloadJSONDataThread(this, handler, LINK);
        downloadJSONDataThread.start();
    }

    public void updateQuotes(ArrayList<Quote> quotes) {
        this.quotes = quotes;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_quote_page_next_btn:
                counter++;
                break;
            case R.id.activity_quote_page_back_btn:
                counter--;
                break;
        }
        if (!quotes.isEmpty()) {
            quotes.get(counter);
            quoteTV.setText(quotes.get(counter).getQuote());
            categoryTV.setText(quotes.get(counter).getCat());
            authorTV.setText(quotes.get(counter).getAuthor());
        } else {
            Toast.makeText(QuotesPageActivity.this, "wating for internet", Toast.LENGTH_SHORT).show();
        }
    }
}
