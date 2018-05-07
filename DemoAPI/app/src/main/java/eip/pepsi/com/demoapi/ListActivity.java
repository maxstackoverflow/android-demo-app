package eip.pepsi.com.demoapi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import eip.pepsi.com.demoapi.model.Inventory;

public class ListActivity extends AppCompatActivity {

    private ArrayList<Inventory> items;
    private EditText edit;
    private Button sync;
    private Button clear;
    private RecyclerView rv;
    private long lastUpdate;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private String serverUrl = "placeholder.com";


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
                    return true;
                case R.id.navigation_dashboard:
                    edit.setVisibility(View.VISIBLE);
                    sync.setVisibility(View.VISIBLE);
                    clear.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.INVISIBLE);
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
        items = new ArrayList<>();

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
                items.clear();
            }
        });

        rv = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new InventoryAdapter();
        rv.setAdapter(mAdapter);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // TODO
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // TODO
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tv;
            public ViewHolder(TextView itemView) {
                super(itemView);
                tv = itemView;
            }
        }
    }
    private void loadItems(String s, long l) {
        try {
            URL url = new URL(serverUrl);
            HttpURLConnection client = (HttpURLConnection) url.openConnection();
            client.setRequestMethod("POST");
            client.setDoOutput(true);
            client.setDoInput(true);
            client.addRequestProperty("Content-Type", "application/POST");

            String json = new JSONObject()
                    .put("username", s)
                    .put("last_sync_time", Long.toString(lastUpdate))
                    .toString();
            OutputStream os = client.getOutputStream();
            os.write(json.getBytes("UTF8"));
            os.flush();
            os.close();

            if(client.getResponseCode() == HttpURLConnection.HTTP_OK) {

                InputStream is = client.getInputStream();

                Scanner scan = new Scanner(is).useDelimiter("\\A");
                String result = scan.hasNext() ? scan.next() : "";

                JSONArray jar = new JSONArray(result);

                for (int i = 0; i < jar.length(); i ++) {
                    JSONObject job = new JSONObject(jar.getString(i));
                    Inventory inv = new Inventory();
                    inv.setProp(job.get("prop"));
                    items.add(inv);
                }

            }

            lastUpdate = l;
            mAdapter.notifyDataSetChanged();
        } catch (Exception e) {

        }
    }


}
