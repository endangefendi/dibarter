package com.dibarter.API;

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
        private static final String URL              = "https://production.coodink.com/android_racika/api/";

        //Server Production
//        private static final String URL              = "https://androidappsracikafalah.mastermultijaya.co.id/api/";

        public static final String open_html        = "<!DOCTYPE html><html><head><style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_res/font/avenir_medium_09.ttf\")}body {font-family: MyFont;font-size: 14px;text-align: justify; color: #404040; line-height: 26px;}</style></head><body>";
        public static final String close_html =     "</body></html>";

        public static final String GET_PROVINSI     = URL+"alamat/get_all_provinsi";
        public static final String GET_KOTA         = URL+"alamat/get_by_id_prov_kabkot/";
        public static final String GET_KEC          = URL+"alamat/get_by_id_kabkot_kec/";
        public static final String REGISTRASI       = URL+"auth/register";
        public static final String LOGOUT           = URL+"auth/logout";
        public static final String LOGIN            = URL+"auth/login";
        public static final String GET_ALL_KATEGORI = URL+"produk/get_all_kategori";
        public static final String GET_BANNER_HOME  = URL+"home/slider";
        public static final String GET_ALL_PRODUK_BY_ID  = URL+"produk/get_produk_kategori";
        public static final String GET_DETAIL_PRODUK = URL+"produk/get_produk?id=";
        public static final String CARI_PRODUK_ALL  = URL+"home/search";
        public static final String LINK_DOWNLOAD    = URL+"home/download";
        public static final String GET_ALL_PRODUK   = URL+"produk/get_produk";
        public static final String EDIT_PASSWORD    = URL+"profil/edit_password";
        public static final String EDIT_ALAMAT      = URL+"profil/edit_alamat";
        public static final String EDIT_PROFILE     = URL+"profil/edit_profil";
        public static final String ADD_TO_CART      = URL+"produk/add_to_cart";
        public static final String DELETE_ITEM_CART = URL+"produk/delete_item_cart";
        public static final String UPDATE_ITEM_CART = URL+"produk/update_cart";
        public static final String LIST_CART        = URL+"produk/lists_cart";
        public static final String DELETE_ALL_CART  = URL+"produk/delete_all_cart";
        public static final String DETAIL_CHECKOUT  = URL+"trans/order_detail";
        public static final String CHECKOUT         = URL+"trans/checkout";
        public static final String CHECKOUT2         = URL+"trans/checkout2";
        public static final String CREATE_ALAMAT    = URL+"alamat/create";
        public static final String UPDATE_ALAMAT    = URL+"alamat/update";
        public static final String DELETE_ALAMAT    = URL+"alamat/delete";
        public static final String GET_ALAMAT       = URL+"alamat/get_alamat";
        public static final String GET_ONGKIR       = URL+"trans/get_onkir";
        public static final String ABOUT_APP        = URL+"home/about";
        public static final String TERM_POLICE      = URL+"home/term_and_police";
        public static final String SAVE_PROMO       = URL+"trans/req_promo";
        public static final String HISTORI_PROSES   = URL+"trans/history_proses";
        public static final String ALL_HISTORI_ORDER= URL+"trans/history_all";
        public static final String DETAIL_HISTORI_ORDER= URL+"trans/detail_histori";
        public static final String UPDATE_TOKEN     = URL+"auth/update_token_fcm";
        public static final String SPLASHSCREEN     = URL+"home/get_splashscreen";
        public static final String BANNER_POPUP     = URL+"home/get_banner_popup";
        public static final String CEK_EMAIL_AKUN   = URL+"auth/cek_email";
        public static final String CEK_HP_AKUN      = URL+"auth/cek_hp";
        public static final String PESANAN_PROSES   = URL+"trans/pesanan_proses";
        public static final String PESANAN_PROSES_BAYAR   = URL+"trans/pesanan_proses_bayar";
        public static final String PESANAN_DETAIL   = URL+"trans/pesanan_detail";
        public static final String HISTORI_ORDER    = URL+"trans/history_pesanan";
        public static final String DETAIL_HISTORI_ORDER2  = URL+"trans/detail_history_pesanan";
        public static final String CANCEL_ORDER     = URL+"trans/pesanan_cencel";
        public static final String API_SETTINGS     = URL+"home/get_setting";
        public static final String GET_MESSAGE      = URL+"trans/get_text_checkout";
        public static final String LIST_NOTIF       = URL+"home/get_notif";
        public static final String DETAIL_NOTIF       = URL+"home/detail_notif";


        private static final String GET_PROFILE      = URL+"profil/detail";
        private static final String KEY              = "Racika-Falah";
        private static final String CLIENT_SERVICE   = "frontend-client";
    }

    public static Gson getGson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson;
    }

    public static DefaultRetryPolicy getDefaultRetryPolicy(){
        return new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Auth-Key", BASE_URL.KEY);
        headers.put("Client-Service", BASE_URL.CLIENT_SERVICE);
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


}
