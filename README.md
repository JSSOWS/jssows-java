# jssows-java 

Please see https://github.com/JSSOWS/jssows-java-api

### Getting started

#### Define your session class

```
public class WebsocketSession {
    
    public WebsocketSession() {
    }

}
```

#### Extends JsonStandardJavaWebSocketServerServices<WebsocketSession>

```
public class BasicWebSocketServer extends JsonStandardJavaWebSocketServerServices<WebsocketSession> {

    public BasicWebSocketServer(int port) throws UnknownHostException {
        super(port);
        configureHandlers();
    }

    public BasicWebSocketServer(InetSocketAddress address) {
        super(address);
        configureHandlers();
    }
    
    public void configureHandlers() {
        ConnectHandler connectHandler = new ConnectHandler();
        bind("connect", connectHandler);
        TasksHandler tasksHandler = new TasksHandler();
        bind("tasks", tasksHandler);
        UpdateHandler updateHandler = new UpdateHandler();
        bind("update", updateHandler);
        SubmitHandler submitHandler = new SubmitHandler();
        bind("submit", submitHandler);
        UnknownHandler unknownHandler = new UnknownHandler();
        setUnknownServiceHandler(unknownHandler);
    }
    
    public static void main(String[] args) throws InterruptedException, IOException {
        WebSocketImpl.DEBUG = true;
        int port = 8887; // 843 flash policy port
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception ex) {
        }
        BasicWebSocketServer s = new BasicWebSocketServer(port);
        s.start();
        System.out.println("WebSocketServer started on port: " + s.getPort());
    }

}
```

#### Define your handlers

```
public class SubmitHandler implements JsonHandler<WebSocket, WebsocketSession> {
  
      @Override
    public boolean handle(JSONObject taskToSubmit, JsonStandardWebSocketServerServices<WebSocket, WebsocketSession> server, WebSocket conn, String callbackId) {
    
        <Your code here>
    
    }

}
```

### Donate!

Help me continuing to work on the JSSOWS projects.
[Donate with PayPal.](https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=YPXUQJM6ACZNG&lc=FR&item_name=JSSOWS&item_number=JSSOWS&currency_code=EUR&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted)
