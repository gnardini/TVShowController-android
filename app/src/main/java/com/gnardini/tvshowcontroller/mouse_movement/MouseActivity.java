package com.gnardini.tvshowcontroller.mouse_movement;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.gnardini.tvshowcontroller.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MouseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mouse_activity);
        ButterKnife.bind(this);
    }

    @OnTouch(R.id.mouse_input)
    void onMouseMoved(View view, MotionEvent motionEvent) {

    }

    @OnClick(R.id.left_click)
    void onLeftClick() {

    }

    @OnClick(R.id.right_click)
    void onRightClick() {

    }

    private Callback<String> emptyCallback = new Callback<String>() {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
        }
    };


}
