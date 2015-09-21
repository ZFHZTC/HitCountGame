package com.example.nicholas.newbuttonandges;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;


public class MainActivity extends Activity {

    private int count;
    private MyCountDownTimer Mytimer;
    private StartTimer ThreeSecTimer;
    public boolean timerHasStarted = false;
    public boolean SecHasEnded = false;
    private final long starttime = 10000;
    private final long interval=1000;
    private final long threesec= 3000;
    private TextView TimerText;
    private TextView CountText;
    private TextView CountButtonText;
    private Button Start;
    private ImageButton Count;
    private Button Reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Start = (Button)this.findViewById(R.id.StartButton);
        Count= (ImageButton)this.findViewById(R.id.CountButton);
        Reset= (Button)this.findViewById(R.id.ResetButton);
        CountText =(TextView)this.findViewById(R.id.Countext);
        CountButtonText=(TextView)this.findViewById(R.id.Buttontext);
        TimerText= (TextView)this.findViewById(R.id.Timertext);
        Mytimer = new MyCountDownTimer(starttime,interval);
        ThreeSecTimer = new StartTimer(threesec, interval);

        Start.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick (View v) {
                        Mytimer.cancel();
                        ThreeSecTimer.cancel();
                        ThreeSecTimer.start();
                        count=0;


//                        if (SecHasEnded){
//                            Mytimer.start();
//                            count=0;
//                            CountText.setText("You hit " + count + " times!");
//                            timerHasStarted = true;
//                            CountButtonText.setText("HIT");
//
//                            Count.setOnClickListener(
//                                    new Button.OnClickListener() {
//                                        public void onClick(View v) {
//                                            if (timerHasStarted) {
//                                                count++;
//                                                CountText.setText("You hit " + count + " times!");
//                                                CountButtonText.setText("OUCH!");
//                                            }
//                                        }
//                                    }
//                            );
//                        }
                 }
    }

    );

    Reset.setOnClickListener(
            new Button.OnClickListener()

            {
                public void onClick(View v) {
                    Mytimer.cancel();
                    timerHasStarted = false;
                    count = 0;
                    TimerText.setText(" ");
                    CountText.setText("Press START to play again!");
                    CountButtonText.setText("HIT");

                }
            }
    );

        Count.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        //Count.setText("No holding button!");
                        CountText.setText("No holding button!");
                        return false;
                    }
                }
        );

    }

    public class StartTimer extends CountDownTimer{

        public StartTimer(long startTime, long interval)
        {
            super(startTime, interval);
        }
        @Override
        public void onTick(long millisUntilFinished)
        {
            TimerText.setText( "Game starts in " + millisUntilFinished / 1000 );
        }

        @Override
        public void onFinish()
        {
//            TimerText.setText("TIME'S UP!");
//            CountText.setText("You hit "+count + " times in " +starttime/1000 + " seconds!");
//            CountButtonText.setText("DONE");
           // SecHasEnded = true;

            Mytimer.start();
            CountText.setText("You hit " + count + " times!");
            timerHasStarted = true;
            CountButtonText.setText("HIT");

            Count.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            if (timerHasStarted) {
                                count++;
                                CountText.setText("You hit " + count + " times!");
                                CountButtonText.setText("OUCH!");
                            }
                        }
                    }
            );

        }
    }

    public class MyCountDownTimer extends CountDownTimer{
        public MyCountDownTimer(long startTime, long interval)
        {
            super(startTime, interval);
        }

        @Override
        public void onTick(long millisUntilFinished)
        {
            TimerText.setText( millisUntilFinished / 1000 + " seconds left");
        }

        @Override
        public void onFinish()
        {
            TimerText.setText("TIME'S UP!");
            CountText.setText("You hit "+count + " times in " +starttime/1000 + " seconds!");
            CountButtonText.setText("DONE");
            timerHasStarted = false;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
