package InteractiveObjects;

import com.mipt.tp.dungeon_sucker.gameplay.generators.ItemGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.items.Item;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class Chest extends InteractiveObject{
  Item[] inventory;
  Room room;
  public Chest(int numberOfItems, Room room){
    this.room = room;
    this.inventory = new Item[numberOfItems];
    for(int i = 0; i < numberOfItems; ++i){
      this.inventory[i] = new ItemGenerator().generateItem(this.room.level);
    }
  }
}
