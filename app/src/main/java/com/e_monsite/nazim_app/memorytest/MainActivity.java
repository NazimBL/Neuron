package com.e_monsite.nazim_app.memorytest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {

    Button start_b = null, rules_b = null,profile_b=null;
    MediaPlayer themeSong=null;
    Bitmap[] array=new Bitmap[5];
    Bitmap bitmap=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        themeSong = MediaPlayer.create(MainActivity.this, R.raw.daft);
        themeSong.start();
        themeSong.setLooping(true);

        ImageView img=(ImageView)findViewById(R.id.id1);
        ImageView img2=(ImageView)findViewById(R.id.id2);
        ImageView img3=(ImageView)findViewById(R.id.id3);
        ImageView img4=(ImageView)findViewById(R.id.id4);
        ImageView img5=(ImageView)findViewById(R.id.id5);



        ImageView[] images=new ImageView[5];

        images[0]=img;
        images[1]=img2;
        images[2]=img3;
        images[3]=img4;
        images[4]=img5;

        start_b = (Button) findViewById(R.id.start_b);
        rules_b = (Button) findViewById(R.id.rules_b);
        profile_b=(Button)findViewById(R.id.profile_b);


        start_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Home.class));
            }
        });
        rules_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RulesActivity.class));
            }
        });

      profile_b.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(MainActivity.this, ProfileActivity.class));
          }
      });


        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.bubble);
        for(int i=0;i<array.length;i++){
            array[i]=Bitmap.createScaledBitmap(bitmap,30-2*i,30-2*i,false);
             images[i].setImageBitmap(array[i]);
        }

        myAnimation((int)(Math.random()*400),(int)(Math.random()*900),images[0]);
        myAnimation((int)(Math.random()*400),(int)(Math.random()*900),images[1]);
        myAnimation((int)(Math.random()*400),(int)(Math.random()*900),images[2]);
        myAnimation((int)(Math.random()*400),(int)(Math.random()*900),images[3]);
        myAnimation((int)(Math.random()*400),(int)(Math.random()*900),images[4]);


    }

    public void myAnimation(int DX,int DY,ImageView view){


    TranslateAnimation animation = new TranslateAnimation(-10, 500, DX, DY);

    animation.setDuration(4000);
    animation.setFillAfter(true);
    animation.setRepeatCount(-1);
    animation.setRepeatMode(Animation.RESTART);
    view.startAnimation(animation);

    }

    @Override
    protected void onPause() {
        super.onPause();
        themeSong.pause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(!themeSong.isPlaying()) themeSong.start();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit Game").setMessage("Are you sur you want to quit ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();

    }

}