package com.dibarter.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

public class Account implements Parcelable {
    public long id;
    public String nama;
    public String foto;
    public int provinsi;
    public int kabkota;
    public int kec;
    public String provinsi_nama;
    public String kabkot_nama;
    public String kec_nama;
    public String kabkot_type;
    public String alamat;
    public String kodepos;
    public String token;
    public String email;
    public String hp;
    public boolean isLogin;

    public Account() {
    }

    protected Account(Parcel in) {
        id = in.readLong();
        provinsi_nama = in.readString();
        kabkot_nama = in.readString();
        kec_nama = in.readString();
        kabkot_type = in.readString();
        nama = in.readString();
        email = in.readString();
        kabkota = in.readInt();
        kec = in.readInt();
        provinsi = in.readInt();
        foto = in.readString();
        alamat = in.readString();
        kodepos = in.readString();
        token = in.readString();
        hp = in.readString();
        isLogin = in.readByte() != 0;
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(nama);
        parcel.writeString(email);
        parcel.writeInt(kabkota);
        parcel.writeInt(kec);
        parcel.writeInt(provinsi);
        parcel.writeString(foto);
        parcel.writeString(alamat);
        parcel.writeString(kodepos);
        parcel.writeString(token);
        parcel.writeString(hp);
        parcel.writeString(provinsi_nama);
        parcel.writeString(kec_nama);
        parcel.writeString(kabkot_nama);
        parcel.writeString(kabkot_type);
        parcel.writeByte((byte) (isLogin ? 1 : 0));

    }

    public static Account parseJsonToAccount(JSONObject response, boolean isLogin){
        Account account = new Account();
        try{
            JSONObject data = response.getJSONObject("data");
            account.id = Long.parseLong(data.getString("id"));
            account.provinsi = Integer.parseInt(data.getString("provinsi"));
            account.kabkota = Integer.parseInt(data.getString("kabkota"));
            account.kec = Integer.parseInt(data.getString("kec"));

            account.provinsi_nama = data.getString("provinsi_nama");
            account.kabkot_nama = data.getString("kabkot_nama");
            account.kabkot_type = data.getString("kabkot_type");
            account.kec_nama = data.getString("kec_nama");

            account.nama = data.getString("nama");
            account.email = data.getString("email");
            account.foto = data.getString("foto");
            account.alamat = data.getString("alamat");
            account.kodepos = data.getString("kodepos");
            account.token = data.getString("token");
            account.hp = data.getString("hp");
            account.isLogin = isLogin;

        }catch (JSONException e){
            e.printStackTrace();
        }
        return account;
    }

    public static class PREF{
        public static final String PROVINSI_NAMA = "com.dibarter.model.Account.PROVINSI_NAMA";
        public static final String KEC_NAMA = "com.dibarter.model.Account.KEC_NAMA";
        public static final String KABKOT_NAMA = "com.dibarter.model.Account.KABKOT_NAMA";
        public static final String KABKOT_TYPE = "com.dibarter.model.Account.KABKOT_TYPE";

        public static final String ID = "com.dibarter.model.Account.ID";
        public static final String KEC = "com.dibarter.model.Account.KEC";
        public static final String PROVINSI = "com.dibarter.model.Account.PROVINSI";
        public static final String KABKOTA = "com.dibarter.model.Account.KABKOTA";
        public static final String NAMA = "com.dibarter.model.Account.NAMA";
        public static final String EMAIL = "com.dibarter.model.Account.EMAIL";
        public static final String FOTO = "com.dibarter.model.Account.FOTO";
        public static final String ALAMAT = "com.dibarter.model.Account.ALAMAT";
        public static final String KODEPOS = "com.dibarter.model.Account.KODEPOS";
        public static final String TOKEN = "com.dibarter.model.Account.TOKEN";
        public static final String HP = "com.dibarter.model.Account.PHONE_NUMBER";
        public static final String IS_LOGIN = "com.dibarter.model.Account.IS_LOGIN";
    }


    public void savePreference(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putLong(PREF.ID, id)
                .putInt(PREF.KEC, kec)
                .putInt(PREF.KABKOTA, kabkota)
                .putInt(PREF.PROVINSI, provinsi)
                .putString(PREF.PROVINSI_NAMA, provinsi_nama)
                .putString(PREF.KABKOT_NAMA, kabkot_nama)
                .putString(PREF.KABKOT_TYPE, kabkot_type)
                .putString(PREF.KEC_NAMA, kec_nama)
                .putString(PREF.NAMA, nama)
                .putString(PREF.EMAIL, email)
                .putString(PREF.FOTO, foto)
                .putString(PREF.ALAMAT, alamat)
                .putString(PREF.KODEPOS, kodepos)
                .putString(PREF.TOKEN, token)
                .putString(PREF.HP, hp)
                .putBoolean(PREF.IS_LOGIN, isLogin)
                .apply();
    }

    public static void deletePreference(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putLong(PREF.ID, 0)
                .putInt(PREF.PROVINSI, 0)
                .putInt(PREF.KABKOTA, 0)
                .putInt(PREF.KEC, 0)
                .putString(PREF.KABKOT_NAMA, null)
                .putString(PREF.KABKOT_TYPE, null)
                .putString(PREF.KEC_NAMA, null)
                .putString(PREF.PROVINSI_NAMA, null)
                .putString(PREF.NAMA, null)
                .putString(PREF.EMAIL, null)
                .putString(PREF.FOTO, null)
                .putString(PREF.ALAMAT, null)
                .putString(PREF.KODEPOS, null)
                .putString(PREF.TOKEN, null)
                .putString(PREF.HP, null)
                .putBoolean(PREF.IS_LOGIN, false)
                .apply();
    }


    public static Account getAccountPreference(Context context){
        if (context == null) return null;
        Account account = new Account();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        account.id = sp.getLong(PREF.ID, 0);
        account.kec = sp.getInt(PREF.KEC, 0);
        account.provinsi = sp.getInt(PREF.PROVINSI, 0);
        account.kabkota = sp.getInt(PREF.KABKOTA, 0);
        account.provinsi_nama = sp.getString(PREF.PROVINSI_NAMA, null);
        account.kabkot_nama = sp.getString(PREF.KABKOT_NAMA, null);
        account.kabkot_type = sp.getString(PREF.KABKOT_TYPE, null);
        account.kec_nama = sp.getString(PREF.KEC_NAMA, null);
        account.nama = sp.getString(PREF.NAMA, null);
        account.email = sp.getString(PREF.EMAIL, null);
        account.foto = sp.getString(PREF.FOTO, null);
        account.alamat = sp.getString(PREF.ALAMAT, null);
        account.kodepos = sp.getString(PREF.KODEPOS, null);
        account.token = sp.getString(PREF.TOKEN, null);
        account.hp = sp.getString(PREF.HP, null);
        account.isLogin = sp.getBoolean(PREF.IS_LOGIN, false);
        return account;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", email='" + email + '\'' +
                ", provinsi_nama=" + provinsi_nama +
                ", kabkot_nama=" + kabkot_nama +
                ", kabkot_type=" + kabkot_type+
                ", kec_nama=" + kec_nama+
                ", kec=" + kec +
                ", kabkota=" + kabkota +
                ", provinsi=" + provinsi+
                ", foto='" + foto + '\'' +
                ", alamat='" + alamat + '\'' +
                ", kodepos='" + kodepos + '\'' +
                ", token='" + token + '\'' +
                ", phoneNumber='" + hp + '\'' +
                ", isLogin=" + isLogin +
                '}';
    }
}

