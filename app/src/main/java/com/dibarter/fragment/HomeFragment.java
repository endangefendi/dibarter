package com.dibarter.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dibarter.API.Constans;
import com.dibarter.R;
import com.dibarter.adapter.BarangAdapter;
import com.dibarter.model.BarangModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements BarangAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    final static String TAG = "HomeFragment";
    private RecyclerView recyclerView;
    private BarangAdapter adapter;
    private List<Object> list;
    private SwipeRefreshLayout refreshLayout;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);


        refreshLayout = v.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);
        recyclerView = v.findViewById(R.id.list_barang);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager layoutManagerKategori = new LinearLayoutManager(getContext());
        layoutManagerKategori.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManagerKategori);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        adapter = new BarangAdapter(getActivity(), this, list);
        recyclerView.setAdapter(adapter);
        addData(1);
    }

    private void addData(int page) {
        list.clear();
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.GET, Constans.BASE_URL.GET_ITEM_LISTS+page, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse" + response);
                try {
                    JSONObject object = new JSONObject(response);
                    int status = object.getInt("status");
                    String message = object.getString("message");
                    if (status == 200 && !message.equalsIgnoreCase("") ) {
                        String item_list = object.getString("data");
                        JSONArray array = new JSONArray(item_list);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            int no = obj.getInt("no");
                            int item_id = obj.getInt("item_id");
                            String item_title = obj.getString("item_title");
                            String item_desc = obj.getString("item_desc");
                            String item_highlight = obj.getString("item_highlight");
                            String item_gambar_utama = obj.getString("item_gambar_utama");
                            String item_wilayah = obj.getString("item_wilayah");
                            String item_tanggal = obj.getString("item_tanggal");
                            int item_views = obj.getInt("item_views");

                            BarangModel item = new BarangModel(no,item_id,item_title,item_desc,item_highlight,item_gambar_utama,item_wilayah,item_tanggal,item_views);
                            list.add(item);
                        }
                        adapter.addList(list);
                        adapter.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);
                        addBannerAds();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Constans.parseError(getContext(), error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return Constans.getHeaders();
            }
        };
        request.setRetryPolicy(Constans.getDefaultRetryPolicy());
        requestQueue.add(request);
    }

    public static int IndexIklan = 4;
    private void addBannerAds() {
        // Loop through the items array and place a new banner ad in every ith position in
        // the items List.

        for (int i = 0; i <= list.size(); i += IndexIklan) {
            List<String> testDevices = new ArrayList<>();
            testDevices.add("33BE2250B43518CCDA7DE426D04EE231");
            RequestConfiguration requestConfiguration
                    = new RequestConfiguration.Builder()
                    .setTestDeviceIds(testDevices)
                    .build();
            MobileAds.setRequestConfiguration(requestConfiguration);
            final AdView adView = new AdView(getContext());
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId(getString(R.string.ads_app_id));
            list.add(i, adView);
        }
        refreshLayout.setRefreshing(false);
    }
    @Override
    public void onItemClicked(int position, BarangModel item) {

    }

    @Override
    public void onRefresh() {
        addData(0);
    }
}