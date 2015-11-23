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

import java.util.Collection;
import org.java_websocket.WebSocket;
import org.java_websocket.server.WebSocketServer;

/**
 *
 * @author Frank Afriat
 */
public class JavaWebSocketServerAdapter implements WebSocketServerServices<WebSocket> {

    protected WebSocketServer webSocketServer;
    
    public JavaWebSocketServerAdapter(WebSocketServer webSocketServer) {
        this.webSocketServer = webSocketServer;
    }
    
    @Override
    public void send(WebSocket websocket, String message) {
        websocket.send(message);
    }

    @Override
    public Collection<WebSocket> getConnections() {
        return webSocketServer.connections();
    }
    
}
