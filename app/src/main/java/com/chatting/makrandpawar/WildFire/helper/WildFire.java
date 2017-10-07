package com.chatting.makrandpawar.WildFire.helper;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;


public class WildFire extends Application {
    private DatabaseReference databaseReference;

    @Override
    public void onCreate() {

        super.onCreate();
        // enable firebase and picasso offline sync
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(false);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);
        if (FirebaseAuth.getInstance().getCurrentUser()!=null) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
            databaseReference.child("statusonline").onDisconnect().setValue(ServerValue.TIMESTAMP);
        }
    }

}
