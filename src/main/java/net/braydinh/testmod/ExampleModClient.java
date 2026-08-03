package net.braydinh.testmod;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.braydinh.testmod.item.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = TutorialMod.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = TutorialMod.MOD_ID, value = Dist.CLIENT)
public class ExampleModClient {
    public ExampleModClient(
            IEventBus modEventBus,
            ModContainer container
    ) {
        container.registerExtensionPoint(
                IConfigScreenFactory.class,
                ConfigurationScreen::new
        );

        modEventBus.addListener(
                ExampleModClient::registerClientExtensions
        );
    }

    private static void registerClientExtensions(
            RegisterClientExtensionsEvent event
    ) {
        event.registerItem(
                new BluntClientExtensions(),
                ModItems.BLUNT.get()
        );
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        TutorialMod.LOGGER.info("HELLO FROM CLIENT SETUP");
        TutorialMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
}
