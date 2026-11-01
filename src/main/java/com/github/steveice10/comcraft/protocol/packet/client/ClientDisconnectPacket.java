package com.github.steveice10.comcraft.protocol.packet.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientDisconnectPacket implements Packet {
    private float pitch;
    private float yaw;

    @SuppressWarnings("unused")
    private ClientDisconnectPacket(){}

    public ClientDisconnectPacket(float pitch, float yaw){
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public float getPitch(){
        return this.pitch;
    }

    public float getYaw(){
        return this.yaw;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.pitch = in.readFloat();
        this.yaw = in.readFloat();
    }
    @Override
    public void write(NetOutput out) throws IOException {
        out.writeFloat(this.pitch);
        out.writeFloat(this.yaw);
    }
    @Override
    public boolean isPriority() {
        return false;
    }
}