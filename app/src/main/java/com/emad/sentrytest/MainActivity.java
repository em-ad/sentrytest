package com.emad.sentrytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.emad.sentrytest.databinding.ActivityMainBinding;

import java.util.Date;

import io.sentry.Sentry;
import io.sentry.SentryEvent;
import io.sentry.protocol.User;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        User user = new User();
        user.setEmail("emad.mollaei@gmail.com");

        user.setId(Settings.Secure.ANDRgit add ./OID_ID);

        Sentry.setUser(user);
        Sentry.setTag("tag", "gholam");

        Sentry.captureMessage("testing SDK setup");

        try {
            throw new Exception("This is a test.");
        } catch (Exception e) {
            Sentry.captureException(e);
        }

        binding.tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sentry.captureMessage("Start Clicked at " + System.currentTimeMillis());
                Sentry.captureException(new Throwable("setup done"));
                Sentry.addBreadcrumb("crumb1");
                Sentry.captureEvent(new SentryEvent(new Date()));
                long c = Long.parseLong("c");
            }
        });
    }
}