package com.mipt.tp.dungeon_sucker.gameplay.generators.Sets;

import java.util.Random;

public class RaritySet extends Set<String> {
  public RaritySet() {
    this.set = new String[]{
        "Poor", // 30%
        "Common", // 30%
        "Uncommon", // 25%
        "Rare", // 12%
        "Epic", // 2,5%
        "Legendary", // 0,5%
    };// коммент - шанс соответствующего  дропа
  }
  public String generate() {
   int a = new Random().nextInt(200)+1;
   int index;
   if(a <= 60){
     index = 0;
   }else if(a <= 120){
     index = 1;
   }else if( a <= 170){
     index = 2;
   }else if(a <= 194){
     index = 3;
   }else if(a <= 199){
     index = 4;
   }else{
     index = 5;
   }
   return set[index];
  }
}
