package uts.eai.elabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import uts.eai.elabs.adapter.AdapterRecyclerLatihan;
import uts.eai.elabs.adapter.AdapterRecyclerTes;
import uts.eai.elabs.app.AppController;
import uts.eai.elabs.data.DataLatihan;
import uts.eai.elabs.data.DataTes;

public class MainActivityTes extends AppCompatActivity {

    RecyclerView list;
    Button btnSubmit;
    SwipeRefreshLayout swipe;
    List<DataTes> itemList = new ArrayList<DataTes>();
    AdapterRecyclerTes adapter;

    //aDARI MYSQL
    //PAKE MYSQL
    private static final String TAG = MainActivityTes.class.getSimpleName();

    private static String url_select 	 = Server.URL + "select_question.php";

    public static final String TAG_ID = "id";
    public static final String TAG_SOAL = "soal";
    public static final String TAG_OPSI1 = "opsi1";
    public static final String TAG_OPSI2 = "opsi2";
    public static final String TAG_OPSI3 = "opsi3";
    public static final String TAG_OPSI4 = "opsi4";
    public static final String TAG_JAWABAN = "jawaban";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_latihan);

        list         = (RecyclerView) findViewById(R.id.list);
        btnSubmit    = (Button) findViewById(R.id.btSelesai);

        //inisialisasi recycler view
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setItemAnimator(new DefaultItemAnimator());

        // menghubungkan variablel pada layout dan pada java

        swipe   = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);





        //set dummy data
        callVolley();

        //TEMPLATE
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fungsi untuk dapat semua jawaban di adapter
                List<DataTes>listAnswer = adapter.getAllAnswer();

                int nilai = 0;
                //looping untuk nilai
                for (int i = 0; i <listAnswer.size() ; i++) {
                    //cek jawaban sama atau tidak dengan kunci jawaban
                    if(listAnswer.get(i).getJawaban().equals(listAnswer.get(i).getDipilih())){
                        //jika jawaban betul maka nilai + 1
                        nilai = nilai + 1;
                    }
                }
                //tampilkan nilai di toast
                Toast.makeText(getApplicationContext(),"Nilai anda: "+nilai,Toast.LENGTH_LONG).show();
            }
        });

    }


     public void onRefresh() {
        itemList.clear();
        //adapter.notifyDataSetChanged();
        callVolley();
    }


    // untuk menampilkan semua data pada listview
    private void callVolley(){
        itemList.clear();
//        adapter.notifyDataSetChanged();
        swipe.setRefreshing(false);

        // membuat request JSON
        JsonArrayRequest jArr = new JsonArrayRequest(url_select, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        DataTes item = new DataTes();

                        item.setId(obj.getString(TAG_ID));
                        item.setSoal(obj.getString(TAG_SOAL));
                        item.setJawaban(obj.getString(TAG_JAWABAN));

                        item.setId(obj.getString(TAG_ID));
                        item.setSoal(obj.getString(TAG_SOAL));
                        item.setOpsi1(obj.getString(TAG_OPSI1));
                        item.setOpsi2(obj.getString(TAG_OPSI2));
                        item.setOpsi3(obj.getString(TAG_OPSI3));
                        item.setOpsi4(obj.getString(TAG_OPSI4));
                        item.setJawaban(obj.getString(TAG_JAWABAN));

                        // menambah item ke array
                        itemList.add(item);

                        //set adapter untuk recycler view
                        adapter = new AdapterRecyclerTes(itemList);
                        list.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // notifikasi adanya perubahan data pada adapter
               // adapter.notifyDataSetChanged();

                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        // menambah request ke request queue
        AppController.getInstance().addToRequestQueue(jArr);
    }
}