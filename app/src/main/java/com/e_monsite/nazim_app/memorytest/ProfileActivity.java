package com.e_monsite.nazim_app.memorytest;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class ProfileActivity extends ActionBarActivity {

    float average=0,max=0;

    String title="",comment="";
    TextView scoreText=null,Title=null,commentText=null;
    MediaPlayer themeSong=null;
    ImageButton rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bestScoreSave();

        rate=(ImageButton)findViewById(R.id.circle);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, " Rate and Comment my Apps :p  ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse("market://details?id=com.e_monsite.nazim_app.binazim"));
                if (!MyStartActivity(intent)) {

                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.e_monsite.nazim_app.binazim"));
                    if (!MyStartActivity(intent)) {

                        Toast.makeText(ProfileActivity.this, "Could not open Android market, please install the market app.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        themeSong = MediaPlayer.create(ProfileActivity.this, R.raw.daft);

        themeSong.start();
        themeSong.setScreenOnWhilePlaying(true);
        themeSong.setLooping(true);

        average=getIntent().getFloatExtra("AVERAGE",0);
        if(average>max) max=average;

        scoreText=(TextView)findViewById(R.id.score);


      if(max==0) {

          title="none";
          comment="none";
      }
        else if(0<max && max<=100) {
      title="Alzheimer";
      comment="We say that Goldfishes forget their existence every minute...Looks like you are pretty close from that.";
      }

       else if (100<max && max<=200) {
      title="At least you tried";
      comment="Seriously ? are you brain damaged ?";

      }
       else if (200<max && max<=300) {

      title="Just average";
       comment="You are at the level of most people,practise to fill the gap and move to the next stage.";
      }
       else if(300<max && max<=400) {

         title="Elephant Memory";
          comment="If memory was weights,you would easily lift one hundred kgs with one hand.Congrats Musclor !";

      }
      else if(400<max && max<=500) {

          title="Computer";
          comment="Insane ! your memory turns to be a gift don't waste it !";
      }

       else if(500<max ) {
         title="Cyborg";
          comment="Inhuman ! you are from a superior level.Only a bunch of people in the world can match with your memory";
      }

         Title=(TextView)findViewById(R.id.title);

        commentText=(TextView)findViewById(R.id.text2);


        Title.setText("Title : "+title);
        scoreText.setText("Best score : "+max);
      commentText.setText("Comment : "+comment);

    }
    private boolean MyStartActivity(Intent aIntent) {
        try
        {
            startActivity(aIntent);
            return true;
        }
        catch (ActivityNotFoundException e)
        {
            return false;
        }
    }
    public void bestScoreSave(){

        SharedPreferences load = getSharedPreferences("save",0);
           max = load.getFloat("max",0);
    }


   public void save(){
           SharedPreferences save = getSharedPreferences("save", 0);
           SharedPreferences.Editor editor = save.edit();
           editor.putFloat("max", max);
           editor.commit();
       }
    protected void onPause(){
        super.onPause();
        themeSong.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!themeSong.isPlaying()) themeSong.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        save();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        if(item.getItemId()==R.id.about) {

    AlertDialog.Builder builder =new AlertDialog.Builder(ProfileActivity.this);
            builder.setCancelable(false).setNeutralButton("Back",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.cancel();
                }
            }).setTitle("Credit").setMessage("Developed By Bellabaci Nazim ,Designed By Yanis Yefsah").show();
        }
            return super.onOptionsItemSelected(item);
    }
}
