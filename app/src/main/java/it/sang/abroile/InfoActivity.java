package it.sang.abroile;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class InfoActivity extends AppCompatActivity
{
    private static final String TAG = "REVA";
    CardView cardView;
    LinearLayout linearLayout;
    ScrollView Smain, Ssang;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Tentang ABroile");

        cardView = findViewById(R.id.cardInfoMe);
        linearLayout = findViewById(R.id.viewBG);
        Smain = findViewById(R.id.scrollMain);
        Ssang = findViewById(R.id.scrollMe);

        cardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (v.getId() == R.id.cardInfoMe) {
                        revealYellow(event.getRawX(), event.getRawY());
                        Log.d(TAG, "onTouch: 1");
                    }
                }
                return false;
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void revealYellow(float x, float y) {
        animateRevealColorFromCoordinates(linearLayout, R.color.sang, (int) x, (int) y);
        layoutMe();
    }

    private Animator animateRevealColorFromCoordinates(ViewGroup viewRoot, @ColorRes int color, int x, int y) {
        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
        viewRoot.setBackgroundColor(getApplicationContext().getColor(color));
        anim.setDuration(300);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
        return anim;
    }
    private void layoutMe(){
        InfoActivity.this.getApplicationContext().setTheme(R.style.AboutME);
        Smain.setVisibility(View.GONE);
        Ssang.setVisibility(View.VISIBLE);
        setTitle("Tentang Saya");
    }
}