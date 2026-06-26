package net.ruterm.primitech.fluid;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.ruterm.primitech.PrimiTech;
import net.ruterm.primitech.fluid.custom.FermentedBreadFluid;
import net.ruterm.primitech.fluid.custom.KvassFluid;
import net.ruterm.primitech.item.ModItems;
import net.ruterm.primitech.block.ModBlocks;

import java.util.function.Consumer;

public class ModFluids {
    // ИСПРАВЛЕНО: Теперь это DeferredRegister, у которого есть метод .register()
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, PrimiTech.MODID);

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(Registries.FLUID, PrimiTech.MODID);

    // Твой кастомный тип жидкости со своими текстурами
    public static final DeferredHolder<FluidType, FluidType> FERMENTED_BREAD_TYPE = FLUID_TYPES.register("fermented_bread_fluid",
            () -> new FluidType(FluidType.Properties.create().descriptionId("fluid.primitech.fermented_bread")) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private final ResourceLocation STILL = ResourceLocation.fromNamespaceAndPath(PrimiTech.MODID, "block/fermented_bread_still");
                        private final ResourceLocation FLOWING = ResourceLocation.fromNamespaceAndPath(PrimiTech.MODID, "block/fermented_bread_flow");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOWING;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xFFFFFFFF; // Чистый белый, чтобы текстура не перекрашивалась игрой
                        }
                    });
                }
            });

    public static final DeferredHolder<FluidType, FluidType> KVASS_TYPE = FLUID_TYPES.register("kvass_fluid",
            () -> new FluidType(FluidType.Properties.create().descriptionId("fluid.primitech.kvass")) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private final ResourceLocation STILL = ResourceLocation.fromNamespaceAndPath(PrimiTech.MODID, "block/kvass_still");
                        private final ResourceLocation FLOWING = ResourceLocation.fromNamespaceAndPath(PrimiTech.MODID, "block/kvass_flow");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOWING;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xFFFFFFFF; // Чистый белый, чтобы текстура не перекрашивалась игрой
                        }
                    });
                }
            });

    // Регистрация самой жидкости (Стоячая и Текучая)
    public static final DeferredHolder<Fluid, FermentedBreadFluid.Source> FERMENTED_BREAD = FLUIDS.register("fermented_bread_fluid",
            () -> new FermentedBreadFluid.Source());

    public static final DeferredHolder<Fluid, FermentedBreadFluid.Flowing> FLOWING_FERMENTED_BREAD = FLUIDS.register("flowing_fermented_bread",
            () -> new FermentedBreadFluid.Flowing());

    // Свойства связи предметов, блоков и типов
    public static final BaseFlowingFluid.Properties FERMENTED_BREAD_PROPERTIES = new BaseFlowingFluid.Properties(
            FERMENTED_BREAD_TYPE, FERMENTED_BREAD, FLOWING_FERMENTED_BREAD)
            .bucket(() -> ModItems.FERMENTED_BREAD_BUCKET.get())
            .block(() -> ModBlocks.FERMENTED_BREAD_BLOCK.get());



    public static final DeferredHolder<Fluid, KvassFluid.Source> KVASS = FLUIDS.register("kvass_fluid",
            () -> new KvassFluid.Source());

    public static final DeferredHolder<Fluid, KvassFluid.Flowing> FLOWING_KVASS = FLUIDS.register("flowing_kvass",
            () -> new KvassFluid.Flowing());

    // Свойства связи предметов, блоков и типов
    public static final BaseFlowingFluid.Properties KVASS_PROPERTIES = new BaseFlowingFluid.Properties(
            KVASS_TYPE, KVASS, FLOWING_KVASS)
            .bucket(() -> ModItems.KVASS_BUCKET.get())
            .block(() -> ModBlocks.KVASS_BLOCK.get());

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
        FLUIDS.register(eventBus);
    }
}