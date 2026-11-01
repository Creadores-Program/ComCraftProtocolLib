package com.github.steveice10.comcraft.protocol;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.PacketHeader;

import java.io.IOException;

/**
 * Packet header used by Comcraft packets.
 */
public class ComcraftPacketHeader implements PacketHeader {

    @Override
    public boolean isLengthVariable() {
        return true;
    }

    @Override
    public int getLengthSize() {
        return 4;
    }

    @Override
    public int getLengthSize(int length) {
        return 4;
    }

    @Override
    public int readLength(NetInput in, int available) throws IOException {
        return in.readInt();
    }

    @Override
    public void writeLength(NetOutput out, int length) throws IOException {
        out.writeInt(length);
    }

    @Override
    public int readPacketId(NetInput in) throws IOException {
        return in.readUnsignedByte();
    }

    @Override
    public void writePacketId(NetOutput out, int packetId) throws IOException {
        out.writeByte(packetId);
    }
}