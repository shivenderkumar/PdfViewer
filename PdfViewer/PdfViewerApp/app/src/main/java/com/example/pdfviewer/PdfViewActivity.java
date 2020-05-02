package com.example.pdfviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.FileOutputStream;

public class PdfViewActivity extends AppCompatActivity {
    String fileToopen="";
    String fileUrl = "http://192.168.0.107:5000/getFileByName?fname=";   //change IP
    PDFView pdfView;
    String filePath ="/data/user/0/com.example.pdfviewer/files/";       //Path where pdf will be created by FileOutstream

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        pdfView  = findViewById(R.id.pdfView);
        Intent i = getIntent();
        fileToopen = i.getExtras().getString("fileName");
        fileUrl = fileUrl+""+fileToopen;

        InputStreamVolleyRequest request = new InputStreamVolleyRequest(Request.Method.GET, fileUrl,
                new Response.Listener<byte[]>() {
                    @Override
                    public void onResponse(byte[] response) {
                        // TODO handle the response
                        try {
                            if (response!=null) {  //response is in byte[]

                                FileOutputStream outputStream;
                                String name= fileToopen;
                                outputStream = openFileOutput(name, Context.MODE_PRIVATE);
                                outputStream.write(response);
                                outputStream.close();

                                Toast.makeText(getApplicationContext(), "Download complete.", Toast.LENGTH_LONG).show();
                                File f = new File(filePath+""+name);
                                pdfView.fromFile(f).load();
                                //or directly you can open
                                //pdfView.fromBytes(response).load();
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            Log.d("KEY_ERROR", "UNABLE TO DOWNLOAD FILE");
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }
                    }
                } ,new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO handle the error
                error.printStackTrace();
            }
        }, null);
        RequestQueue mRequestQueue = SingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
        mRequestQueue.add(request);

    }
}
