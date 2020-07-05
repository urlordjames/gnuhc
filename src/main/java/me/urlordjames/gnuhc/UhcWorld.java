package me.urlordjames.gnuhc;

//not sure if this has a maven repo, the documentation page is 504ing right now
//if it doesn't work replacing file parameters with absolute paths might fix it
//mvn deploy:deploy-file -Durl=file://lib/Multiverse-Core-4.1.0.jar -Dfile=lib/Multiverse-Core-4.1.0.jar -DgroupId=com.onarandombox.multiversecore -DartifactId=Multiverse-Core -Dpackaging=jar -Dversion=4.1.0
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.*;

public class UhcWorld {
    public static boolean newworld() {
        MultiverseCore plugin = (MultiverseCore) Bukkit.getPluginManager().getPlugin("Multiverse-Core");
        MVWorldManager worldManager = plugin.getMVWorldManager();
        if (worldManager.isMVWorld("uhc")) {
            worldManager.loadWorld("uhc");
            if (!worldManager.deleteWorld("uhc")) {
                return false;
            }
        }
        boolean done = worldManager.addWorld("uhc", World.Environment.NORMAL, null, WorldType.NORMAL, true, null, true);
        MultiverseWorld world = worldManager.getMVWorld("uhc");
        world.setGameMode(GameMode.SURVIVAL);
        world.setAutoHeal(false);
        world.setPVPMode(true);
        World bukkitworld = Bukkit.getWorld("uhc");
        bukkitworld.setGameRule(GameRule.NATURAL_REGENERATION, false);
        world.setSpawnLocation(new Location(bukkitworld, 0, bukkitworld.getHighestBlockYAt(0, 0), 0));
        return done;
    }
}
