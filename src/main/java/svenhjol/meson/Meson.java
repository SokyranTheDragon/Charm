package svenhjol.meson;

import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import svenhjol.meson.asm.MesonLoadingPlugin;
import svenhjol.meson.registry.ProxyRegistry;
import svenhjol.meson.registry.VillagerRegistry;

public class Meson
{
    public static final String MOD_ID = "Meson";
    public static boolean DEBUG = false;
    private static boolean hasInit = false;

    public static void log(Object ...out)
    {
        for (Object obj : out) {
            LogManager.getLogger("Meson").info(obj);
        }
    }

    public static void debug(Object ...out)
    {
        if (Meson.DEBUG || !MesonLoadingPlugin.obf) {
            for (Object obj : out) {
                LogManager.getLogger("Meson DEBUG").info(obj);
            }
        }
    }

    public static void fatal(Object ...out)
    {
        for (Object obj : out) {
            LogManager.getLogger("Meson DIED (✖╭╮✖)").fatal(obj);
        }
    }

    public static void runtimeException(String message)
    {
        throw new RuntimeException("MESON DIED (✖╭╮✖): " + message);
    }

    public static void init()
    {
        if (!hasInit) {
            MinecraftForge.EVENT_BUS.register(ProxyRegistry.class);
            MinecraftForge.EVENT_BUS.register(VillagerRegistry.class);
            hasInit = true;
        }
    }
}