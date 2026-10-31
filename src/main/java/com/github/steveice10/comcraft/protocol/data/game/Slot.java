package com.github.steveice10.comcraft.protocol.data.game;

public class Slot{
    public int itemId;
    public volatile int stackSize;
    public Slot(int itemId, int stackSize){
        this.itemId = itemId;
        this.stackSize = stackSize;
    }
}