package com.mipt.tp.dungeon_sucker.UI.Buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mipt.tp.dungeon_sucker.UI.Drawable;
import com.mipt.tp.dungeon_sucker.helper.Constants;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

import java.util.ArrayList;

public class ButtonsGroup {
    private ArrayList<Button> buttons;
    private IntVector2 buttonGroupSize;
    private int realButtonGroupSizeX;

    private IntVector2 buttonSize;
    private int gapX;
    private int gapY;
    private IntVector2 leftTopCorner;

    public ButtonsGroup(IntVector2 buttonGroupSize, IntVector2 leftTopCorner) {
        this.buttonGroupSize = buttonGroupSize;
        this.leftTopCorner = leftTopCorner;
        buttons = new ArrayList<>();
    }

    public void addButton(Button newButton) {
        buttons.add(newButton);
        resetButtonSizes();
    }

    public void clear() {
        buttons.clear();
    }

    public void resetButtonSizes() {
        if (!buttons.isEmpty()) {
            gapX = buttonGroupSize.x / (buttons.size() + 1) * Constants.BUTTON_GROUP_GAP_PERCENTS / 100;
            gapY = buttonGroupSize.y * Constants.BUTTON_GROUP_GAP_PERCENTS / 100;
            realButtonGroupSizeX = buttonGroupSize.x - gapX * (buttons.size() + 1);
            buttonSize = new IntVector2(realButtonGroupSizeX / buttons.size(),
                    buttonGroupSize.y * (100 - 2 * Constants.BUTTON_GROUP_GAP_PERCENTS) / 100);
            int currPos = gapX + leftTopCorner.x;
            for (Button button :
                    buttons) {
                button.setPos(new IntVector2(currPos, leftTopCorner.y + gapY));
                button.setSize(buttonSize);
                currPos += gapX + buttonSize.x;
            }
        }
    }

    public void pushBtnOnPos(int posX) {
        int num = posX / (buttonSize.x / buttons.size());
        buttons.get(num).push();
    }

    public void drawButtons() {
        for (Button button :
                buttons) {
            button.draw();
        }
    }
}
