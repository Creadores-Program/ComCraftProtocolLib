package com.github.steveice10.comcraft.protocol;

import com.github.steveice10.comcraft.protocol.packet.client.ClientLoginPacket;
import com.github.steveice10.comcraft.protocol.packet.server.ServerDisconnectPacket;
import com.github.steveice10.packetlib.event.session.DisconnectingEvent;
import com.github.steveice10.packetlib.event.session.PacketReceivedEvent;
import com.github.steveice10.packetlib.event.session.SessionAdapter;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Listener used on Comcraft server sessions.
 */
public class ServerListener extends SessionAdapter {
    @Override
    public void packetReceived(PacketReceivedEvent event) {
        if(event.getPacket() instanceof ClientLoginPacket) {
            ClientLoginPacket packet = event.getPacket();
            event.getSession().setFlag(ComcraftConstants.USERNAME_KEY, packet.getUsername());
            event.getSession().setFlag(ComcraftConstants.UNIQUEID_KEY, packet.getUniqueId());
        }
    }

    @Override
    public void disconnecting(DisconnectingEvent event) {
        event.getSession().send(new ServerDisconnectPacket(event.getReason()));
    }
}