package com.github.steveice10.comcraft.protocol.packet.server;

import com.github.steveice10.comcraft.protocol.ComcraftConstants;
import com.github.steveice10.comcraft.protocol.data.game.Player;
import com.github.steveice10.comcraft.protocol.data.game.Slot;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerWorldInfoPacket implements Packet {
    private int worldVersion;
    private int worldSize;
    private float spawnX;
    private float spawnY;
    private float spawnZ;
    private float x;
    private float y;
    private float z;
    private float pitch;
    private float yaw;
    private Slot[] slots;
    private boolean commandsAllowed;
    private Player[] players;

    @SuppressWarnings("unused")
    private ServerWorldInfoPacket(){}

    public ServerWorldInfoPacket(int worldSize, float spawnX, float spawnY, float spawnZ, float x, float y, float z, float pitch, float yaw, Slot[] slots, boolean commandsAllowed, Player[] players){
        this.worldVersion = ComcraftConstants.WORLD_VERSION;
        this.worldSize = worldSize;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.spawnZ = spawnZ;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.slots = slots;
        this.commandsAllowed = commandsAllowed;
        this.players = players;
    }

    public int getWorldVersion(){
        return this.worldVersion;
    }

    public int getWorldSize(){
        return this.worldSize;
    }

    public float getSpawnX(){
        return this.spawnX;
    }

    public float getSpawnY(){
        return this.spawnY;
    }

    public float getSpawnZ(){
        return this.spawnZ;
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public float getZ(){
        return this.z;
    }

    public float getPitch(){
        return this.pitch;
    }

    public float getYaw(){
        return this.yaw;
    }

    public Slot[] getSlots(){
        return this.slots;
    }

    public boolean isCommandsAllowed(){
        return this.commandsAllowed;
    }

    public Player[] getPlayers(){
        return this.players;
    }

    @Override
    public void read(NetInput in) throws IOException {
        int len = in.readInt();
        this.worldVersion = (int) in.readFloat();
        this.worldSize = in.readInt();
        this.spawnX = in.readFloat();
        this.spawnY = in.readFloat();
        this.spawnZ = in.readFloat();
        in.readInt();
        this.x = in.readFloat();
        this.y = in.readFloat();
        this.z = in.readFloat();
        this.pitch = in.readFloat();
        this.yaw = in.readFloat();
        int lenS = in.readInt();
        this.slots = new Slot[lenS];
        for(int i = 0; i < lenS; i++){
            this.slots[i] = new Slot(in.readInt(), (int) in.readByte());
        }
        this.commandsAllowed = in.readBoolean();
        int lenP = in.readUnsignedByte();
        this.players = new Player[lenP];
        for(int i = 0; i < lenP; i++){
            this.players[i] = new Player(in.readInt(), in.readFloat(), in.readFloat(), in.readFloat());
        }
    }
    @Override
    public void write(NetOutput out) throws IOException {
        out.writeInt(49 + (this.slots.length * 5));
        out.writeFloat((float) this.worldVersion);
        out.writeInt(this.worldSize);
        out.writeFloat(this.spawnX);
        out.writeFloat(this.spawnY);
        out.writeFloat(this.spawnZ);
        out.writeInt(0);
        out.writeFloat(this.x);
        out.writeFloat(this.y);
        out.writeFloat(this.z);
        out.writeFloat(this.pitch);
        out.writeFloat(this.yaw);
        out.writeInt(this.slots.length);
        for(Slot slot : this.slots){
            out.writeInt(slot.itemId);
            out.writeByte(slot.stackSize);
        }
        out.writeBoolean(this.commandsAllowed);
        out.writeByte(this.players.length);
        for(Player player : this.players){
            out.writeInt(player.pId);
            out.writeFloat(player.x);
            out.writeFloat(player.y);
            out.writeFloat(player.z);
        }
    }
    @Override
    public boolean isPriority() {
        return false;
    }
}