package com.example.changetheme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.motion.widget.Animatable;

import android.animation.Animator;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    LinearLayout linearLayout;
    TextView textView;
    Button button;
    boolean isDark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        linearLayout = findViewById(R.id.container);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.txt);
        button.setOnClickListener(v -> {
         setTheme(null);
        });
        isDark=false;
    }


    public void setTheme(Resources.Theme theme) {
        if (imageView.getVisibility()== View.VISIBLE) {
//            return;
        }

        int w = linearLayout.getWidth();
        int h = linearLayout.getHeight();

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        linearLayout.draw(canvas);

        imageView.setImageBitmap(bitmap);
//        imageView.setImageResource(R.drawable.ic_launcher_background);
        imageView.setVisibility(View.VISIBLE);

        float finalRadius = (float) Math.hypot((float)w,(float)h);

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        if (isDark){
            linearLayout.setBackgroundColor(getResources().getColor(R.color.white));
            textView.setTextColor(getResources().getColor(R.color.black));
            isDark=false;
        }
        else {
            linearLayout.setBackgroundColor(getResources().getColor(R.color.black));
            textView.setTextColor(getResources().getColor(R.color.white));
            isDark=true;
        }
//        linearLayout.animate().translationX(200).setDuration(1000);
        Animator anim = ViewAnimationUtils.createCircularReveal(linearLayout, button.getLeft()+button.getWidth()/2, button.getTop()+button.getHeight()/2, 0f, finalRadius);
        anim.setDuration(400L);
        anim.addPauseListener(new Animator.AnimatorPauseListener() {
            @Override
            public void onAnimationPause(Animator animation) {
                imageView.setImageDrawable(null);
                imageView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationResume(Animator animation) {
                imageView.setImageDrawable(null);
                imageView.setVisibility(View.GONE);
            }
        });
        anim.start();
    }
}