package net.pl3x.map.essentials.hook;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.Warps;

public class EssentialsHook {
    public static Warps getWarps() {
        Essentials ess = Essentials.getPlugin(Essentials.class);
        return ess.getWarps();
    }
}
