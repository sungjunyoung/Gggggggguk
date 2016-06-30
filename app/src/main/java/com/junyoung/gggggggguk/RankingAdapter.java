package com.junyoung.gggggggguk;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Junyoung on 16. 6. 28..
 */


public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingViewHolder> {

    private List<thisUser> userList;

    RankingAdapter(List<thisUser> userList) {
        if (userList == null) {
            throw new IllegalArgumentException(
                    "modelData must not be null");
        }
        this.userList = userList;
    }

    @Override
    public RankingViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ranking_row, viewGroup, false);
        return new RankingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RankingViewHolder viewHolder, int position) {
        thisUser user = userList.get(position);
        viewHolder.nickname.setText(user.getNickname());
        viewHolder.score.setText(String.valueOf(user.getScore()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public final static class RankingViewHolder extends RecyclerView.ViewHolder {
        TextView nickname;
        TextView score;

        public RankingViewHolder(View itemView) {
            super(itemView);
            nickname = (TextView) itemView.findViewById(R.id.nickname);
            score = (TextView) itemView.findViewById(R.id.score);
        }
    }
}