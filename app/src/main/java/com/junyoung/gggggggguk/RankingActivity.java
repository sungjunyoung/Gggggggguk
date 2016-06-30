package com.junyoung.gggggggguk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RankingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RankingAdapter mAdapter;
    private List<thisUser> userList = new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        String nickname = User.getNickname();
        long score = User.getScore();
        thisUser user = new thisUser(nickname, score);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new RankingAdapter(userList);

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");



        writeNewRecord(user);
        prepareMessageData();

    }

    private void prepareMessageData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                userList.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    thisUser user = child.getValue(thisUser.class);
                    userList.add(user);
                }

                Collections.sort(userList, new Comparator<thisUser>() {
                    @Override
                    public int compare(thisUser user, thisUser t1) {
                        return (user.getScore() > t1.getScore()) ? -1:(user.getScore()>t1.getScore())?1:0;
                    }
                });

                mAdapter.notifyDataSetChanged();
                Log.d("", "Success");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException());
            }
        });
    }

    private void writeNewRecord(thisUser user) {
        myRef.push().setValue(user);
    }


}
