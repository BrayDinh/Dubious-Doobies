package net.braydinh.testmod.item;

import net.braydinh.testmod.TutorialMod;
import net.braydinh.testmod.block.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TutorialMod.MOD_ID);

    public static final DeferredItem<Item> VIBRATOR = ITEMS.register("vibrator",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BISMUTH = ITEMS.register("bismuth",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.register("raw_bismuth",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MARIJUANA = ITEMS.register("marijuana",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BLUNT = ITEMS.register("blunt",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TOBACCO_LEAF = ITEMS.register("tobacco_leaf",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MARIJUANA_SEEDS = ITEMS.register("marijuana_seeds",
            () -> new ItemNameBlockItem(
                    ModBlocks.MARIJUANA_CROP.get(),
                    new Item.Properties()
            ));
    public static final DeferredItem<Item> TOBACCO_SEEDS = ITEMS.register("tobacco_seeds",
            () -> new ItemNameBlockItem(
                    ModBlocks.TOBACCO_CROP.get(),
                    new Item.Properties()
            ));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);

    }
}
