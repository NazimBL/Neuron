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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class step3 extends Activity {

    ImageView[] imageArray=new ImageView[10];


    TextView countText=null,scoreText=null,toastText=null;
    int[] code=new int[20];
    int j=0,count=2,loop=0,rando=0,countDown=3,score=0,win=0,error=0,var=1;
    int step1=0,step2=0;
    GridView grid=null;
    MediaPlayer themeSong=null,correctMedia=null;
    boolean tag=false,loopTag=false,soundTag=false;

    public Integer[] mThumbIds = {
            R.drawable.one, R.drawable.two,
            R.drawable.three, R.drawable.four,
            R.drawable.five, R.drawable.six,
            R.drawable.seven, R.drawable.eight,
            R.drawable.ten, R.drawable.nine,
            R.drawable.eleven, R.drawable.twelve,
            R.drawable.treize, R.drawable.quatorze,
            R.drawable.quinze, R.drawable.seize,
            R.drawable.seventeen, R.drawable.eighteen,
            R.drawable.nineteen,R.drawable.twenione,
            R.drawable.twenifive, R.drawable.twenifour,
            R.drawable.tweninine, R.drawable.twenisix,
            R.drawable.twenithree, R.drawable.thirty,
            R.drawable.threeone, R.drawable.threetwo,
            R.drawable.threethree, R.drawable.twenitwo,
            R.drawable.tweniseven, R.drawable.twenieight};

    @Override
    protected void onPause() {
        super.onPause();
        themeSong.pause();
         finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step3);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        countText = (TextView) findViewById(R.id.count);
        scoreText = (TextView) findViewById(R.id.score);


        grid = (GridView) findViewById(R.id.gridview);


        imageArray[0] = (ImageView) findViewById(R.id.img1);
        imageArray[1] = (ImageView) findViewById(R.id.img2);
        imageArray[2] = (ImageView) findViewById(R.id.img3);
        imageArray[3] = (ImageView) findViewById(R.id.img4);
        imageArray[4] = (ImageView) findViewById(R.id.img5);
        imageArray[5] = (ImageView) findViewById(R.id.img6);
        imageArray[6] = (ImageView) findViewById(R.id.img7);
        imageArray[7] = (ImageView) findViewById(R.id.img8);


        step1=getIntent().getIntExtra("STEP1",0);
        step2=getIntent().getIntExtra("STEP2",0);
        soundTag=getIntent().getBooleanExtra("TAG",false);
        if(!soundTag) {

            themeSong = MediaPlayer.create(step3.this, R.raw.daft);
            themeSong.start();
            themeSong.setLooping(true);
        }


        code();
        adapterUpdate();
        numberupdate();

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                loop = 0;
                loopTag=false;
                while (loop < count) {

                    if (code[loop] == i) {
                       customToast();
                        score =score+ (50*var);
                        scoreText.setText(""+score);
                        loopTag=true;
                        win++;

                    }
                    loop++;
                }


                  if(!loopTag)
                  {
                      error++;
                      Toast.makeText(step3.this, "Wrong", Toast.LENGTH_SHORT).show();
                  }


                if(win>=count)

                {
                    error = 0;
                    count++;
                    var++;
                    tag = false;
                    countDown = 3;
                    code();
                    adapterUpdate();
                    numberupdate();
                }

                if(error>=2)  {
                    Intent intent=new Intent(step3.this, ScorePanel.class);
                    intent.putExtra("STEP1",step1);
                    intent.putExtra("STEP2",step2);
                    intent.putExtra("STEP3",score);
                    intent.putExtra("TAG",soundTag);
                    startActivity(intent);

                }

            }

        });


    }



    public int randomize(int i){

    i=0;
    i=(int)(Math.random()*31);

    return i;
}
    public void numberupdate() {

       j=0;
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (countDown > -1) {

                    step3.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            countText.setText("" + countDown--);

                            if (countDown <0) {

                            while (j<count)
                                {
                                imageArray[j].setImageResource(R.drawable.pt_converted);
                                    j++;
                                }
                                tag=true;
                           adapterUpdate();

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

  public void adapterUpdate() {
      if (tag) {
          grid.setAdapter(new ImageAdapter(step3.this));
      }
  }
  public void code(){

      win=0;
      loop=0;
      while (loop < count)
      {
          int v=randomize(rando);
          code[loop]=v;
          imageArray[loop].setImageResource(mThumbIds[v]);
          loop++;

      }
       loop=0;
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

       correctMedia= MediaPlayer.create(step3.this,R.raw.correct);
       correctMedia.start();

       toast.show();

       if(!correctMedia.isPlaying()) correctMedia.release();
   }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(step3.this);
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

