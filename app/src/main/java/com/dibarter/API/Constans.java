package com.dibarter.API;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.dibarter.model.Account;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.dibarter.activity.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Endang Efendi on Jan 2021.
 */

public class Constans {
    public static class BASE_URL{
        //Server Developer
        private static final String URL              = "http://production.dibarter.com/apiapp/";

        //Server Production
//        private static final String URL              = "";

        public static final String open_html        = "<!DOCTYPE html><html><head><style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_res/font/avenir_medium_09.ttf\")}body {font-family: MyFont;font-size: 14px;text-align: justify; color: #404040; line-height: 26px;}</style></head><body>";
        public static final String close_html =     "</body></html>";

        public static final String GET_ITEM_LISTS   = URL+"items/lists/";
        public static final String GET_SUGGEST_CARI = URL+"items/suggestion";


        private static final String GET_PROFILE      = URL+"profil/detail";
        private static final String KEY              = "D1b4rt3RD0tC0M";
        private static final String CLIENT_SERVICE   = "frontend-client";
    }

    public static Gson getGson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson;
    }

    public static DefaultRetryPolicy getDefaultRetryPolicy(){
        return new DefaultRetryPolicy(40000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Client-Service", BASE_URL.CLIENT_SERVICE);
        headers.put("Auth-Key", BASE_URL.KEY);
        return headers;
    }

    public static Intent newIntent(Context ctx) {
        Intent intent = new Intent(ctx, LoginActivity.class);
        ctx.startActivity(intent);
        Account.deletePreference(ctx);
        return intent;
    }

    public static Map<String, String> getHeadersDAta(Context context) {
        Account account = Account.getAccountPreference(context);
        Map<String, String> headers = new HashMap<>();
        headers.put("Auth-Key", BASE_URL.KEY);
        headers.put("Client-Service", BASE_URL.CLIENT_SERVICE);
        headers.put("User-ID", String.valueOf(account.id));
        headers.put("Authorization", account.token);
        return headers;
    }

    public static Map<String, String> getHeaders(Context context) {
        Account account = Account.getAccountPreference(context);
        Map<String, String> headers = new HashMap<>();
        headers.put("Auth-Key", BASE_URL.KEY);
        headers.put("Client-Service", BASE_URL.CLIENT_SERVICE);
        headers.put("User-ID", String.valueOf(account.id));
        return headers;
    }

    public static void parseError(Context context, VolleyError error){
        if (context == null || error == null)return;
        if (error instanceof NetworkError){
//            Log.e(String.valueOf(context), "Network issue, tejadi kesalahan jaringan, periksa koneksi internet anda");
            Toast.makeText(context, "Network issue, tejadi kesalahan jaringan, periksa koneksi internet anda", Toast.LENGTH_LONG).show();
        }else if (error instanceof NoConnectionError){
//            Log.e(String.valueOf(context), "Network issue, tidak ada koneksi internet, periksa koneksi internet anda");
            Toast.makeText(context, "Network issue, tidak ada koneksi internet, periksa koneksi internet anda", Toast.LENGTH_LONG).show();
        }else if (error instanceof ServerError){
//            Log.e(String.valueOf(context), "Server issue, Kesalahan Merespon");
            Toast.makeText(context, "Server issue, Kesalahan Merespon", Toast.LENGTH_LONG).show();
        }else if (error instanceof TimeoutError){
//            Log.e(String.valueOf(context), "Network issue, Connections timeout");
            Toast.makeText(context, "Network issue, Connections timeout", Toast.LENGTH_LONG).show();
        }else if (error instanceof ParseError){
//            Log.e(String.valueOf(context), "Kesalahan Parsing");
            Toast.makeText(context, "Kesalahan Parsing", Toast.LENGTH_LONG).show();
        }else if (error instanceof AuthFailureError){
//            Log.e(String.valueOf(context), "kesalahan autentikasi");
            Toast.makeText(context, "kesalahan autentikasi", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context, "Opps, Something wrong!", Toast.LENGTH_LONG).show();
        }
    }

}
