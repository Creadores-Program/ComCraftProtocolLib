package com.github.steveice10.comcraft.protocol.packet.server;

import com.github.steveice10.comcraft.protocol.packet.ComcraftPacketUtil;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerDisconnectPacket implements Packet {
    private String reason;

    @SuppressWarnings("unused")
    private ServerDisconnectPacket(){}

    public ServerDisconnectPacket(String reason){
        this.reason = reason;
    }

    public String getReason(){
        return this.reason;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.reason = ComcraftPacketUtil.readString(in);
    }
    @Override
    public void write(NetOutput out) throws IOException {
        ComcraftPacketUtil.writeString(out, this.reason);
    }
    @Override
    public boolean isPriority() {
        return false;
    }
}