package com.spool.types;

import java.util.HashMap;

/**
 * Created by Tanuj on 4/5/2017.
 */
public class HeroIDData {
    private static HashMap<Integer, String> heroIdToName = new HashMap<>();
    private static boolean initialized = false;

    public static void init(String[] keys, String[] values){
        for(int i = 0; i < keys.length; i++){
            heroIdToName.put(Integer.parseInt(keys[i]), values[i]);
        }
        initialized = true;
    }

    public static String getHeroNameById(int id){
        if(initialized) {
            return heroIdToName.get(id);
        }
        return null;
    }
}
