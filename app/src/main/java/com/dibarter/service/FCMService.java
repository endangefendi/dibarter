package com.dibarter.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.text.Html;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.dibarter.activity.MainActivity;
import com.dibarter.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

public class FCMService extends FirebaseMessagingService {
    private static final String TAG = "FCMService";
    private static final String TITLE = "title";
    private static final String EMPTY = "";
    private static final String MESSAGE = "message";
    private static final String IMAGE = "image";
    private static final String ACTION = "action";
    private static final String ID_PESAN = "id_pesan";
    private static final String DATA = "data";
    private static final String ACTION_DESTINATION = "action_destination";
    private static final String ID = "id";
    private static final String BODY = "body";
    private static final String STREAM_ID = "stream_id";
    private static final String ID_PESANAN = "id_pesanan";
    private static final String KODE = "kode";
    private static final String ID_PRODUK = "id_produk";
    private static final String KODE_NOTIF = "kode_notif";


    private static final String TAG1 = "MyFirebaseIdService";
    private static final String TOPIC_GLOBAL = "global";


    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG1, "Refreshed token: " + refreshedToken);

        // now subscribe to `global` topic to receive app wide notifications
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_GLOBAL);

        sendRegistrationToServer(refreshedToken);
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
  }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        onTokenRefresh();
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            Map<String, String> data = remoteMessage.getData();
            handleData(data);

        } else if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            handleNotification(remoteMessage.getNotification());
        }
        // Check if message contains a notification payload.

    }

    private void handleNotification(RemoteMessage.Notification RemoteMsgNotification) {
        String message = RemoteMsgNotification.getBody();
        String title = RemoteMsgNotification.getTitle();
        NotificationVO notificationVO = new NotificationVO();
        notificationVO.setTitle(title);
        notificationVO.setMessage(message);

        Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
        NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
        notificationUtils.displayNotification(notificationVO, resultIntent);
    }

    private void handleData(Map<String, String> data) {
        String title = data.get(TITLE);
        String message = data.get(MESSAGE);
        String iconUrl = data.get(IMAGE);
        String id = data.get(ID);
        String body = data.get(BODY);
        String stream_id = data.get(STREAM_ID);
        String id_pesanan = data.get(ID_PESANAN);
        String id_produk = data.get(ID_PRODUK);
        String kode = data.get(KODE);
        String kode_notif = data.get(KODE_NOTIF);

        NotificationVO notificationVO = new NotificationVO();
        notificationVO.setTitle(title);
        notificationVO.setMessage(message);
        notificationVO.setIconUrl(iconUrl);
        Intent resultIntent = null;
        NotificationManager notificationManager = (NotificationManager)
                getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        //1 = pending payment
        //2 = sukses payment
        //3 = expired payment
        //4 = pesanan dikirim (NewDetailHistoriActivity)
        //5 = pesanan sampai/selesai (NewDetailHistoriActivity)
        //6 = pesanan cancel
        //7 = new product (DetailProdukActivity)
        //8 = promo
        //9 = link external
        //10 = pesan text
        //11 = list status transaksi (TransaksiActivity)
        //12 = detail transaksi (DetailTransaksiStatusActivity)
        //13 = histori order (HistoryActivity)
        //selain kode di atas (HomeActivity)
        if (kode_notif != null) {
            if (kode_notif.equalsIgnoreCase("1") || kode_notif.equalsIgnoreCase("2")
                    || kode_notif.equalsIgnoreCase("3")||
                    kode_notif.equalsIgnoreCase("6") ) {
//                resultIntent = new Intent(getApplicationContext(), DetailHistoriActivity.class);
                resultIntent.putExtra("NotifId", "com.google.firebase.MESSAGING_EVENT");
                resultIntent.putExtra("id_pesanan", id_pesanan);
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            } else if (kode_notif.equalsIgnoreCase("7")) {
//                resultIntent = new Intent(getApplicationContext(), DetailProdukActivity.class);
                resultIntent.putExtra("NotifId", "com.google.firebase.MESSAGING_EVENT");
                resultIntent.putExtra("id_produk", Integer.parseInt(id_produk));
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }else if (kode_notif.equalsIgnoreCase("5")  ) {
//                resultIntent = new Intent(getApplicationContext(), NewDetailHistoriActivity.class);
                resultIntent.putExtra("NotifId", "com.google.firebase.MESSAGING_EVENT");
                resultIntent.putExtra("id_pesanan", id_pesanan);
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }else if (kode_notif.equalsIgnoreCase("4")  ) {
//                resultIntent = new Intent(getApplicationContext(), NewDetailHistoriActivity.class);
                resultIntent.putExtra("NotifId", "com.google.firebase.MESSAGING_EVENT");
                resultIntent.putExtra("id_pesanan", id_pesanan);
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }else if (kode_notif.equalsIgnoreCase("9")  ) {
                resultIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(kode));
                resultIntent.putExtra("NotifId", "com.google.firebase.MESSAGING_EVENT");
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }else if (kode_notif.equalsIgnoreCase("8") || kode_notif.equalsIgnoreCase("10")  ) {
//                resultIntent = new Intent(getApplicationContext(), DetailPesanActivity.class);
                resultIntent.putExtra("id_notif", kode);
                resultIntent.putExtra("NotifId", "com.google.firebase.MESSAGING_EVENT");
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }else if (kode_notif.equalsIgnoreCase("11")  ) {
//                resultIntent = new Intent(getApplicationContext(), TransaksiActivity.class);
                resultIntent.putExtra("NotifId", "com.google.firebase.MESSAGING_EVENT");
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }else if (kode_notif.equalsIgnoreCase("12")  ) {
//                resultIntent = new Intent(getApplicationContext(), DetailTransaksiStatusActivity.class);
                resultIntent.putExtra("NotifId", "com.google.firebase.MESSAGING_EVENT");
                resultIntent.putExtra("id_pesanan", id_pesanan);
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }else if (kode_notif.equalsIgnoreCase("13")  ) {
//                resultIntent = new Intent(getApplicationContext(), HistoryActivity.class);
                resultIntent.putExtra("NotifId", "com.google.firebase.MESSAGING_EVENT");
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }else {
//                resultIntent = new Intent(getApplicationContext(), HomeActivity.class);
                resultIntent.putExtra("NotifId", "com.google.firebase.MESSAGING_EVENT");
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
        }

        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), m, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Bitmap bigIcon = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                R.drawable.ic_logo);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_logo);
        builder.setContentTitle(title);
        builder.setContentText(Html.fromHtml(message));

        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setLargeIcon(bigIcon);
        builder.setSound(alarmSound);
        builder.setAutoCancel(true);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            String channelId = "com.racikafalah";
            builder.setChannelId(channelId);
            NotificationChannel mChannel = new NotificationChannel(channelId, "racikafalah", importance);
            notificationManager.createNotificationChannel(mChannel);
        }
        notificationManager.notify(m, builder.build());
    }


}

