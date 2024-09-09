package g54900.skyjo.message.model;

/**
 * Enumeration TypeMessageSkyjo represents the different types of messages sent
 * between the clients and the server.
 *
 * @author timmy
 */
public enum TypeMessageSkyjo {
    INFORMATIONS_GAME, READY_TO_PLAY, IN_WAIT, IN_GAME, NAMES, HIT_CARD,
    EXCHANGE_DISCARD_CARD, DROP_HIT_CARD, KEEP_HIT_CARD, EXIT, ENDGAME, DATA,
    DATA_DB
}
