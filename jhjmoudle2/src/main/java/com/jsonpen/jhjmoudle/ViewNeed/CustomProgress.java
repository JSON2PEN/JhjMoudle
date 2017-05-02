package com.jsonpen.jhjmoudle.ViewNeed;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jsonpen.jhjmoudle.R;


/**
 * 自定义加载进度条
 */
public class CustomProgress extends ProgressDialog {

    private ProgressBar pnImg;
    private TextView tvLoading;
    private String pbText = "";

    public CustomProgress(Context context) {
        super(context, R.style.CustomProgressDialog);
    }

    public CustomProgress(Context context, int theme) {
        super(context, theme);
    }

    public CustomProgress(Context context, String str) {
        super(context, R.style.CustomProgressDialog);
        pbText = str;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        pnImg = (ProgressBar) findViewById(R.id.pb_img);
        tvLoading = (TextView) findViewById(R.id.tv_loading);
        tvLoading.setText(pbText);
    }

}
