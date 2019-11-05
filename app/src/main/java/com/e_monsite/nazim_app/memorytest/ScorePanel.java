package com.e_monsite.nazim_app.memorytest;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class ScorePanel extends Activity {


   int step1=0,step2=0,step3=0;
    float average=0;
    Boolean soundTag=false;
    MediaPlayer themeSong=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_panel);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        TextView text=(TextView)findViewById(R.id.score);
        TextView step1Text=(TextView)findViewById(R.id.step1);
        TextView step2Text=(TextView)findViewById(R.id.step2);
        TextView step3Text=(TextView)findViewById(R.id.step3);

        step1= getIntent().getIntExtra("STEP1",0);
        step2=getIntent().getIntExtra("STEP2",0);
        step3=getIntent().getIntExtra("STEP3",0);
        soundTag=getIntent().getBooleanExtra("TAG",false);
        if(!soundTag) {

            themeSong = MediaPlayer.create(ScorePanel.this, R.raw.daft);
            themeSong.start();
            themeSong.setLooping(true);
        }



        if(step1==0) step1Text.setText("Spontaneous  Memory : 0 ? Are you really living on Earth ? You must be an Alien or something like that.");
     else if (50<=step1 && step1<=100) step1Text.setText("Spontaneous Memory : Seems you've been struggling a little bite,not bad though just be more attentive.");
     else if(step1==150) step1Text.setText("Spontaneous Memory : Perfect ! you've got a really nice instantaneous Memory.");


     if(0<=step2 && step2<=150) step2Text.setText("Digit Memory : Either you're doing it on purpose(I hope),or you just have a Goldfish Memory.");
    else if(150<step2 && step2<450) step2Text.setText("Digit Memory : It's OK if you're a 70 years old having Alzheimer.");
    else if (450<=step2 && 750<=step2) step2Text.setText("Digit Memory : You're at an average Memory stage,80% of people have the same score as yours.");
    else if(750<step2 && step2<=900) step2Text.setText("Digit Memory : GG,you are clearly above the normal, exceptional Memory!");
    else if(900<step2) step2Text.setText("Digit Memory : Outstanding ! Respect,just realize you are among 5% of people with this level.");

        if(0<=step3 && step3<100) step3Text.setText("Visual Memory : I just hope that you got something on your eyes or that you accidently clicked on the wrong pictures...");
       else if (100<=step3 && step3<400) step3Text.setText("Visual Memory : Average visual Memory");
       else if(400<=step3 && step3<850) step3Text.setText("Visual Memory : Very Good Visual Memory");
       else if(850<=step3)  step3Text.setText("Visual Memory : AMAZING ! Do you have a Sharingan or something like that ?");

      average=(step1+step2*3+step3*3)/7;

        text.setText("Total Score : "+average);

        Button button=(Button)findViewById(R.id.profile);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent=new Intent(ScorePanel.this,ProfileActivity.class);
                intent.putExtra("AVERAGE",average);
                startActivity(intent);
            }
        });

    }

    protected void onPause(){
        super.onPause();
        themeSong.pause();
       finish();
    }

}
