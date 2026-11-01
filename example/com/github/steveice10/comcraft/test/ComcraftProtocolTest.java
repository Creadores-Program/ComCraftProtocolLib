package com.github.steveice10.comcraft.test;

import com.github.steveice10.comcraft.protocol.ComcraftConstants;
import com.github.steveice10.comcraft.protocol.ComcraftProtocol;
import com.github.steveice10.comcraft.protocol.packet.client.ClientLoginPacket;
import com.github.steveice10.comcraft.protocol.packet.server.ServerWorldInfoPacket;
import com.github.steveice10.comcraft.protocol.data.game.Player;
import com.github.steveice10.comcraft.protocol.data.game.Slot;
import com.github.steveice10.packetlib.Client;
import com.github.steveice10.packetlib.Server;
import com.github.steveice10.packetlib.event.server.ServerAdapter;
import com.github.steveice10.packetlib.event.server.SessionAddedEvent;
import com.github.steveice10.packetlib.event.server.SessionRemovedEvent;
import com.github.steveice10.packetlib.event.session.DisconnectedEvent;
import com.github.steveice10.packetlib.event.session.PacketReceivedEvent;
import com.github.steveice10.packetlib.event.session.SessionAdapter;
import com.github.steveice10.packetlib.tcp.comcraft.TcpSessionFactory;

public class ComcraftProtocolTest {
    private static final boolean SPAWN_SERVER = true;
    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 25565;
    private static final String USERNAME = "Username";
    private static final int UNIQUEID = 0;

    public static void main(String[] args){
        if(SPAWN_SERVER){
            Server server = new Server(SERVER_HOST, SERVER_PORT, ComcraftProtocol.class, new TcpSessionFactory());
            server.addListener(new ServerAdapter() {
                @Override
                public void sessionAdded(SessionAddedEvent event) {
                    event.getSession().addListener(new SessionAdapter() {
                        @Override
                        public void packetReceived(PacketReceivedEvent event) {
                            if(event.getPacket() instanceof ClientLoginPacket) {
                                ClientLoginPacket packet = event.getPacket();
                                // Store username in session flags
                                event.getSession().setFlag(ComcraftConstants.USERNAME_KEY, packet.getUsername());
                                event.getSession().send(new ServerWorldInfoPacket(127, 0, 0, 0, 0, 0, 0, 0, 0, new Slot[0], true, new Player[0]));
                            }
                        }
                    });
                }
                @Override
                public void sessionRemoved(SessionRemovedEvent event) {
                    System.out.println("Closing server.");
                    event.getServer().close();
                }
            });
            server.bind();
        }
        login();
    }

    public static void login(){
        ComcraftProtocol protocol;
        String host = SERVER_HOST;
        int port = SERVER_PORT;
        int uniqueId = UNIQUEID;
        String cleanName = USERNAME.contains("@") ? USERNAME.substring(0, USERNAME.indexOf("@")) : USERNAME;    
        protocol = new ComcraftProtocol(cleanName, uniqueId);
        Client client = new Client(host, port, protocol, new TcpSessionFactory());
        client.getSession().addListener(new SessionAdapter() {
            @Override
            public void packetReceived(PacketReceivedEvent event) {
                if(event.getPacket() instanceof ServerWorldInfoPacket) {
                    ServerWorldInfoPacket packet = event.getPacket();
                    event.getSession().send(new ClientChunkDataPacket((int) Math.floor(packet.getX() / 16.0f), (int) Math.floor(packet.getZ() / 16.0f)));
                }
            }

            @Override
            public void disconnected(DisconnectedEvent event) {
                System.out.println("Disconnected: " + event.getReason());
            }
        });

        client.getSession().connect(true);
        while(client.getSession().isConnected()) {
            try { Thread.sleep(5); } catch(InterruptedException e) { break; }
        }
    }
}