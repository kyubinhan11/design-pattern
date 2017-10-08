/*
  Coding to an interface, not an implementation

  the factory method design intent is to define an interface for creating objects, but let the subclassses decide which class to instantiate
*/

public abstract class KnifeStore {
  public Knife orderKnife(String knifeType) {
    Knife knife;

    // now creating a knife is a method in the class
    knife = createKnife(knifeType);

    knife.sharpen();
    knife.polish();
    knife.package();

    return knife;
  }

  abstract Knife createKnife(String type);
}

// inherit orderKnife method from knifeStore
public BudegetKnifeStore extends KnifeStore {
  // up to any subclass of KnifeStore to define this method
  Knife createKnife(String knifeType) {
    if(knifeType.equals("steak")) {
      return new BudgetSteakKnife();
    } else if (knifeType.equals("chefs")){
      return new BudgetChefsKnife();
    }
    // .. more types
    else return null;
  }
}
