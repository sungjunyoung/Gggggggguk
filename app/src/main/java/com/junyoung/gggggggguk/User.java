package com.junyoung.gggggggguk;

/**
 * Created by Junyoung on 16. 7. 1..
 */
public class User {
    static private String nickname;
    static private long score;

    public static String getNickname() {
        return nickname;
    }

    public static void setNickname(String nickname) {
        User.nickname = nickname;
    }

    public static long getScore() {
        return score;
    }

    public static void setScore(long score) {
        User.score = score;
    }
}
