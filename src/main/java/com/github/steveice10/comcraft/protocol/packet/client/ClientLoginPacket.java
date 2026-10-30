package com.github.steveice10.comcraft.protocol.packet.client;

import com.github.steveice10.comcraft.protocol.ComcraftConstants;
import com.github.steveice10.comcraft.protocol.packet.ComcraftPacketUtil;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientLoginPacket implements Packet {
    private int uniqueId;
    private String username;
    private int serverVersion;
    private int apiVersion;

    @SuppressWarnings("unused")
    private ClientLoginPacket(){}

    public ClientLoginPacket(int uniqueId, String username){
      this.uniqueId = uniqueId;
      this.username = username;
      this.serverVersion = ComcraftConstants.SERVER_VERSION;
      this.apiVersion = ComcraftConstants.API_VERSION;
    }

    public int getUniqueId(){
        return this.uniqueId;
    }

    public String getUsername(){
        return this.username;
    }

    public int getServerVersion(){
        return this.serverVersion;
    }

    public int getApiVersion(){
        return this.apiVersion;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.uniqueId = in.readInt();
        this.username = ComcraftPacketUtil.readString(in);
        this.serverVersion = in.readShort();
        this.apiVersion = in.readShort();
    }
    //write
}
