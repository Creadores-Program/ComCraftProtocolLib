package com.github.steveice10.comcraft.protocol.packet.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerBlockChangePacket implements Packet {
    
    private int x;
    private int y;
    private int z;
    private int metadata;
    private int id;
    private int chunkX;
    private int chunkZ;

    @SuppressWarnings("unused")
    private ServerBlockChangePacket(){}

    public ServerBlockChangePacket(int chunkX, int chunkZ, int x, int y, int z, int id, int metadata){
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = id;
        this.metadata = metadata;
    }

    public int getChunkX(){
        return this.chunkX;
    }

    public int getChunkZ(){
        return this.chunkZ;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getZ(){
        return this.z;
    }

    public int getId(){
        return this.id;
    }

    public int getMetadata(){
        return this.metadata;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.chunkX = (int) in.readByte();
        this.chunkZ = (int) in.readByte();
        this.x = (int) in.readByte();
        this.y = (int) in.readByte();
        this.z = (int) in.readByte();
        this.id = in.readUnsignedByte();
        this.metadata = in.readUnsignedByte();
    }
    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.chunkX);
        out.writeByte(this.chunkZ);
        out.writeByte(this.x);
        out.writeByte(this.y);
        out.writeByte(this.z);
        out.writeByte(this.id);
        out.writeByte(this.metadata);
    }
    @Override
    public boolean isPriority() {
        return false;
    }
}