package net.pl3x.map.essentials.hook;

import net.pl3x.map.api.Key;
import net.pl3x.map.api.Pl3xMapProvider;
import net.pl3x.map.api.SimpleLayerProvider;
import net.pl3x.map.essentials.Logger;
import net.pl3x.map.essentials.configuration.Config;
import net.pl3x.map.essentials.configuration.WorldConfig;
import net.pl3x.map.essentials.task.Pl3xMapTask;
import org.bukkit.plugin.Plugin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public class Pl3xMapHook {
    public static final Key warpIconKey = Key.of("essentials_warp_icon");

    private static final Map<UUID, Pl3xMapTask> providers = new HashMap<>();

    public static void load(Plugin plugin) {
        try {
            BufferedImage icon = ImageIO.read(new File(plugin.getDataFolder(), "warp.png"));
            Pl3xMapProvider.get().iconRegistry().register(warpIconKey, icon);
        } catch (IOException e) {
            Logger.log().log(Level.WARNING, "Failed to register warp icon", e);
        }

        Pl3xMapProvider.get().mapWorlds().forEach(mapWorld -> {
            WorldConfig worldConfig = WorldConfig.get(mapWorld);
            if (worldConfig.ENABLED) {
                SimpleLayerProvider provider = SimpleLayerProvider.builder("DeathSpots")
                        .showControls(worldConfig.WARPS_SHOW_CONTROLS)
                        .defaultHidden(worldConfig.WARPS_CONTROLS_HIDDEN)
                        .build();
                mapWorld.layerRegistry().register(Key.of("essentials_" + mapWorld.uuid() + "_warps"), provider);
                Pl3xMapTask task = new Pl3xMapTask(mapWorld, worldConfig, provider);
                task.runTaskTimerAsynchronously(plugin, 0, 20L * Config.UPDATE_INTERVAL);
                providers.put(mapWorld.uuid(), task);
            }
        });
    }

    public static void disable() {
        providers.values().forEach(Pl3xMapTask::disable);
        providers.clear();
    }
}
