package com.mipt.tp.dungeon_sucker.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.helper.Constants;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class Window implements Drawable {

    // Todo: Переписать на просто Vector2
    protected IntVector2 topLeftPoint;
    protected IntVector2 bottomRightPoint;
    protected Vector2 bottomLeftPoint;
    protected int width;
    protected int height;
    private Pixmap pixmap;
    private Texture board;
    private static Color boardsColor = Color.valueOf("#0a0a0a");
    private SpriteBatch batch;

    public Window(IntVector2 topLeftPoint, IntVector2 bottomRightPoint) {
        this.topLeftPoint = new IntVector2(topLeftPoint);
        this.bottomRightPoint = new IntVector2(bottomRightPoint);
        this.bottomLeftPoint = new Vector2(topLeftPoint.x * Constants.CELL_SIZE, bottomRightPoint.y * Constants.CELL_SIZE);
        width = (bottomRightPoint.x - topLeftPoint.x) * Constants.CELL_SIZE;
        height = (topLeftPoint.y - bottomRightPoint.y) * Constants.CELL_SIZE;
        pixmap = new Pixmap(width, height, Format.RGBA8888);
        setBoardPixels();
        this.batch = new SpriteBatch();
    }

    private void setBoardPixels() {
        int color = Color.rgba8888(boardsColor);
        for (int i = 0; i < height; i++) {
            pixmap.drawPixel(0, i, color);
            pixmap.drawPixel(width - 1, i, color);
        }
        for (int i = 0; i < width; i++) {
            pixmap.drawPixel(i, 0, color);
            pixmap.drawPixel(i, height - 1, color);
        }
    }

    protected void drawBoard() {
        if (board == null) {
            board = new Texture(pixmap);
            pixmap.dispose();
        }
        batch.begin();
        batch.draw(board, topLeftPoint.x * Constants.CELL_SIZE,
                bottomRightPoint.y * Constants.CELL_SIZE);
        batch.end();
    }

    @Override
    public void drawInLibGDX() {
        drawBoard();
    }

    @Override
    public void drawInConsole() {

    }

    protected boolean isPointInWindow(int x, int y) {
        return y < topLeftPoint.y * Constants.CELL_SIZE && y > bottomRightPoint.y * Constants.CELL_SIZE
                && x <= bottomRightPoint.x * Constants.CELL_SIZE
                && x >= topLeftPoint.x * Constants.CELL_SIZE;
    }
}
