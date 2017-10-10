/*
  Enforcing polymorphism across each class through implementing an interface (or inheriting from a superclass)

  The composite design pattern is used to solve the issues of how to build a tree-like structure of objects, and how to treat the individual types of those objects uniformly

  Each individual class is a subtype of an interface or superclass, and will be able to conform to a set of shared behaviors.

  the composite class is capable of aggregating component classes, which will create a tree-like structure
*/

// Step 1: Design the interface that defines the overall types
public interface IStructure {
  public void enter();
  public void exit();
  public void location();
  public string getName();
}

// Step 2; Implement composite class
public class Housing implements IStructure {
  private ArrayList<IStructure> structures;
  private String address;

  public Housing (String address){
    this.structures = new ArrayList<IStructure>();
    this.address = address;
  }

  public String getName(){
    return this.address;
  }

  public int addStructure(IStructure component) {
    this.structures.add(component);
    return this.structures.size() - 1;
  }

  public IStructure getStructure(int componentNumber){
    return this.structures.get(componentNumber);
  }

  public void location() {
    System.out.println("You are currently in " + this.getName() + ". It has ");
    for (IStructure struct : this.structures){
      System.out.println(struct.getName());
    }
  }
  /* print out when yo enter and exit the building */
  public void enter(){...}
  public void exit(){...}
}

// Step 3: Implement the leaf class
public abstract class Room implements IStructure {
  public String name;
  public void enter() {
    System.out.println("You have entered the " + this.name);
  }

  public void exit() {
    System.out.println("You have left the " + this.name);
  }

  public void location() {
    System.out.println("You are currently in the " + this.name);
  }

  public String getName() {
    return this.name;
  }

}

public class Program {
  public static void main(String args[]) {
    Housing building = new Housing("123 Street");
    Housing floor1 = new Housing("123 Street - First Floor");
    int firstFloor = building.addStructure(floor1);

    Room washroom1m = new Room("1F Men's Washroom");
    Room washroom1w = new Room("1F Women's Washroom");
    Room common1 = new Room("1F Common Area");

    int firstMens = floor1.addStructure(washroom1m);
    int firstWomens = floor1.addStructure(washroom1w);
    int firstCommon = floor1.addStructure(common1);

    building.enter(); // Enter the building
    Housing currentFloor = building.getStructure(firstFloor);
    currentFloor.enter(); // walk into the first floor
    Room currentRoom = currentFloor.getStructure(firstMens);
    currentRoom.enter(); // walk into the men's Room
    currentRoom = currentFloor.getStructure(firstCommon);
    currentRoom.enter(); // walk into the common area
  }
}
