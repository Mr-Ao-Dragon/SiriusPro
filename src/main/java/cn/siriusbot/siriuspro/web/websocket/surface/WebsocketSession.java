package cn.siriusbot.siriuspro.web.websocket.surface;

import java.io.IOException;

public interface WebsocketSession {

    /**
     * Provides a unique identifier for the session. This identifier should not
     * be relied upon to be generated from a secure random source.
     * @return A unique identifier for the session.
     */
    String getId();


    /**
     * Close the connection to the remote end point using the code
     * {@link javax.websocket.CloseReason.CloseCodes#NORMAL_CLOSURE} and an
     * empty reason phrase.
     *
     * @throws IOException if an I/O error occurs while the WebSocket session is
     *                     being closed.
     */
    void close() throws IOException;


    void send(String s) throws IOException;
}
