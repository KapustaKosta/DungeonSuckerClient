package com.mipt.tp.dungeon_sucker.UI.Buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mipt.tp.dungeon_sucker.UI.Drawable;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.helper.Constants;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class ButtonsGroupUI implements Drawable {
    private ButtonsGroup buttonsGroup;
    private Texture texture;
    private SpriteBatch batch;
    private IntVector2 leftTopCorner;
    private IntVector2 size;

    public ButtonsGroupUI(Texture texture) {
        this.batch = new SpriteBatch();
        this.texture = texture;
        leftTopCorner = new IntVector2((Constants.SCREEN_WITH / 100) * Constants.BUTTON_GROUP_POS_IN_PERCENTS.x,
                (Constants.SCREEN_HEIGHT / 100) * Constants.BUTTON_GROUP_POS_IN_PERCENTS.y);
        size = new IntVector2((Constants.SCREEN_WITH / 100) * Constants.BUTTON_GROUP_SIZE_IN_PERCENTS.x,
                (Constants.SCREEN_HEIGHT / 100) * Constants.BUTTON_GROUP_SIZE_IN_PERCENTS.y);

        buttonsGroup = new ButtonsGroup(size, leftTopCorner);

        // пример
        buttonsGroup.setArticle("Your choice:");
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            Button button = new Button(i + "", new Action() {
                @Override
                public void run(int[] args) {
                    System.out.println(finalI);
                }
            });
            buttonsGroup.addButton(button);
        }
    }

    public void click(int x, int y) {
        if (leftTopCorner.x <= x && size.x + size.x >= x) {
            if (leftTopCorner.y <= y && leftTopCorner.y + size.y >= y) {
                buttonsGroup.pushBtnOnPos(x - leftTopCorner.x);
            }
        }
    }

    @Override
    public void drawInLibGDX() {
        batch.begin();
        batch.draw(texture, leftTopCorner.x, leftTopCorner.y, size.x, size.y);
        batch.end();
        if (buttonsGroup != null) {
            buttonsGroup.drawButtons();
        }
    }

    @Override
    public void drawInConsole() {

    }
}
