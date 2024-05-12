package com.mipt.tp.dungeon_sucker.UI;

import com.mipt.tp.dungeon_sucker.gameplay.items.Item;
import com.mipt.tp.dungeon_sucker.helper.Constants;
import com.mipt.tp.dungeon_sucker.math.IntVector2;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class InventoryWindow extends Window {
    private ArrayList<Item> items;
    private int cellsWidth;
    private int cellsHeight;
    private Cell[][] cells;

    public InventoryWindow(IntVector2 topLeftPoint,
                           IntVector2 bottomRightPoint, int cellsWidth, int cellsHeight) {
        super(topLeftPoint, bottomRightPoint);
        items = new ArrayList<>();
        this.cellsWidth = cellsWidth;
        this.cellsHeight = cellsHeight;
        cells = new Cell[cellsHeight][cellsWidth];
        int cellWidth = (width / Constants.CELL_SIZE) / cellsWidth;
        int cellHeight = (height / Constants.CELL_SIZE) / cellsHeight;
        IntVector2 cellTopLeft = new IntVector2(topLeftPoint);
        IntVector2 cellBottomRight = new IntVector2(topLeftPoint);
        cellBottomRight.moveY(-cellHeight);
        for (int i = 0; i < cellsHeight; i++) {
            cellTopLeft.x = topLeftPoint.x;
            cellBottomRight.x = topLeftPoint.x + cellsWidth;
            for (int j = 0; j < cellsWidth; j++) {
                cells[i][j] = new Cell(cellTopLeft, cellBottomRight, null);
                cellTopLeft.moveX(cellWidth);
                cellBottomRight.moveX(cellWidth);
            }
            cellTopLeft.moveY(-cellHeight);
            cellBottomRight.moveY(-cellHeight);
        }
    }

    public boolean addItem(Item item) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getItem() == null) {
                    cells[i][j].setItem(item);
                    items.add(item);
                    return true;
                }
            }
        }
        return false;
    }

    public Item getItem(IntVector2 position) {
        throw new NotImplementedException();
    }

    public boolean removeItem(Item item) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getItem().name.equals(item.name)) {
                    cells[i][j].setItem(null);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void drawInLibGDX() {
        drawBoard();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].drawInLibGDX();
            }
        }
    }
}
