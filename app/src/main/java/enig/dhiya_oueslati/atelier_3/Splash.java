package enig.dhiya_oueslati.atelier_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
ImageView imageView;
TextView textView;

Animation teanim , imanim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        imanim = AnimationUtils.loadAnimation(this,R.anim.imageanim);
        teanim = AnimationUtils.loadAnimation(this,R.anim.textanim);

        imageView.setAnimation(imanim);
        textView.setAnimation(teanim);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
             startActivity(new Intent(Splash.this, MainActivity.class));
             finish();
            }
        },3000);
    }
}