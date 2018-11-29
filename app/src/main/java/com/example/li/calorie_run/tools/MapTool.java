package com.example.li.calorie_run.tools;

import java.util.List;
import java.util.Map;

public class MapTool {

    public static Object getValue(Map<String, Object> map, String key){

        for(String temp : map.keySet()){
            if(temp.equals(key)){
                return map.get(key);
            }
        }
        return null;
    }

    public static Object getValueInList(List<Map> maps, String key){
        String temp = "";
        for(Map<String, Object> map : maps){
//            if(map.size() == 1){
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if(entry.getKey().toString().equals(key)){
                        return entry.getValue();
                    }
                }
//            }

        }

        return null;
    }

}
