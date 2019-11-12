package com.github.wkigen.asm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.wkigen.trace.TraceManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TraceManager.getInstance().startTrace();
    }
}
