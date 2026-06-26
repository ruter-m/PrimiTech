package net.ruterm.primitech.fluid.custom;

import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.ruterm.primitech.fluid.ModFluids;

public abstract class FermentedBreadFluid extends BaseFlowingFluid {

    protected FermentedBreadFluid(Properties properties) {
        super(properties);
    }

    // Этот метод ДОЛЖЕН БЫТЬ ЗДЕСЬ и возвращать наш зарегистрированный тип
    @Override
    public FluidType getFluidType() {
        return ModFluids.FERMENTED_BREAD_TYPE.get();
    }

    // Подкласс для стоячей жидкости
    public static class Source extends FermentedBreadFluid {
        public Source() {
            super(ModFluids.FERMENTED_BREAD_PROPERTIES);
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }

        @Override
        public int getAmount(FluidState state) {
            return 8;
        }
    }

    // Подкласс для текучей жидкости
    public static class Flowing extends FermentedBreadFluid {
        public Flowing() {
            super(ModFluids.FERMENTED_BREAD_PROPERTIES);
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }

        @Override
        public boolean isSame(Fluid fluid) {
            // Обязательно вызываем .get(), чтобы достать саму жидкость из холдера!
            return fluid == ModFluids.FERMENTED_BREAD.get() || fluid == ModFluids.FLOWING_FERMENTED_BREAD.get();
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);

        }
    }
}