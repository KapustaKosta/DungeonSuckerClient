package com.mipt.tp.dungeon_sucker.InteractiveObjects;

import com.mipt.tp.dungeon_sucker.gameplay.generators.ItemGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.items.Item;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.LinkedList;
import java.util.Scanner;

public class Chest extends InteractiveObject{
  LinkedList<Item> inventory;
  Room room;
  public Chest(int numberOfItems, Room room){
    this.room = room;
    this.inventory  = new LinkedList<>();
    for(int i = 0; i < numberOfItems; ++i){
      this.inventory.addLast(new ItemGenerator().generateItem(this.room.level));
    }this.description = "chest, full of precious items (or not)\n Contains:\n";
  }

  public void add(Item item) {
    this.inventory.addLast(item);
    this.description = this.description + item.name + " \n";
  }
  public void extractItem(int index){
    this.inventory.remove(index);
  }
  public void getInteracted(Character player){
    super.getInteracted(player);
    Scanner in = new Scanner(System.in);
    System.out.println("want to take something?");
    System.out.println("1 - yes, 0 - no");
    int i = in.nextInt();
    if(i == 0){
      return;
    }else{
      System.out.println("what would you want to take?");
      System.out.println("0: nothing, I'm just watching");
      for(int k = 0; k < this.inventory.size(); ++k){
        System.out.println((k+1) + ": " + this.inventory.get(k).name);
      }
      i = in.nextInt();
      if(i == 0){
        return;
      }else{
        int index = Math.min(i-1, this.inventory.size()-1);
        this.inventory.get(index).getObtained(player);
        this.extractItem(index);
        return;
      }
    }
  }
}
