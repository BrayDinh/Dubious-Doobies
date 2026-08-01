package net.braydinh.testmod.block.custom;

import net.braydinh.testmod.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.CropBlock;

public class MarijuanaCropBlock extends CropBlock {

    public MarijuanaCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected Item getBaseSeedId() {
        return ModItems.MARIJUANA_SEEDS.get();
    }
}