package net.pl3x.map.essentials.hook;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.Warps;
import org.bukkit.entity.Player;

public class EssentialsHook {
    public static Warps getWarps() {
        return ess().getWarps();
    }

    public static boolean isVanished(Player player) {
        return ess().getUser(player).isVanished();
    }

    public static Essentials ess() {
        return Essentials.getPlugin(Essentials.class);
    }
}
