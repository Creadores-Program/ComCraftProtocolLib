package com.github.steveice10.comcraft.protocol.packet.client;

import com.github.steveice10.comcraft.protocol.data.game.ActionType;
import com.github.steveice10.comcraft.protocol.data.game.Slot;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientPlayerDataPacket implements Packet {
    private ActionType action;
    private float x;
    private float y;
    private float z;
    private int itemIndex;
    private Slot item;
    
    public ClientPlayerDataPacket(float x, float y, float z){
        this.action = ActionType.MOVE;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public ClientPlayerDataPacket(int itemIndex, Slot item){
        this.action = ActionType.INVENTORY;
        this.itemIndex = itemIndex;
        this.item = item;
    }

    public ClientPlayerDataPacket(){
        this.action = ActionType.QUIT;
    }

    public ActionType getAction(){
        return this.action;
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
                this.x = in.readFloat();
                this.y = in.readFloat();
                this.z = in.readFloat();
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
                out.writeFloat(this.x);
                out.writeFloat(this.y);
                out.writeFloat(this.z);
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
