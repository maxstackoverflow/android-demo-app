package eip.pepsi.com.demoapi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eip.pepsi.com.demoapi.model.Inventory;

public class ListActivity extends AppCompatActivity {

    private EditText edit;
    private Button sync;
    private Button clear;
    private RecyclerView rv;
    private TextView desc;
    private TextView nothing;
    private long lastUpdate;

    private InventoryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private String serverUrl = "https://pcf-test-products.apps.pepdfdev.pepsico.com/getproducts?username=";


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    edit.setVisibility(View.INVISIBLE);
                    sync.setVisibility(View.INVISIBLE);
                    clear.setVisibility(View.INVISIBLE);
                    rv.setVisibility(View.VISIBLE);
                    desc.setVisibility(View.VISIBLE);
                    if (mAdapter.isEmpty()){
                        nothing.setVisibility(View.VISIBLE);
                    }
                    return true;
                case R.id.navigation_dashboard:
                    edit.setVisibility(View.VISIBLE);
                    sync.setVisibility(View.VISIBLE);
                    clear.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.INVISIBLE);
                    nothing.setVisibility(View.INVISIBLE);
                    desc.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final ListActivity la = this;
        lastUpdate = -1;

        edit = findViewById(R.id.username);
        edit.setVisibility(View.INVISIBLE);

        sync = findViewById(R.id.sync);
        sync.setVisibility(View.INVISIBLE);

        sync.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                la.loadItems(edit.getText().toString(), System.currentTimeMillis());
            }
        });

        clear = findViewById(R.id.clear);
        clear.setVisibility(View.INVISIBLE);

        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edit.setText(null);
                mAdapter.setDataset(new ArrayList<Inventory>());
            }
        });

        rv = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new InventoryAdapter();
        rv.setAdapter(mAdapter);

        desc = findViewById(R.id.inv);

        nothing = findViewById(R.id.nothing);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void loadItems(final String s, long l) {
        final StringBuilder result = new StringBuilder();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(serverUrl + s);
                        HttpURLConnection client = (HttpURLConnection) url.openConnection();
                        client.setRequestMethod("GET");
                        int resp = client.getResponseCode();

                        BufferedReader rd = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        String line;
                        while ((line = rd.readLine()) != null) {
                            result.append(line);
                        }
                        rd.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            try {
                Thread.sleep(850);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String res = result.toString();

            List<Inventory> items = Arrays.asList(new Gson().fromJson(new JsonParser().parse(res)
                .getAsJsonObject()
                .getAsJsonArray("items")
                .toString(), Inventory[].class));

            mAdapter.setDataset(items);
            lastUpdate = l;
    }


}
