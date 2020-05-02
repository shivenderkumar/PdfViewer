package com.example.pdfviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    ArrayList<String> arraylist;
    ArrayAdapter<String> adapter;
    JSONObject result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchview);
        listView = findViewById(R.id.listview);
        arraylist = new ArrayList<>();

        //add element from net
        String BASE_URL = "http://192.168.0.107:5000/getAllFiles";
        final RequestQueue reqQueue = SingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(BASE_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.wtf(response.toString());
                result =response;
                System.out.println(result.toString());
                try{
                    JSONArray arrJson = result.getJSONArray("filenames");
                    for(int i = 0; i < arrJson.length(); i++){
                        arraylist.add(arrJson.getString(i));
                    }
                    adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arraylist);
                    listView.setAdapter(adapter);

                }catch (Exception e){
                    System.out.println("Error while converting jsonArray to arraylist");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.wtf(error.getMessage(), "utf-8");
                System.out.println(error.getMessage());
            }
        });

        reqQueue.add(jsonObjectRequest);
        //////////////////////////
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position, long id) {
                Intent i = new Intent(getApplicationContext(), PdfViewActivity.class);
                String fileName = adapter.getItem(position);
                i.putExtra("fileName", fileName);
                startActivity(i);
            }
        });

        /////////////////////////
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(arraylist.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
