package svenhjol.charm;

import net.minecraftforge.fml.common.Mod;
import svenhjol.charm.base.CharmMessages;
import svenhjol.charm.module.*;
import svenhjol.meson.MesonMod;
import svenhjol.meson.MesonModule;

import java.util.Arrays;
import java.util.List;

@Mod(Charm.MOD_ID)
public class Charm extends MesonMod {
    public static final String MOD_ID = "charm";

    public Charm() {
        super(MOD_ID);
        CharmMessages.init(this);
    }

    protected List<Class<? extends MesonModule>> getModules() {
        return Arrays.asList(
            AnvilImprovements.class,
            AutomaticRecipeUnlock.class,
            BeaconsHealMobs.class,
            CaveSpidersDropCobwebs.class,
            ChickensDropFeathers.class,
            CraftingInventory.class,
            Crates.class,
            ExtractEnchantments.class,
            FeatherFallingCrops.class,
            HuskImprovements.class,
            LanternsObeyGravity.class,
            ArmorInvisibility.class,
            MineshaftImprovements.class,
            MoreVillageBiomes.class,
            MoreVillagerTrades.class,
            MusicImprovements.class,
            ParrotsStayOnShoulder.class,
            PathToDirt.class,
            RemoveNitwits.class,
            RemovePotionGlint.class,
            StackableBooks.class,
            StackablePotions.class,
            StrayImprovements.class,
            TamedAnimalsNoDamage.class,
            UseTotemFromInventory.class,
            VillagersFollowEmeralds.class,
            WanderingTraderImprovements.class,
            WitchesDropLuck.class
        );
    }
}
