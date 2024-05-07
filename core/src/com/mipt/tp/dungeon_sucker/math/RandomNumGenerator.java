package com.mipt.tp.dungeon_sucker.math;

public class RandomNumGenerator {

    public static int generateFromRange(int from, int to) {
        return (int) Math.floor(Math.random() * (to - from + 1) + from);
    }
}
