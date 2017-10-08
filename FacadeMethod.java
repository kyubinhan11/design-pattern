/*
  Information Hiding

  it is a means to hide the complexcity of a subsystem by encapsulating it behind a unifying wrapper called a facade class.

  removes the need for client classes to manage a subsystem on their own, resulting in less coupling between the subsystem and the client classes.

  it also handles instantiation and redirection of tasks to the appropriate class within the subsystem
  
  it provide a single simplfied interface for client classes to interact with the subsystem
*/

// step 1: Design the interface
public interface IAcount {
  public void deposit(BigDecimal amount);
  public void withdraw(BigDecimal amount);
  public void transfer(BigDecimal amount);
  public int getAccountNumber();
}

// step 2: Implement the interface with one or more classes
public class Chequing implements IAcount{...}
public class Saving implements IAcount{...}
public class Investment implements IAcount{...}

// step 3: Create the facade class and wrap the classes
// that implement the interface
public class BankService {
  private HashTable<int, IAcount> bankAccounts;

  public BankService(){
    this.bankAccounts = new HashTable<int, IAcount>;
  }

  public int createNewAccount(String type, BigDecimal initAmount) {
    IAcount newAccount = null;
    switch (type) {
      case "chequing":
        newAccount = new Chequing(initAmount);
        break;
      case "saving":
        newAccount = new Saving(initAmount);
        break;
      case "investment":
        newAccount = new Investment(initAmount);
      default:
        System.out.println("Invalid account type");
    }

    if(newAccount != null) {
      this.bankAccounts.put(newAccount.getAccountNumber(), newAccount);
      return newAccount.getAccountNumber();
    }

    return -1;
  }

  public int transferMoney(int to, int from, BigDecimal amount) {
    IAcount toAccount = this.bankAccounts.get(to);
    IAcount fromAccount = this.bankAccounts.get(from);
    fromAccount.transfer(toAccount, amount);
  }
}

// Step 4: Use the facade class to access the subsystem
public class Customer {
  public static void main(String args[]){
    BankService myBanckService = new BankService();
    int mySaving = myBanckService.createNewAccount("saving", new BigDecimal(500.00));
    int myInvestment = myBanckService.createNewAccount("investment", new BigDecimal(1000.00));
    myBanckService.transferMoney(mySaving, myInvestment, new BigDecimal(300.00));
  }
}
