package net.ruterm.primitech.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.ruterm.primitech.PrimiTech;
import net.ruterm.primitech.block.custom.FermentedBreadLiquidBlock;
import net.ruterm.primitech.block.custom.RyeCropBlock;
import net.ruterm.primitech.fluid.ModFluids;
import net.ruterm.primitech.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(PrimiTech.MODID);

    public static final DeferredBlock<Block> RYE_CROP = BLOCKS.register("rye_crop",
            () -> new RyeCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static final DeferredBlock<FermentedBreadLiquidBlock> FERMENTED_BREAD_BLOCK = BLOCKS.register("fermented_bread_block",
            () -> new FermentedBreadLiquidBlock(ModFluids.FERMENTED_BREAD,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)
                            .noCollission()
                            .strength(100.0F)
                            .pushReaction(net.minecraft.world.level.material.PushReaction.DESTROY)
                            .noLootTable()
                            .randomTicks()));

    public static final DeferredBlock<LiquidBlock> KVASS_BLOCK = BLOCKS.registerBlock("kvass_block",
            name -> new LiquidBlock(
                    ModFluids.KVASS.get(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)
                            .noCollission()
                            .strength(100.0F)
                            .pushReaction(PushReaction.DESTROY)
                            .noLootTable()
            ));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block, Supplier<? extends T> Block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, Block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
