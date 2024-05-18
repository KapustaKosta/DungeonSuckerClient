package com.mipt.tp.dungeon_sucker.UI;

import com.mipt.tp.dungeon_sucker.gameplay.items.Item;
import com.mipt.tp.dungeon_sucker.helper.Constants;
import com.mipt.tp.dungeon_sucker.math.IntVector2;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Character;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class InventoryWindow extends Window {
    private ArrayList<Item> items;
    private int cellsWidth;
    private int cellsHeight;
    private Cell[][] cells;

    private Character character;

    public InventoryWindow(IntVector2 topLeftPoint,
                           IntVector2 bottomRightPoint, int cellsWidth, int cellsHeight) {
        super(topLeftPoint, bottomRightPoint);
        items = new ArrayList<>();
        this.cellsWidth = cellsWidth;
        this.cellsHeight = cellsHeight;
        cells = new Cell[cellsHeight][cellsWidth];
        int cellSize = (width / Constants.CELL_SIZE) / cellsWidth;
        IntVector2 cellTopLeft = new IntVector2(topLeftPoint);
        IntVector2 cellBottomRight = new IntVector2(topLeftPoint);
        cellBottomRight.moveY(-cellSize);
        for (int i = 0; i < cellsHeight; i++) {
            cellTopLeft.x = topLeftPoint.x;
            cellBottomRight.x = topLeftPoint.x + cellsWidth;
            for (int j = 0; j < cellsWidth; j++) {
                cells[i][j] = new Cell(cellTopLeft, cellBottomRight, null);
                cells[i][j].setSize(cellSize * Constants.CELL_SIZE);
                cellTopLeft.moveX(cellSize);
                cellBottomRight.moveX(cellSize);
            }
            cellTopLeft.moveY(-cellSize);
            cellBottomRight.moveY(-cellSize);
        }
    }

    public void update(ArrayList<Item> items) {
        this.items = items;
        updateTextures();
    }

    public void updateTextures() {
        for (int i = 0; i < this.items.size(); i++) {
            int x = i % cells[0].length;
            int y = i / cells[0].length;
            cells[y][x].setItem(items.get(i));
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
