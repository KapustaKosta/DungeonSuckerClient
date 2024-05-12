package com.mipt.tp.dungeon_sucker.UI;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.mipt.tp.dungeon_sucker.helper.Constants;

public class Interface implements Drawable, InputProcessor {
    private MapWindow mapWindow;
    private InventoryWindow inventoryWindow;
    private MainWindow mainWindow;

    public Interface(MapWindow mapWindow, InventoryWindow inventoryWindow, MainWindow mainWindow) {
        this.mapWindow = mapWindow;
        this.inventoryWindow = inventoryWindow;
        this.mainWindow = mainWindow;
    }

    @Override
    public void drawInLibGDX() {
        mapWindow.drawInLibGDX();
        inventoryWindow.drawInLibGDX();;
        mainWindow.drawInLibGDX();
    }

    @Override
    public void drawInConsole() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenY = Constants.SCREEN_HEIGHT - screenY;
        if (button == Buttons.LEFT) {
            mainWindow.onMouseClick(screenX, screenY);
        }
        return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
