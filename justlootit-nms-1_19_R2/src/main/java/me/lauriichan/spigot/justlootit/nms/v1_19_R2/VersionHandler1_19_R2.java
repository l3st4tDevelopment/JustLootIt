package me.lauriichan.spigot.justlootit.nms.v1_19_R2;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import me.lauriichan.spigot.justlootit.nms.*;
import me.lauriichan.spigot.justlootit.nms.v1_19_R2.network.*;
import me.lauriichan.spigot.justlootit.nms.v1_19_R2.packet.*;

import net.minecraft.network.protocol.game.*;

public final class VersionHandler1_19_R2 extends VersionHandler implements IServiceProvider {

    private final PacketManager1_19_R2 packetManager;
    private final VersionHelper1_19_R2 versionHelper;

    public VersionHandler1_19_R2(IServiceProvider provider) {
        super(provider);
        this.packetManager = new PacketManager1_19_R2(this);
        this.versionHelper = new VersionHelper1_19_R2(this);
    }

    @Override
    protected void onEnable(PluginManager pluginManager) {
        registerPackets();
        packetManager.close();
    }

    private void registerPackets() {
        // Incoming packets (nms)
        packetManager.register(ServerboundUseItemOnPacket.class, PacketInUseItemOn1_19_R2::new);
        packetManager.register(ServerboundSwingPacket.class, PacketInSwingArm1_19_R2::new);
        packetManager.register(ServerboundContainerClickPacket.class, PacketInContainerClick1_19_R2::new);
        // Outgoing packets (nms)
        
        // Outgoing packets (adapter)
    }

    @Override
    public PacketManager1_19_R2 getPacketManager() {
        return packetManager;
    }

    @Override
    public VersionHelper1_19_R2 getVersionHelper() {
        return versionHelper;
    }
    
    @Override
    protected PlayerAdapter createAdapter(Player player) {
        return new PlayerAdapter1_19_R2(this, player);
    }

    @Override
    protected void terminateAdapter(PlayerAdapter adapter) {
        if (!(adapter instanceof PlayerAdapter1_19_R2)) {
            return;
        }
        ((PlayerAdapter1_19_R2) adapter).terminate();
    }

    @Override
    protected LevelAdapter createAdapter(World world) {
        return null;
    }

    @Override
    protected void terminateAdapter(LevelAdapter adapter) {
        // TODO Auto-generated method stub
        
    }

}
