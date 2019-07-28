package sample.java.com.typicodeapp.network;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import sample.java.com.typicodeapp.BuildConfig;
import sample.java.com.typicodeapp.listener.MessageReceiver;

@Singleton
public class WebSocketManager {

    private WebSocket webSocket;
    private MessageReceiver receiver;
    private SocketListener listener = new SocketListener();

    public void connectToWebSocket() {
        if (webSocket == null) {
            openWebSocketConnection();
        }
    }

    public void closeWebSocketConnection() {
        if (webSocket != null) {
            webSocket.clearListeners();
            webSocket.disconnect();
            webSocket.sendClose();
            webSocket = null;
        }
    }

    public void sendMessage(String message) {
        if (webSocket != null) {
            webSocket.sendText(message);
        }
    }

    public void setReceiver(MessageReceiver receiver) {
        this.receiver = receiver;
    }

    //region Private methods and classes

    private void openWebSocketConnection() {
        if (webSocket == null) {
            WebSocketFactory factory = new WebSocketFactory().setConnectionTimeout(5000);
            try {
                webSocket = factory.createSocket(BuildConfig.SOCKET_URL);
                webSocket.addListener(listener);
                webSocket.connectAsynchronously();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private class SocketListener extends WebSocketAdapter {

        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);

            receiver.onMessageReceived(text);
        }

        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
            super.onConnected(websocket, headers);
        }

    }

    //endregion

}
