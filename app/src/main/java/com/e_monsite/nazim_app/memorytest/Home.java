package com.e_monsite.nazim_app.memorytest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class Home extends ActionBarActivity {


    String[] questionArray=new String[30];
    String[]answers1=new String[30];
    String[]answers2=new String[30];
    String[]correct=new String[30];
    TextView textView=null,scoreText=null,toastText=null;
    int rando=0,score=0,count=0,v=0;
    RadioButton rb1=null,rb2=null;
    Button button =null;
    Boolean songTag=false;
    MediaPlayer themeSong=null,correctMedia=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if(!songTag) {

            themeSong = MediaPlayer.create(Home.this, R.raw.daft);
            themeSong.start();
            themeSong.setLooping(true);
        }


        textView=(TextView)findViewById(R.id.question);
        scoreText=(TextView)findViewById(R.id.score);
        rb1=(RadioButton)findViewById(R.id.radio1);
        rb2=(RadioButton)findViewById(R.id.radio2);
        button=(Button)findViewById(R.id.button);

        questionArray[0]="In Da Vinci's Joconde,which shoulder is clearly nearest ?";
        answers1[0]="Left";answers2[0]="right";


        questionArray[1]="What's the order of traffic lights ?";
        answers1[1]="Red,Orange,Green";answers2[1]="Green,Orange,Red";


        questionArray[2]="What's the clockwise order of colors in Google Chrome logo ?";
        answers1[2]="Yellow,Red,Green";answers2[2]="Red,Yellow,Green";

        questionArray[3]="What are the colors of Belgium flag from left to right";
        answers1[3]="Yellow,Red,Black";answers2[3]="Black,Yellow,Red";

        questionArray[4]="How many white rings does the VLC logo contains";
        answers1[4]="2";answers2[4]="3";

        questionArray[5]="On windows logo which side of the square is greater ?";
        answers1[5]="Left";answers2[5]="Right";

         questionArray[6]="Which pedal is the accelerator in most of te vehicules ?";
         answers1[6]="Left"; answers2[6]="Right";

        questionArray[7]="Which country is nothernmost ?";
        answers1[7]="Bolivia";answers2[7]="Brazil";

        questionArray[8]="Which side are zippers generally located ?";
        answers1[8]="Left"; answers2[8]="Right";

        questionArray[9]="What's the color of a panda belly ?";
        answers1[9]="White"; answers2[9]="Black";

        questionArray[10]="What's the color of the bands in the Football world cup bottom ?";
        answers1[10]="Blue"; answers2[10]="Green";

        questionArray[11]="Which side of the Windows logo is larger ?";
        answers1[11]="Left"; answers2[11]="Right";

        questionArray[12]="Whiche side of your KeyPad is the sharp( # ) located ?";
        answers1[12]="Left"; answers2[12]="Right";

        questionArray[13]="Which one of these is not a Rainbow color ?";
        answers1[13]="Indigo"; answers2[13]="Brown";

        questionArray[14]="With which side of your mouse you use the double click ?";
        answers1[14]="Left"; answers2[14]="Right";

        questionArray[15]="Which country is nothernmost ?";
        answers1[15]="Belgium";answers2[15]="Holland";

        questionArray[16]="What's the color on the left side of Portugal's flag ?";
        answers1[16]="Green";answers2[16]="Red";

        questionArray[17]="Light is on when the higher side of your switch is... "   ;
        answers1[17]="Down";answers2[17]="Up";

        questionArray[18]="Light is off when the higher side of your switch is... "   ;
        answers1[18]="Down";answers2[18]="Up";

        questionArray[19]="If you pull on your Tshirt upside down with your hands in the correct way,in which side is the Tshirt label";
        answers1[19]="Back";answers2[19]="Forward";

        questionArray[20]="If you pull on your Tshirt upside down with your hands in the inverse normal way,in which side is the Tshirt label";
        answers1[20]="Back";answers2[20]="Forward";

        questionArray[21]="How many pairs of legs does a spider have ?";
        answers1[21]="3";answers2[21]="4";

        questionArray[22]="Which color is not present in the Rainbow of Instagram logo ?";
        answers1[22]="Yellow";answers2[22]="Orange";

        questionArray[23]="In Star Wars,what's the color of Dark Vador laser-blade ?";
        answers1[23]="Red";answers2[23]="Green";

        questionArray[24]="What's the color of the (A) button in an Xbox controller ?";
        answers1[24]="Blue";answers2[24]="Green";
        questionArray[25]="What's the color of the (B) button in an Xbox controller ?";
        answers1[25]="Red";answers2[25]="Green";
        questionArray[26]="What's the color of the (Y) button in an Xbox controller ?";
        answers1[26]="Green";answers2[26]="Yellow";

        questionArray[27]="What's the color of the (X) in a Playstation controller's button?";
        answers1[27]="Blue";answers2[27]="Green";

        questionArray[28]="What's the color of the (O) in a Playstation controller's button?";
        answers1[28]="Red";answers2[28]="Pink";

        questionArray[29]="Whiche side of your KeyPad is the star ( * ) located ?";
        answers1[29]="Left"; answers2[29]="Right";


        correct[0]="Left";
        correct[1]="Red,Orange,Green";
        correct[2]="Red,Yellow,Green";
        correct[3]="Black,Yellow,Red";
        correct[4]="2";
        correct[5]="Right";
        correct[6]="Right";
        correct[7]="Brazil";
        correct[8]="Left";
        correct[9]="White";
        correct[10]="Green";
        correct[11]="Right";
        correct[12]="Right";
        correct[13]="Brown";
        correct[14]="Left";
        correct[15]="Holland";
        correct[16]="Green";
        correct[17]="Down";
        correct[18]="Up";
        correct[19]="Back";
        correct[20]="Forward";
        correct[21]="4";
        correct[22]="Orange";
        correct[23]="Red";

        correct[24]="Green";
        correct[25]="Red";
        correct[26]="Yellow";
        correct[27]="Blue";
        correct[28]="Red";
        correct[29]="Left";


       v=randomize(rando);
        textView.setText(questionArray[v]);
        rb1.setText(answers1[v]);
        rb2.setText(answers2[v]);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(rb1.isChecked() && rb1.getText().toString()==correct[v]) {
                    score += 50;
                    scoreText.setText(""+score);
                   customToast();
                }
                else if(rb2.isChecked() && rb2.getText().toString()==correct[v])
                {
                    score+=50;
                    scoreText.setText(""+score);
                   customToast();
                }
                else{
                    scoreText.setText(""+score);
                    Toast.makeText(Home.this,"WRONG",Toast.LENGTH_SHORT).show();
                }

                count++;
                if(count==3)
                {
                    Intent i=new Intent(Home.this, numericalMemory.class);
                    i.putExtra("STEP1",score);
                    i.putExtra("TAG",songTag);
                    startActivity(i);
                }
                else {
                   v= randomize(rando);
                    textView.setText(questionArray[v]);
                    rb1.setText(answers1[v]);
                    rb2.setText(answers2[v]);
                }
            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();
        themeSong.pause();

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if(!themeSong.isPlaying() && !songTag) themeSong.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {


            case R.id.sound: {

                if(!songTag) {
                    themeSong.pause();
                    item.setIcon(R.drawable.mute);
                    songTag = true;
                }

                else{
                    item.setIcon(R.drawable.sound_on);
                    if(!themeSong.isPlaying())themeSong.start();
                    songTag=false;
                }

            }

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public int randomize(int random){

      random=(int)(Math.random()*30);

      return random;
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

        correctMedia= MediaPlayer.create(Home.this,R.raw.correct);
        correctMedia.start();

        toast.show();

        if(!correctMedia.isPlaying()) correctMedia.release();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(Home.this);
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
