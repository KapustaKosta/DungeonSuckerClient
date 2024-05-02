package com.mipt.tp.dungeon_sucker.UI;

import java.util.ArrayList;

public class ButtonsGroup {
    private ArrayList<Button> buttons;

    public void addButton(Button newButton) {
        buttons.add(newButton);
    }

    public void clear() {
        buttons.clear();
    }
}
