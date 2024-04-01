package InteractiveObjects;

public class InteractiveObject {
  protected String description;

  public void getInteracted(Character player) {
    System.out.println(this.description);
  }
}
