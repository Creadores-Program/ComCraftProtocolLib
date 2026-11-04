package com.github.steveice10.comcraft.protocol.packet.client;

import com.github.steveice10.comcraft.protocol.data.game.ActionType;
import com.github.steveice10.comcraft.protocol.data.game.Slot;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientPlayerDataPacket implements Packet {
    private ActionType action;
    private int playerId;
    private float x;
    private float y;
    private float z;
    private int itemIndex;
    private Slot item;

    @SuppressWarnings("unused")
    private ClientPlayerDataPacket(){}

    public ClientPlayerDataPacket(boolean join, int playerId, float x, float y, float z){
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

    public ClientPlayerDataPacket(int itemIndex, Slot item){
        this.action = ActionType.INVENTORY;
        this.itemIndex = itemIndex;
        this.item = item;
    }

    public ClientPlayerDataPacket(int playerId){
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
    public void read(NetInput in) throws IOException {
        this.action = ActionType.fromId(in.readUnsignedByte());
        switch(this.action){
            case JOIN:
            case MOVE:
                this.playerId = in.readInt();
                this.x = in.readFloat();
                this.y = in.readFloat();
                this.z = in.readFloat();
                break;
            case QUIT:
                this.playerId = in.readInt();
                break;
            case INVENTORY:
                this.itemIndex = in.readByte();
                this.item = new Slot((int) in.readShort(), (int) in.readByte());
                break;
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.action.getId());
        switch(this.action){
            case JOIN:
            case MOVE:
                out.writeInt(this.playerId);
                out.writeFloat(this.x);
                out.writeFloat(this.y);
                out.writeFloat(this.z);
                break;
            case QUIT:
                out.writeInt(this.playerId);
                break;
            case INVENTORY:
                out.writeByte(this.itemIndex);
                out.writeShort(this.item.itemId);
                out.writeByte(this.item.stackSize);
                break;
        }
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
