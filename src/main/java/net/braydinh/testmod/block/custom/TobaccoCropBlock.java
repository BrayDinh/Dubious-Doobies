package net.braydinh.testmod.block.custom;

import net.braydinh.testmod.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.CropBlock;

public class TobaccoCropBlock extends CropBlock {

    public TobaccoCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected Item getBaseSeedId() {
        return ModItems.TOBACCO_SEEDS.get();
    }
}