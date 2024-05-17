package com.mipt.tp.dungeon_sucker.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mipt.tp.dungeon_sucker.gameplay.items.Item;
import com.mipt.tp.dungeon_sucker.helper.Constants;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class Cell extends Window {
    private Item item;
    private SpriteBatch batch;
    private IntVector2 bottomLeftPoint;

    public Cell(IntVector2 topLeftPoint,
                IntVector2 bottomRightPoint, Item item) {
        super(topLeftPoint, bottomRightPoint);
        this.item = item;
        this.bottomLeftPoint = new IntVector2(topLeftPoint.x, bottomRightPoint.y);
        batch = new SpriteBatch();
    }

    @Override
    public void drawInLibGDX() {
        drawBoard();
        if (item == null) return;

        batch.begin();
        if (item.texture != null) {
            batch.draw(item.texture, bottomLeftPoint.x * Constants.CELL_SIZE, bottomLeftPoint.y * Constants.CELL_SIZE, 100, 100);
        }
        batch.end();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
