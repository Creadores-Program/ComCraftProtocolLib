package com.github.steveice10.comcraft.protocol.packet;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import java.io.IOException;

/**
 * Utility class for reading and writing special protocol structures.
 */
public class ComcraftPacketUtil {
    private ComcraftPacketUtil() {
    }

    /**
     * Reads a string from packet input.
     *
     * @param in Input to read from.
     * @return The resulting string.
     * @throws IOException If an I/O error occurs.
     */
    public static String readString(NetInput in) throws IOException {
        int len = in.readUnsignedShort();
        return new String(in.readBytes(len), "UTF-8");
    }

    /**
     * Writes a string to packet output.
     *
     * @param out Output to write to.
     * @param str String to write.
     * @throws IOException If an I/O error occurs.
     */
    public static void writeString(NetOutput out, String str) throws IOException {
        out.writeShort(str.length);
        out.writeBytes(str.getBytes("UTF-8"));
    }
}
