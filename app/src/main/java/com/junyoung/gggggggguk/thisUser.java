package com.junyoung.gggggggguk;

/**
 * Created by Junyoung on 16. 7. 1..
 */
public class thisUser {
    private String nickname;
    private long score;

    public thisUser(String nickname, long score) {
        this.nickname = nickname;
        this.score = score;
    }

    public thisUser() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
