package net.ruterm.primitech.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.ruterm.primitech.PrimiTech;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PrimiTech.MODID);

    public static final DeferredHolder<Item, ClubItem> CLUB = ITEMS.register("club",
            () -> new ClubItem());

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
