package com.e_monsite.nazim_app.memorytest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class numericalMemory extends Activity {

    TextView textView=null,scoreText=null,countText=null,toastText=null;
    int a=0,b=0,c=0,w=0,count=1,i=1,score=0,countDown=3;
    int step1=0;
    MediaPlayer mp=null,correctMedia=null;
    Boolean soundTag;

    Button button =null;
    EditText editText=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical_memory);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        textView=(TextView)findViewById(R.id.serie);
        countText=(TextView)findViewById(R.id.count);
        scoreText=(TextView)findViewById(R.id.score);
        editText=(EditText)findViewById(R.id.edit);
        button=(Button)findViewById(R.id.button);
        randomize();

        step1=getIntent().getIntExtra("STEP1",0);
        soundTag=getIntent().getBooleanExtra("TAG",false);

        if(!soundTag) {

            mp = MediaPlayer.create(numericalMemory.this, R.raw.daft);
            mp.start();
            mp.setLooping(true);
        }


        editText.setEnabled(false);

        numberupdate();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(count==1) w=a;
                else if(count==2) w=b;
                else if(count==3) w=c;

          if(editText.getText().toString().equals("") )editText.setText("0");

           int x=Integer.parseInt(editText.getText().toString());

              if(x==w) {

                  score =score+ (50*i);
                  scoreText.setText(""+score);
                 customToast();
              }

            else {
                  Toast.makeText(numericalMemory.this, "WRONG", Toast.LENGTH_SHORT).show();
                  scoreText.setText(""+score);

                  Intent i=new Intent(numericalMemory.this,step3.class);
                  i.putExtra("STEP1",step1);
                  i.putExtra("STEP2",score);
                  i.putExtra("TAG",soundTag);
                  startActivity(i);

                }
                 count++;
                if(count>3){
                    count=1;
                    i++;
                    countDown=3;
                    randomize();
                    numberupdate();
                }

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
        finish();

    }

    public void randomize(){

       a= (int) (Math.random()*(int)(Math.pow(10,i))-1);
       b= (int) (Math.random()*(int)(Math.pow(10,i))-1);
       c= (int) (Math.random()*(int)(Math.pow(10,i))-1);

       textView.setText(" "+a+" "+b+" "+c);
   }


  public void numberupdate() {


          new Thread(new Runnable() {
              @Override
              public void run() {

                  while (countDown > -1) {

                      numericalMemory.this.runOnUiThread(new Runnable() {
                          @Override
                          public void run() {


                              countText.setText("" + countDown--);

                              if (countDown <0)

                              {
                                 editText.setEnabled(true);
                                  textView.setText("");

                              }

                          }
                      });
                      try {
                          Thread.sleep(2000);


                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }

                  }
              }
          }).start();
      }

    private void runOnThread(){

        countText.setText(""+countDown--);
}

    public void customToast(){


        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.toast_layout_id));
        toastText = (TextView) layout.findViewById(R.id.tvtoast);
        toastText.setText(" + 50 ! ");
        toastText.setTextColor(Color.WHITE);
        toastText.setTextSize(30);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);

        correctMedia= MediaPlayer.create(numericalMemory.this,R.raw.correct);
        correctMedia.start();

        toast.show();

        if(!correctMedia.isPlaying()) correctMedia.release();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(numericalMemory.this);
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