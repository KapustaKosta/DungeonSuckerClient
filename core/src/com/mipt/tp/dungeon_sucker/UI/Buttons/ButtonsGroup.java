package com.mipt.tp.dungeon_sucker.UI.Buttons;

import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.UI.text.Text;
import com.mipt.tp.dungeon_sucker.helper.Constants;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

import java.util.ArrayList;

public class ButtonsGroup {
    private String article = "Your choice:";
    private ArrayList<Button> buttons;
    private final IntVector2 buttonGroupSize;
    private int realButtonGroupSizeX;

    private IntVector2 buttonSize;
    private int gapX;
    private int gapY;
    private IntVector2 leftDownCorner;

    private static ButtonsGroup buttonsGroup;

    private Text articleText;

    public static ButtonsGroup getInstance() {
        if (buttonsGroup != null) return buttonsGroup;
        return null;
    }

    public ButtonsGroup(IntVector2 buttonGroupSize, IntVector2 leftDownCorner) {
        this.buttonGroupSize = buttonGroupSize;
        articleText = new Text(article);
        this.articleText.position = new Vector2(new Vector2(leftDownCorner.x, leftDownCorner.y + buttonGroupSize.y + 42));
        this.leftDownCorner = leftDownCorner;

        buttons = new ArrayList<>();
        buttonsGroup = this;
    }

    public void addButton(Button newButton) {
        buttons.add(newButton);
        resetButtonSizes();
    }

    public void setArticle(String newArticle) {
        this.article = newArticle;
        articleText.setText(article);
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
            int currPos = gapX + leftDownCorner.x;
            for (Button button :
                    buttons) {
                button.setPos(new IntVector2(currPos, leftDownCorner.y + gapY));
                button.setSize(buttonSize);
                currPos += gapX + buttonSize.x;
            }
        }
    }

    public void pushBtnOnPos(int posX) {
        int num = posX / (buttonGroupSize.x / buttons.size());
        if (num < buttons.size()) {
            buttons.get(num).push();
        }
    }

    public void drawButtons() {
        articleText.drawInLibGDX();
        for (Button button :
                buttons) {
            button.drawInLibGDX();
        }
    }
}
