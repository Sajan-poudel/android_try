package com.example.fetch_json_internet;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.HttpStatus;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {

    private WeakReference<ImageView> imageViewWeakReference;

    public ImageDownloaderTask(ImageView imageView) {
        imageViewWeakReference = new WeakReference<ImageView>(imageView);
    }


    @Override
    protected Bitmap doInBackground(String... params) {
        return downloadBitmap(params[0]);
    }

    protected void onPostExecute(Bitmap bitmap){
        if (isCancelled()){
            bitmap = null;
        }

        if (imageViewWeakReference != null){
            ImageView imageView = imageViewWeakReference.get();
            if (imageView != null){
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }

    private Bitmap downloadBitmap(String imageURL){
        HttpURLConnection urlConnection = null;
        try{
            URL uri = new URL(imageURL);
            urlConnection = (HttpURLConnection) uri.openConnection();

            int statusCode = urlConnection.getResponseCode();

            if (statusCode != HttpStatus.SC_OK){
                return null;
            }
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null){
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }

        }catch (Exception e){
//            urlConnection.disconnect();
            Log.i("ImageDownloader", "error downloading the image");
        }

        finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
        }
        return null;
    }
}
