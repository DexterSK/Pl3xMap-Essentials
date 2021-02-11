package net.pl3x.map.essentials.hook;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.Warps;

public class EssentialsHook {
    public static Warps getWarps() {
        return ess().getWarps();
    }

    private static Essentials ess() {
        return Essentials.getPlugin(Essentials.class);
    }
}
