package com.ontob.transitionanimationdemo;

import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TransitionAnimationActivity extends AppCompatActivity {

    private Scene mDverViewScene;
    private Scene mInfoScene;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition1);
        ViewGroup scene = findViewById(R.id.scene_root);
        //参数1，存放场景的容器的view，参数2，需要跳转到的场景的资源布局，参数3，上下文
        mDverViewScene = Scene.getSceneForLayout(scene, R.layout.scene_overview, getBaseContext());
        mInfoScene = Scene.getSceneForLayout(scene, R.layout.scene_info, getBaseContext());
        TransitionManager.go(mDverViewScene);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInfo:
               // TransitionManager.go(mInfoScene);
                Transition transition = TransitionInflater.from(getBaseContext())
                        .inflateTransition(R.transition.transition);
                TransitionManager.go(mInfoScene, transition);//参数，1，要跳转到的场景，2，动画
                break;
            case R.id.btnClose:
                TransitionManager.go(mDverViewScene);
                break;
        }
    }

}
