package syd.jjj.notifications;

import android.app.Activity;
import android.app.NotificationChannel;
import android.os.Build;
import android.os.Bundle;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    int notificationID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        Button button = findViewById(R.id.btn_displaynotif);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                displayNotification();
            }
        });
    }

    protected void displayNotification() {

        Intent i = new Intent(this, NotificationView.class);
        i.putExtra("notificationID", notificationID);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, i, 0);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this,"ID")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("You pressed a button")
                .setContentText("Look at you go...")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat nm = NotificationManagerCompat.from(this);
        nm.notify(notificationID, builder.build());

    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("ID", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
