package com.github.steveice10.comcraft.protocol.packet.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerChunkDataPacket implements Packet {
    
    private int x;
    private int z;
    private byte[] data;

    @SuppressWarnings("unused")
    private ServerChunkDataPacket(){}

    public ServerChunkDataPacket(int x, int z, byte[] data){
        this.x = x;
        this.z = z;
        this.data = data;
    }

    public int getX(){
        return this.x;
    }

    public int getZ(){
        return this.z;
    }

    public byte[] getData(){
        return this.data;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.x = (int) in.readByte();
        this.z = (int) in.readByte();
        int len = in.readInt();
        this.data = in.readBytes(len);
    }
    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.x);
        out.writeByte(this.z);
        out.writeBytes(this.data);
    }
    @Override
    public boolean isPriority() {
        return false;
    }
}