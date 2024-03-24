package com.mipt.tp.dungeon_sucker.gameplay.generators.Sets;

import java.util.Random;

public class Set<E> {
  E[] set;
  public E generate(){
    return set[new Random().nextInt(set.length)];
  }
}
