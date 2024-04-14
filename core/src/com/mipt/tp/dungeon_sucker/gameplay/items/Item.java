package com.mipt.tp.dungeon_sucker.gameplay.items;

import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;

public class Item {
  public String name;
  public Entity holder;
  public Texture texture;
  protected int weight;
  public void getObtained(Entity entity){
    this.holder = entity;
    this.holder.items.add(this);
    this.holder.weight += this.weight;
  }
  public void getLost(){
    for(int i = 0; i < this.holder.items.size(); ++i){
      if(this.holder.items.get(i) == this){
        this.holder.items.remove(i);
        return;
      }
    }
  }
}
