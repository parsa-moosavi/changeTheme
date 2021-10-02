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

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    LinearLayout linearLayout;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        linearLayout = findViewById(R.id.container);
        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
         setTheme(null);
        });
    }


    public void setTheme(Resources.Theme theme) {
        if (imageView.getVisibility()== View.GONE) {
//            return;
        }

        int w = linearLayout.getWidth();
        int h = linearLayout.getHeight();

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        linearLayout.draw(canvas);

        imageView.setImageBitmap(bitmap);
        imageView.setVisibility(View.VISIBLE);

        float finalRadius = (float) Math.hypot(w,h);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        Animator anim = ViewAnimationUtils.createCircularReveal(linearLayout, w / 2, h / 2, 0f, finalRadius);
        anim.setDuration(400L);
//        anim. {
//            imageView.setImageDrawable(null);
//            imageView.setVisibility(View.GONE);
//        }
        anim.start();
    }
}