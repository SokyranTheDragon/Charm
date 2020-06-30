package svenhjol.charm.world.module;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import svenhjol.charm.Charm;
import svenhjol.charm.base.CharmCategories;
import svenhjol.charm.world.client.renderer.EndermitePowderRenderer;
import svenhjol.charm.world.entity.EndermitePowderEntity;
import svenhjol.charm.world.item.EndermitePowderItem;
import svenhjol.meson.MesonModule;
import svenhjol.meson.handler.RegistryHandler;
import svenhjol.meson.iface.Module;

@Module(mod = Charm.MOD_ID, category = CharmCategories.WORLD, hasSubscriptions = true,
    description = "Endermite Powder has a chance of being dropped from an Endermite.\n" +
        "Use it in the End to help locate an End City.")
public class EndermitePowder extends MesonModule {
    public static EndermitePowderItem item;
    public static EntityType<EndermitePowderEntity> entity;

    @Override
    public void init() {
        item = new EndermitePowderItem(this);
        ResourceLocation res = new ResourceLocation(Charm.MOD_ID, "endermite_powder");

        entity = EntityType.Builder.<EndermitePowderEntity>create(EndermitePowderEntity::new, EntityClassification.MISC)
            .setTrackingRange(80)
            .setUpdateInterval(10)
            .setShouldReceiveVelocityUpdates(false)
            .size(2.0F, 2.0F)
            .build(res.getPath());

        RegistryHandler.registerEntity(entity, res);
    }

    @Override
    public void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(entity, EndermitePowderRenderer::new);
    }

    @SubscribeEvent
    public void onEndermiteDrops(LivingDropsEvent event) {
        if (!event.getEntityLiving().world.isRemote
            && event.getEntityLiving() instanceof EndermiteEntity
            && event.getSource().getTrueSource() instanceof PlayerEntity
            && event.getEntityLiving().world.rand.nextFloat() <= 0.5F + (0.1F * event.getLootingLevel())
        ) {
            EndermiteEntity endermite = (EndermiteEntity) event.getEntityLiving();
            ItemStack stack = new ItemStack(item);
            BlockPos endermitePos = endermite.func_233580_cy_();
            event.getDrops().add(new ItemEntity(endermite.getEntityWorld(), endermitePos.getX(), endermitePos.getY(), endermitePos.getZ(), stack));
        }
    }
}
