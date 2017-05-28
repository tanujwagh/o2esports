package com.spool.types;

/**
 * Created by Tanuj on 4/5/2017.
 */
public class BasicInfo {
    public static final String RADIANT_NAME = "Radiant";
    public static final String DIRE_NAME = "Dire";

    public static String radiantTeamName;
    public static String direTeamName;

    public static String duration;
    public static long matchId;
    public static long leagueId;

    public static void init(String[] data){
        radiantTeamName = data[0];
        direTeamName = data[1];
        duration = data[2];
        matchId = Long.parseLong(data[3]);
        leagueId = Long.parseLong(data[4]);
    }

}
