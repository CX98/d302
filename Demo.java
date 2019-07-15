package com.d302.util;

import java.util.*;

public class Demo {
    public static void main(String []args){

        //Map
        Map<String, Long> map = new HashMap<>();
        map.put("张三", 18379714959L);
        map.put("李斯", 15180243564L);
        map.put("王五", 15970950016L);

        //Map取值方式1
//        Set<String> KeySet = map.keySet();
//        Iterator<String> it = KeySet.iterator();
//        while (it.hasNext()){
//            String key = it.next();
//            Long value = map.get(key);
//            System.out.println(key+"-->"+value);
//        }
        //取值方式
        Set<Map.Entry<String,Long>> set;
        set = map.entrySet();
        for (Map.Entry<String, Long> entry : set) {
            System.out.println(entry.getKey() + "-->" + entry.getValue());
        }

        //Set
//        Set<String> set = new HashSet<String>();
//        set.add("Holle");
//        set.add("Java");
//        set.add("你好坏");
//取值方式
//        Iterator<String> it = set.iterator();
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }




//        List<String> name = new ArrayList<String>();
//        name.add("甘宏杰");
//        name.add("贾泓钰");
//
//        for (String names:name){
//            System.out.println(names);
//        }
    }
}
