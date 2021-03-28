package com.example.currencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Document doc;
    private Thread secThread;
    private Runnable runnable;
    private ListView listView;
    private CustomArrayAdapter adapter;
    private List<ListItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {
        listView = findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new CustomArrayAdapter(this, R.layout.list_item, list, getLayoutInflater());
        listView.setAdapter(adapter);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        });
        thread.start();

    }

    private void getWeb() {
        try {
            doc = Jsoup.connect("https://nationalbank.kz/ru/exchangerates/ezhednevnye-oficialnye-rynochnye-kursy-valyut").get();
            Elements table = doc.getElementsByTag("tbody");
            Element currencyTable = table.get(0);
//
//            Log.d("MyLog", "Tbody size " + currrency_elements.get(3).text());

            for (int i = 0; i < currencyTable.childrenSize(); i++) {
                ListItem items = new ListItem();
                items.setCurName(currencyTable.children().get(i).child(1).text());
                items.setCurCode(currencyTable.children().get(i).child(2).text());
                items.setCurValue(currencyTable.children().get(i).child(3).text());

                list.add(items);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}