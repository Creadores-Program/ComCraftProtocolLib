package com.github.steveice10.comcraft.protocol;

import com.github.steveice10.comcraft.protocol.packet.client.*;
import com.github.steveice10.comcraft.protocol.packet.server.*;
import com.github.steveice10.packetlib.Client;
import com.github.steveice10.packetlib.Server;
import com.github.steveice10.packetlib.Session;
import com.github.steveice10.packetlib.crypt.PacketEncryption;
import com.github.steveice10.packetlib.packet.PacketHeader;
import com.github.steveice10.packetlib.packet.PacketProtocol;

/**
 * Comcraft protocol.
 */
public class ComcraftProtocol extends PacketProtocol {
	private PacketHeader header = new ComcraftPacketHeader();

	private String username;
	private int uniqueId;

    /**
     * Creates a new ComcraftProtocol instance.
     *
     * @param username        Username to use when connecting.
     * @param uniqueId UniqueId key to use when connecting.
	 */
	public ComcraftProtocol(String username, int uniqueId) {
		this.username = username.contains("@") ? username.substring(0, username.indexOf("@")) : username;
		this.uniqueId = uniqueId;
	}

    /**
     * Creates a new ComcraftProtocol instance.
     *
     * Server use when connecting.
     */
	public ComcraftProtocol() {
	}

    @Override
    public String getSRVRecordPrefix() {
        return "_comcraft";
    }

	@Override
	public PacketHeader getPacketHeader() {
		return this.header;
	}

	@Override
	public PacketEncryption getEncryption() {
		return null;
	}

	@Override
	public void newClientSession(Client client, Session session) {
		this.registerIncoming(0x04, ServerWorldInfoPacket.class);
		this.registerIncoming(0x06, ServerPlayerDataPacket.class);
		this.registerIncoming(0x07, ServerChunkDataPacket.class);
		this.registerIncoming(0x0A, ServerBlockChangePacket.class);
		this.registerIncoming(0xFF, ServerDisconnectPacket.class);

		this.registerOutgoing(0x01, ClientLoginPacket.class);
		this.registerOutgoing(0x04, ClientWorldInfoPacket.class);
		this.registerOutgoing(0x06, ClientPlayerDataPacket.class);
		this.registerOutgoing(0x07, ClientChunkDataPacket.class);
		this.registerOutgoing(0x0A, ClientBlockChangePacket.class);
		this.registerOutgoing(0xFF, ClientDisconnectPacket.class);
		session.setFlag(ComcraftConstants.USERNAME_KEY, this.username);
		session.setFlag(ComcraftConstants.UNIQUEID_KEY, this.uniqueId);
		session.addListener(new ClientListener());
	}

	@Override
	public void newServerSession(Server server, Session session) {
		this.registerIncoming(0x01, ClientLoginPacket.class);
		this.registerIncoming(0x04, ClientWorldInfoPacket.class);
		this.registerIncoming(0x06, ClientPlayerDataPacket.class);
		this.registerIncoming(0x07, ClientChunkDataPacket.class);
		this.registerIncoming(0x0A, ClientBlockChangePacket.class);
		this.registerIncoming(0xFF, ClientDisconnectPacket.class);

		this.registerOutgoing(0x04, ServerWorldInfoPacket.class);
		this.registerOutgoing(0x06, ServerPlayerDataPacket.class);
		this.registerOutgoing(0x07, ServerChunkDataPacket.class);
		this.registerOutgoing(0x0A, ServerBlockChangePacket.class);
		this.registerOutgoing(0xFF, ServerDisconnectPacket.class);
		session.addListener(new ServerListener());
	}
}
