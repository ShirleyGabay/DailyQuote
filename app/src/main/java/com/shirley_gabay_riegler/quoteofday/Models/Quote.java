package com.shirley_gabay_riegler.quoteofday.Models;

import org.json.JSONException;
import org.json.JSONObject;

public class Quote {

    private String quote;
    private String author;
    private String cat;
    private JSONObject object;

    public Quote(String quote, String author, String cat) {
        this.quote = quote;
        this.author = author;
        this.cat = cat;
    }

    public Quote(JSONObject object) throws JSONException {
        this.object = object;
        quote = object.getString("quote");
        author = object.getString("author");
        cat = object.getString("cat");
    }

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }

    public String getCat() {
        return cat;
    }
}
