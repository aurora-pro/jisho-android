package com.example.maryam.jishoorg;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by maryam on 11/25/16.
 */

public class DownloadLink extends AsyncTask <String, Void, String>
{

    @Override
    protected String doInBackground(String... urls) {
        String result = "";

        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(urls [0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            int data = inputStreamReader.read();

            while (data != -1){
                result+= (char) data;
                data = inputStreamReader.read();
            }

            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}

