/*
  the adapter essentially encapsulates the adaptee and presents a new interface, or appearnce, to the client class.
  It does this by wrapping the adaptee's interface and exposing a new target interface that makes sense to the client.

  an adapter is meant to:

  - wrap the adaptee and expose a target interface to the client.

  - indirectly change the adaptee's interface into one that the client is expecting by implementing a target interface

  - indirectly translate the client's request into one that the adaptee is expecting

  - reuse an existing adaptee with in incompatible interface
*/

// Step 1: Design the target interface
public interface WebRequester {
  public int request(Object);
}

 // Step 2: Implement the target interface with the adapter class
public class WebAdapter implements WebRequester {
  private WebService service;
  public void connect(WebService currService){
   this.service = currService;
   /* Connect to the web service */
  }

  public int request(Object request) {
   Json result = this.toJson(request);
   if(response != null) return 200; // OK
   return 500; // server error
  }
  private Json toJson(Object input) {...}
}

// Step 3: Send the request from the client to the adapter using the target interface
public class WebClient {
  private WebRequester webRequester;
  public WebClient(WebRequester webRequester){
    this.webRequester = webRequester;
  }

  private Object makeObject() {...}

  public void doWork() {
    Object object = makeObject();
    int status = webRequester.request(object);

    if(status == 200){
      System.out.println("OK");
    } else {
      System.out.println("Not ok");
    }
    return;
  }
}

// usage
public class Program {
  public static void main(String args[]) {
    String webHost = "Host: https://google.com\n\r";
    WebService service = new WebService(webHost);
    WebAdapter adapter = new WebAdapter();
    adapter.connect(service);
    Webclient client = new Webclient(adapter);
    client.doWork(); // WebClient doesn't need to know anything about the Webservice!
  }
}
