package com.github.steveice10.comcraft.protocol.packet.server;

import com.github.steveice10.comcraft.protocol.data.game.ActionType;
import com.github.steveice10.comcraft.protocol.data.game.Slot;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerPlayerDataPacket implements Packet {
    private ActionType action;
    private int playerId;
    private float x;
    private float y;
    private float z;
    private int itemIndex;
    private Slot item;

    @SuppressWarnings("unused")
    private ServerPlayerDataPacket(){}

    public ServerPlayerDataPacket(boolean join, int playerId, float x, float y, float z){
        if(join){
            this.action = ActionType.JOIN;
        }else{
            this.action = ActionType.MOVE;
        }
        this.playerId = playerId;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public ServerPlayerDataPacket(int playerId, int itemIndex, Slot item){
        this.action = ActionType.INVENTORY;
        this.playerId = playerId;
        this.itemIndex = itemIndex;
        this.item = item;
    }

    public ServerPlayerDataPacket(int playerId){
        this.action = ActionType.QUIT;
        this.playerId = playerId;
    }

    public ActionType getAction(){
        return this.action;
    }

    public int getPlayerId(){
        return this.playerId;
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

    public int getItemIndex(){
        return this.itemIndex;
    }

    public Slot getItem(){
        return this.item;
    }

    @Override
    public void read(NetInput in) throws IOException {}

    @Override
    public void write(NetOutput out) throws IOException {}

    @Override
    public boolean isPriority() {
        return false;
    }
}
