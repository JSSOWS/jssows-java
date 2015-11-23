/*
    Copyright 2015 Frank Afriat

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package jssows;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import org.java_websocket.WebSocket;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;

/**
 *
 * @author Frank Afriat
 */
public class JsonStandardJavaWebSocketServerServices<S> extends WebSocketServer implements JsonStandardWebSocketServerServices<WebSocket,S> {

    JsonStandardWebSocketServerServicesDelegator<WebSocket,S> delegator;
    
    public JsonStandardJavaWebSocketServerServices(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
        delegator = new JsonStandardWebSocketServerServicesDelegator<>(new JavaWebSocketServerAdapter(this));
    }

    public JsonStandardJavaWebSocketServerServices(InetSocketAddress address) {
        super(address);
        delegator = new JsonStandardWebSocketServerServicesDelegator<>(new JavaWebSocketServerAdapter(this));
    }
    
    
    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        //System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " onOpen!");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        //this.sendToAll(conn + " has left the room!");
        //System.out.println(conn + " onClose!");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
      delegator.onMessage(conn, message);
    }    

    //@Override
    public void onFragment(WebSocket conn, Framedata fragment) {
        //System.out.println("received fragment: " + fragment);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            // some errors like port clientBinding failed may not be assignable to a specific websocket
        }
    }

    @Override
    public void bindSession(WebSocket websocket, S s) {
        delegator.bindSession(websocket, s);
    }

    @Override
    public S getSession(WebSocket websocket) {
        return delegator.getSession(websocket);
    }

    @Override
    public void unbindSession(WebSocket websocket) {
        delegator.unbindSession(websocket);
    }

    @Override
    public void send(WebSocket websocket, String service, JSONObject data, JsonHandler<WebSocket, S> handler) {
        delegator.send(websocket, service, data, handler);
    }

    @Override
    public void send(WebSocket websocket, String service, JSONObject data) {
        delegator.send(websocket, service, data);
    }

    @Override
    public void sendToAll(String service, JSONObject data, JsonHandler<WebSocket, S> handler) {
        delegator.sendToAll(service, data, handler);
    }

    @Override
    public void sendToAll(String service, JSONObject data) {
        delegator.sendToAll(service, data);
    }

    @Override
    public void setUnknownServiceHandler(JsonHandler<WebSocket, S> unknownServiceHandler) {
        delegator.setUnknownServiceHandler(unknownServiceHandler);
    }

    @Override
    public void bind(String service, JsonHandler<WebSocket, S> handler) {
        delegator.bind(service, handler);
    }

    @Override
    public void unbind(String service) {
        delegator.unbind(service);
    }
    
}
