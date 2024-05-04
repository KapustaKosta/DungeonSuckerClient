package com.mipt.tp.dungeon_sucker.InteractiveObjects;

public class InteractiveObject {
  protected String description;

  public void getInteracted(Character player) {
    // todo: remake all getInteracted methods so information is on the screen
    // todo: in all inheritors too
    System.out.println(this.description);
  }
}
