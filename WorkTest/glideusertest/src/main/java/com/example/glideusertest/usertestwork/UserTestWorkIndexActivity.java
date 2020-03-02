package com.example.glideusertest.usertestwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.glideusertest.R;
import com.example.glideusertest.usertestwork.activity.baseglide.BaseGlideActivity;
import com.example.glideusertest.usertestwork.activity.generateapiuutils.GeneratedAPIGlideActivity;
import com.example.glideusertest.usertestwork.activity.optionsutils.OpstionClassGlideActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserTestWorkIndexActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workindaex);
    }

    public void wGlideOnClick(View v) {
        switch (v.getId()) {
            case R.id.w_base_glideBtn:
                startActivity(new Intent(UserTestWorkIndexActivity.this,BaseGlideActivity.class));
                break;
            case R.id.w_classOpstion_glideBtn:
                startActivity(new Intent(UserTestWorkIndexActivity.this,OpstionClassGlideActivity.class));
                break;
            case R.id.w_generatedAPI_netBtn:
                startActivity(new Intent(UserTestWorkIndexActivity.this,GeneratedAPIGlideActivity.class));
                break;
        }
    }
}
