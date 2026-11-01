package com.github.steveice10.comcraft.protocol.packet.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientChunkDataPacket implements Packet {
    
    private int x;
    private int z;

    @SuppressWarnings("unused")
    private ClientChunkDataPacket(){}

    public ClientChunkDataPacket(int x, int z){
        this.x = x;
        this.z = z;
    }

    public int getX(){
        return this.x;
    }

    public int getZ(){
        return this.z;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.x = (int) in.readByte();
        this.z = (int) in.readByte();
    }
    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.x);
        out.writeByte(this.z);
    }
    @Override
    public boolean isPriority() {
        return false;
    }
}