package com.mipt.tp.dungeon_sucker.UI;

import java.util.function.Function;

public class Button {
    private Action onPress;
    private int[] args;

    public void run() {
        onPress.run(args);
    }
}
