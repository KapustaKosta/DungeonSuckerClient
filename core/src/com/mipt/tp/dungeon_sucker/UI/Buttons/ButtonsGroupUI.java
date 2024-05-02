package com.mipt.tp.dungeon_sucker.UI.Buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mipt.tp.dungeon_sucker.UI.Drawable;
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
        Button button1 = new Button(new Texture("button.png"));
        Button button2 = new Button(new Texture("button.png"));
        Button button3 = new Button(new Texture("button.png"));
        Button button4 = new Button(new Texture("button.png"));
        buttonsGroup.addButton(button1);
        buttonsGroup.addButton(button2);
        buttonsGroup.addButton(button3);
        buttonsGroup.addButton(button4);
    }

    @Override
    public void draw() {
        batch.begin();
        batch.draw(texture, leftTopCorner.x, leftTopCorner.y, size.x, size.y);
        batch.end();
        if (buttonsGroup != null){
            buttonsGroup.drawButtons();
        }
    }
}
