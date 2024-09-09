package g54900.skyjo.message.model;

import java.io.Serializable;

/**
 * Interface MessageSkyjo represents the interface of the different messages
 * sent between the clients and the server.
 *
 * @author timmy
 */
public interface MessageSkyjo extends Serializable {

    /**
     * Gets the type of message sent by a client or the server.
     *
     * @return the type of message.
     */
    TypeMessageSkyjo getType();

    /**
     * Gets the content of the message sent by the client or the server.
     *
     * @return the content of the message.
     */
    Object getContent();
}
