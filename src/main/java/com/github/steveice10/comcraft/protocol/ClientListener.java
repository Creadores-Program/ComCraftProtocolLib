package com.github.steveice10.comcraft.protocol;

import com.github.steveice10.comcraft.protocol.packet.client.ClientLoginPacket;
import com.github.steveice10.comcraft.protocol.packet.server.ServerDisconnectPacket;
import com.github.steveice10.packetlib.event.session.ConnectedEvent;
import com.github.steveice10.packetlib.event.session.PacketReceivedEvent;
import com.github.steveice10.packetlib.event.session.SessionAdapter;

/**
 * Listener used on Comcraft client sessions.
 */
public class ClientListener extends SessionAdapter {
    @Override
    public void connected(ConnectedEvent event) {
        event.getSession().send(new ClientLoginPacket(event.getSession().<int>getFlag(ComcraftConstants.UNIQUEID_KEY), event.getSession().<String>getFlag(ComcraftConstants.USERNAME_KEY)));
    }

    @Override
    public void packetReceived(PacketReceivedEvent event) {
        if(event.getPacket() instanceof ServerDisconnectPacket) {
            event.getSession().disconnect(event.<ServerDisconnectPacket>getPacket().getReason());
        }
    }
}