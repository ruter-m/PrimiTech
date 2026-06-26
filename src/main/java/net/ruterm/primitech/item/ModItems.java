package net.ruterm.primitech.item;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.ruterm.primitech.PrimiTech;
import net.ruterm.primitech.block.ModBlocks;
import net.ruterm.primitech.fluid.ModFluids;
import net.ruterm.primitech.item.custom.ClubItem;
import net.ruterm.primitech.item.custom.KvassItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PrimiTech.MODID);

    public static final DeferredHolder<Item, ClubItem> CLUB = ITEMS.register("club",
            () -> new ClubItem());

    public static final DeferredItem<Item> RYE_SEEDS = ITEMS.register("rye_seeds",
            () -> new ItemNameBlockItem(ModBlocks.RYE_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> RYE = ITEMS.register("rye",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RYE_BREAD = ITEMS.registerItem("rye_bread",
            (properties) -> new Item(properties.food(ModFoodProperties.RYE_BREAD)));

    public static final DeferredItem<KvassItem> KVASS = ITEMS.registerItem("kvass",
            (properties) -> new KvassItem(properties.food(ModFoodProperties.KVASS)));

    public static final DeferredItem<BucketItem> FERMENTED_BREAD_BUCKET = ITEMS.registerItem("fermented_bread_bucket",
            properties -> new BucketItem(ModFluids.FERMENTED_BREAD.get(), properties));

    public static final DeferredItem<BucketItem> KVASS_BUCKET = ITEMS.registerItem("kvass_bucket",
            properties -> new BucketItem(ModFluids.KVASS.get(), properties));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
