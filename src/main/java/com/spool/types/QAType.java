package com.spool.types;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tanuj on 4/5/2017.
 */
public class QAType {
    public static int Oddtime;
    public static double Rnetworthodds;
    public static double Dnetworthodds;
    public static double Rkodds;
    public static double Dkodds;

    public static Map<Integer, Double> heroNetOdds = new HashMap<>();
    public static Map<Integer, Double> heroKillOdds = new HashMap<>();

    public static void init(String[] data){
        Oddtime = Integer.parseInt(data[0]);
        Rnetworthodds = Double.parseDouble(data[1]);
        Dnetworthodds = Double.parseDouble(data[2]);
        Rkodds = Double.parseDouble(data[3]);
        Dkodds = Double.parseDouble(data[4]);

        int index = 1;
        for(String heroNetOddsValue : Arrays.copyOfRange(data, 5, 5+10)){
            heroNetOdds.put(HeroNameData.getIdByHeroName("hero" + (index++)), Double.parseDouble(heroNetOddsValue));
        }

        index = 1;
        for(String heroKillOddsValue : Arrays.copyOfRange(data, 15, 15+10)){
            heroKillOdds.put(HeroNameData.getIdByHeroName("hero" + (index++)), Double.parseDouble(heroKillOddsValue));
        }
    }

}
