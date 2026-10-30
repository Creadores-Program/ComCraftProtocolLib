package com.github.steveice10.comcraft.protocol;

/**
 * Contains various protocol constants.
 */
public class ComcraftConstants {
    /**
     * Current server version.
     */
    public static final int SERVER_VERSION = 2;

    /**
     * Current api version.
     */
    public static final int API_VERSION = 6;

    /**
     * Flag key for a session's player username.
     * <p>
     * Available on both clients and servers, set automatically based on provided username.
     */
    public static final String USERNAME_KEY = "username";

    /**
     * Flag key for a session's server list salt.
     * <p>
     * Available on clients, uniqueId player
     */
    public static final String UNIQUEID_KEY = "uniqueId";
}
