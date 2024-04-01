package InteractiveObjects;

import com.mipt.tp.dungeon_sucker.gameplay.generators.ItemGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.items.Item;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.LinkedList;

public class Chest extends InteractiveObject{
  LinkedList<Item> inventory;
  Room room;
  public Chest(int numberOfItems, Room room){
    this.room = room;
    this.inventory  = new LinkedList<>();
    for(int i = 0; i < numberOfItems; ++i){
      this.inventory.addLast(new ItemGenerator().generateItem(this.room.level));
    }

  }

  public void add(Item item) {
    this.inventory.addLast(item);
  }
  public void extractItem(int index){
    this.inventory.remove(index);
  }
}
