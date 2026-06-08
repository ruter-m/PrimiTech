package net.ruterm.primitech.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.ruterm.primitech.PrimiTech;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PrimiTech.MODID);

    public static final Supplier<CreativeModeTab> PRIMITIVE_TOOLS = CREATIVE_MODE_TAB.register("primitive_tools",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CLUB.get()))
                    .title(Component.translatable("creativetab.primitech.primitive_tools"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept((ItemLike) ModItems.CLUB);

                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
