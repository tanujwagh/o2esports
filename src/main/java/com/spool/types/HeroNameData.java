package com.spool.types;

import java.util.HashMap;

/**
 * Created by Tanuj on 4/5/2017.
 */
public class HeroNameData {

    private static HashMap<String, Integer> heroNameToIdMap = new HashMap<>();
    private static boolean initialized = false;

    public static void init(String[] keys, String[] values){
        for(int i = 0; i < keys.length; i++){
            heroNameToIdMap.put(keys[i].toLowerCase(), Integer.parseInt(values[i]));
        }
        initialized = true;
    }

    public static Integer getIdByHeroName(String heroName){
        if(initialized) {
            return heroNameToIdMap.get(heroName.trim().toLowerCase());
        }
        return -1;
    }

}
