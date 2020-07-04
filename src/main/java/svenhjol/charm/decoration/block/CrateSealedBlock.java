package svenhjol.charm.decoration.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import svenhjol.charm.base.CharmSounds;
import svenhjol.charm.decoration.module.Crates;
import svenhjol.meson.MesonModule;
import svenhjol.meson.enums.IChestMaterialType;

public class CrateSealedBlock extends CrateBaseBlock {
    public CrateSealedBlock(MesonModule module, IChestMaterialType wood) {
        super(module, wood.getName() + "_crate_sealed", wood);
    }

    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof IInventory) {
                InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
                world.updateComparatorOutputLevel(pos, this);
                world.playSound(null, pos, CharmSounds.WOOD_SMASH, SoundCategory.BLOCKS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
            }
        }
    }

    public static Block getBlockByWood(IChestMaterialType wood) {
        return Crates.sealedTypes.get(wood);
    }
}
