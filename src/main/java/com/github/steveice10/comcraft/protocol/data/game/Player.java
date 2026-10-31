package com.github.steveice10.comcraft.protocol.data.game;

public class Player{
    public volatile float x;
    public volatile float y;
    public volatile float z;
    public final int pId;
    public Player(int pId, float x, float y, float z){
        this.pId = pId;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}