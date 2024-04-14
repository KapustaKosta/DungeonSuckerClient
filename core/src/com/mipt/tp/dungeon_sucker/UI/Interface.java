package com.mipt.tp.dungeon_sucker.UI;

public class Interface implements LibGDXDrawable{
  private MapWindow mapWindow;
  private InventoryWindow inventoryWindow;
  private Window mainWindow;

  public Interface(MapWindow mapWindow, InventoryWindow inventoryWindow, Window mainWindow) {
    this.mapWindow = mapWindow;
    this.inventoryWindow = inventoryWindow;
    this.mainWindow = mainWindow;
  }

  @Override
  public void draw() {
    mapWindow.draw();
    inventoryWindow.draw();;
    //mainWindow.draw();
  }
}
