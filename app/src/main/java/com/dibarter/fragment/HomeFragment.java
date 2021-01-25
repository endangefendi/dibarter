package com.dibarter.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dibarter.R;
import com.dibarter.adapter.BarangAdapter;
import com.dibarter.model.BarangModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements BarangAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

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
        String[] data = {"ksjdahfjak;","feawjkhfew","flosujfeka","hjgweafiwaf",
                "oneahfewa", "mowaforw", "hfejwafewa","fewalfuewaf",
                "ksjdahfjak;","feawjkhfew","flosujfeka","hjgweafiwaf",
                "oneahfewa", "mowaforw", "hfejwafewa","fewalfuewaf"};


        addData();
    }

    private void addData() {
        list.clear();
        BarangModel item1 = new  BarangModel("ksjdahfjak");
        BarangModel item2 = new  BarangModel("feawjkhfew");
        BarangModel item3 = new  BarangModel("flosujfeka");
        BarangModel item4 = new  BarangModel("ksjdahfjak");
        BarangModel item5 = new  BarangModel("hjgweafiwaf");
        BarangModel item6 = new  BarangModel("ksjdahfjak");
        BarangModel item7 = new  BarangModel("ksjdahfjak");
        BarangModel item8 = new  BarangModel("ksjdahfjak");
        BarangModel item9 = new  BarangModel("ksjdahfjak");
        BarangModel item10 = new  BarangModel("ksjdahfjak");
        BarangModel item11 = new  BarangModel("ksjdahfjak");
        BarangModel item12 = new  BarangModel("ksjdahfjak");
        BarangModel item13 = new  BarangModel("ksjdahfjak");
        BarangModel item14 = new  BarangModel("ksjdahfjak");
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);
        list.add(item6);
        list.add(item7);
        list.add(item8);
        list.add(item9);
        list.add(item10);
        list.add(item11);
        list.add(item12);
        list.add(item13);
        list.add(item14);
        adapter.addList(list);
        addBannerAds();
    }

    private void addBannerAds() {
        // Loop through the items array and place a new banner ad in every ith position in
        // the items List.

        for (int i = 0; i <= list.size(); i += 3) {
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
        addData();
    }
}