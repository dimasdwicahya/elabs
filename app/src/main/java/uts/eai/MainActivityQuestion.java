package uts.eai;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import uts.eai.elabs.R;
import uts.eai.elabs.adapter.AdapterQuestion;
import uts.eai.elabs.app.AppController;
import uts.eai.elabs.data.DataQuestion;
import uts.eai.elabs.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivityQuestion extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{


    ListView list;
    SwipeRefreshLayout swipe;
    List<DataQuestion> itemList = new ArrayList<DataQuestion>();
    AdapterQuestion adapter;
    int success;



    private static final String TAG = MainActivityQuestion.class.getSimpleName();

    private static String url_select 	 = Server.URL + "select_question.php";
    private static String url_insert 	 = Server.URL + "insert.php";
    private static String url_edit 	     = Server.URL + "edit.php";
    private static String url_update 	 = Server.URL + "update.php";
    private static String url_delete 	 = Server.URL + "delete.php";

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


        setContentView(R.layout.activity_main_question);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);


        // menghubungkan variablel pada layout dan pada java

        swipe   = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        list    = (ListView) findViewById(R.id.list);

        // untuk mengisi data dari JSON ke dalam adapter
        adapter = new AdapterQuestion(MainActivityQuestion.this, itemList);
        list.setAdapter(adapter);




        // menamilkan widget refresh
        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           itemList.clear();
                           adapter.notifyDataSetChanged();
                           callVolley();
                       }
                   }
        );


        // listview ditekan maka detail soal akan muncul di aktivity lain
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(final AdapterView<?> parent, View view,
//                                    final int position, long id) {
//                // TODO Auto-generated method stub
//                final String idx = itemList.get(position).getId();
//
//                //Berpindah Activity dan Mempassing data Nama pada Activity Selanjutnya
//                Intent sendData = new Intent(MainActivityQuestion.this, MainActivityQuestion.class);
//                sendData.putExtra("idSoal", idx);
//                startActivity(sendData);
//            }
//
//        });

    }

    @Override
    public void onRefresh() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        callVolley();
    }


    // untuk menampilkan semua data pada listview
    private void callVolley(){
        itemList.clear();
        adapter.notifyDataSetChanged();
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

                        DataQuestion item = new DataQuestion();

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
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // notifikasi adanya perubahan data pada adapter
                adapter.notifyDataSetChanged();

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