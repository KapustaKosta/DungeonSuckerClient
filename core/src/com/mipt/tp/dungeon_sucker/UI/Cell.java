package com.mipt.tp.dungeon_sucker.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mipt.tp.dungeon_sucker.gameplay.items.Item;
import com.mipt.tp.dungeon_sucker.helper.Constants;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class Cell extends Window {
    private Item item;
    private SpriteBatch batch;
    private IntVector2 bottomLeftPoint;

    private int size;

    public Cell(IntVector2 topLeftPoint,
                IntVector2 bottomRightPoint, Item item) {
        super(topLeftPoint, bottomRightPoint);
        this.item = item;
        this.bottomLeftPoint = new IntVector2(topLeftPoint.x, bottomRightPoint.y);
        batch = new SpriteBatch();
    }

    public void setSize(int cellSize) {
        this.size = cellSize;
    }

    @Override
    public void drawInLibGDX() {
        drawBoard();
        if (item == null) return;

        batch.begin();
        if (item.texture != null) {
            batch.draw(item.texture, bottomLeftPoint.x * Constants.CELL_SIZE, bottomLeftPoint.y * Constants.CELL_SIZE, size, size);
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
