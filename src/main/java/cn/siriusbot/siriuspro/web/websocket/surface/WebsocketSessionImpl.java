package cn.siriusbot.siriuspro.web.websocket.surface;

import lombok.extern.log4j.Log4j2;

import javax.websocket.CloseReason;
import javax.websocket.Session;
import java.io.IOException;

@Log4j2
public class WebsocketSessionImpl implements WebsocketSession{

    Session session;

    public WebsocketSessionImpl(Session session) {
        this.session = session;
    }


    /**
     * Provides a unique identifier for the session. This identifier should not
     * be relied upon to be generated from a secure random source.
     *
     * @return A unique identifier for the session.
     */
    @Override
    public String getId() {
        return session.getId();
    }

    /**
     * Close the connection to the remote end point using the code
     * {@link CloseReason.CloseCodes#NORMAL_CLOSURE} and an
     * empty reason phrase.
     *
     * @throws IOException if an I/O error occurs while the WebSocket session is
     *                     being closed.
     */
    @Override
    public void close() throws IOException {
        this.session.close();
    }

    @Override
    public void send(String s) throws IOException {
        log.info("Websocket.send() -> " + s);
        this.session.getBasicRemote().sendText(s);
    }
}
