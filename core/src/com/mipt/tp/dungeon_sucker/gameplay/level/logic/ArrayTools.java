package com.mipt.tp.dungeon_sucker.gameplay.level.logic;

import java.util.Random;

public class ArrayTools {
  public static void shuffleArray(int[] array) {
    Random random = new Random();
    for (int i = array.length - 1; i > 0; i--) {
      int index = random.nextInt(i + 1);
      int a = array[index];
      array[index] = array[i];
      array[i] = a;
    }
  }
}
