package net.ruterm.primitech.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.ruterm.primitech.block.ModBlocks;
import net.ruterm.primitech.fluid.custom.FermentedBreadFluid;

import java.util.function.Supplier;

public class FermentedBreadLiquidBlock extends LiquidBlock {

    public FermentedBreadLiquidBlock(Supplier<? extends FlowingFluid> fluid, Properties properties) {
        super(fluid.get(), properties);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        // Разрешаем блоку получать случайные тики, только если это источник (LEVEL = 0)
        return state.getValue(LEVEL) == 0;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Проверяем, что это всё ещё источник
        if (state.getValue(LEVEL) == 0) {

            // Если нужно, чтобы превращалось ХАОТИЧНО, но быстро — убирай if ниже.
            // Если нужно приблизить к 7 минутам (8400 тиков), задаём шанс (например, 1 из 6 тиков сработает):
            if (random.nextInt(6) == 0) {

                // Заменяем блок источника на воду (или любую твою жидкость/блок)
                level.setBlock(pos, ModBlocks.KVASS_BLOCK.get().defaultBlockState(), 3);

                // Обновляем физику соседей, чтобы старые течения пропали, а новые потекли правильно
                level.blockUpdated(pos, ModBlocks.KVASS_BLOCK.get());
            }
        }
    }
}