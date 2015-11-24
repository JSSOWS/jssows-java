/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jssows;

import java.io.IOException;
import org.java_websocket.WebSocketImpl;

/**
 *
 * @author Frank Afriat
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        WebSocketImpl.DEBUG = true;
        int port = 8887; // 843 flash policy port
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception ex) {
        }
        JsonStandardJavaWebSocketServerServices<WebsocketSession> s = new JsonStandardJavaWebSocketServerServices(port);
        s.start();
        System.out.println("WebSocketServer started on port: " + s.getPort());
    }

}
