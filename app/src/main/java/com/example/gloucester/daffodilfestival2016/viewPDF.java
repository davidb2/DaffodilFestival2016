package com.example.gloucester.daffodilfestival2016;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by David on 2/20/2016.
 */
// class within a class;
// allows a task to be performed on a different thread than the main activity
public class ViewPDF extends AsyncTask<MainActivity, Void, MainActivity> {
    // online pdf of the map
    private String url;
    private String pdfviewurl;
    private String pdfext;
    // Default download path
    private String filepathOfDownloads = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .getAbsolutePath();
    // Just the name of the map
    private String mapName;
    // creating file name
    private File file;
    private Uri path;

    // executes before asynchronous task begins
    // currently not of need
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    // executes on a separate thread than the main activity
    // takes the main activity as a parameter to later return it
    // passes main activity off to onPostExecute
    @Override
    protected MainActivity doInBackground (MainActivity... m) {
        // initializing global variables from strings.xml
        url = m[0].getString(R.string.map_url);
        pdfviewurl = m[0].getString(R.string.googlepdfviewer_url);
        mapName = m[0].getString(R.string.map_name);
        pdfext = m[0].getString(R.string.pdf_extension);
        file = new File(filepathOfDownloads+mapName+pdfext);
        path = Uri.fromFile(new File(file.toString()));


        // if the map is already downloaded onto the phone,
        //     dont do anything here
        // else,
        //     download the map onto the phone!
        if (!file.exists()) {
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection)(new URL(url)).openConnection();
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                FileOutputStream fileOutputStream =
                        new FileOutputStream(file.toString());
                byte[] buffer = new byte[4096];
                int bufferLength = 0;
                while((bufferLength = inputStream.read(buffer))!=-1 ){
                    fileOutputStream.write(buffer, 0, bufferLength);
                }
                if (fileOutputStream != null)
                    fileOutputStream.close();
                if (inputStream != null)
                    inputStream.close();
                if (urlConnection != null)
                    urlConnection.disconnect();
            } catch (IOException e) {
                // Do not do anything if something goes wrong.
            }
        }
        // I only need the main activity.
        // Only one parameter should be passed anyway.
        return m[0];
    }
    // Executes after the asynchronous task has completed
    protected void onPostExecute(MainActivity m) {
        // action to view pdf
        Intent intent = new Intent(Intent.ACTION_VIEW, path);
        try {
            // try to view file
            m.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // if android cannot find the file or default pdf viewer,
            // just make an action to open the file/url in Google's online pdf viewer
            Intent browserIntent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(pdfviewurl+url));
            // open file via Google's online pdf viewer
            m.startActivity(browserIntent);
        }
    }
}