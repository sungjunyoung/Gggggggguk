package com.junyoung.gggggggguk;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private Button button;
    private TextView score;
    private long intScore;
    private Handler mHandler;
    private NumberThread mNumberThread;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intScore = 0;
        button = (Button) findViewById(R.id.button);
        score = (TextView) findViewById(R.id.score);
        mHandler = new Handler();
        intent = new Intent(this, RankingActivity.class);


        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mNumberThread = new NumberThread(true);
                    mNumberThread.start();
                }

                if ( motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    mNumberThread.stopThread();
                    User.setScore(intScore);
                    startActivity(intent);
                }
                return true;

            }
        });


    }


    class NumberThread extends Thread {

        private long i = 0;
        private boolean isPlay = false;

        public NumberThread(boolean isPlay) {
            this.isPlay = isPlay;
        }

        public void stopThread() {
            isPlay = !isPlay;
        }

        @Override
        public void run() {
            super.run();
            while (isPlay) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        intScore = i;
                        score.setText("" + i++);
                    }
                });
            }
        }
    }


}
