package in.trentweet.recyclerwithfilter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyAdapter adapter;
    EditText etSearch;
    List<CountryModel> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = findViewById(R.id.etSearch);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        getCountryNamesList();

    }

    void filter(String text) {
        List<CountryModel> temp = new ArrayList();
        for (CountryModel d : model) {
            if (d.getCountryName().toLowerCase().contains(text.toLowerCase())) {
                temp.add(d);
            }
        }
        adapter.updateList(temp);
    }

    public void setText(String text){
        etSearch.setText(text);
    }

    private void getCountryNamesList() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                "https://restcountries.eu/rest/v2/all", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                model = new ArrayList<>();

                for (int i = 0; i < response.length(); i++) {
                    model.add(new CountryModel(response.optJSONObject(i).optString("name"),
                            response.optJSONObject(i).optString("capital"),
                            response.optJSONObject(i).optString("nativeName"),
                            response.optJSONObject(i).optString("flag")));
                }

                adapter = new MyAdapter(getApplicationContext(), model, MainActivity.this);
                RecyclerView recyclerView = findViewById(R.id.recyclerView);

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                        LinearLayoutManager.VERTICAL, false));
                recyclerView.hasFixedSize();
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(), "SIZE : " + adapter.getItemCount(),
                        Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null) {
                    NetworkResponse response = error.networkResponse;
                    Integer statusCode = 0;
                    if (response != null)
                        statusCode = response.statusCode;

                    Toast.makeText(getApplicationContext(), "Error: " + statusCode,
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

        AppController.getInstance().addToRequestQueue(request);
    }

}
