package net.ruterm.primitech.datagen;

import net.ruterm.primitech.PrimiTech;
import net.ruterm.primitech.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PrimiTech.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handheldItem(ModItems.CLUB.get());

        basicItem(ModItems.RYE_SEEDS.get());
        basicItem(ModItems.RYE.get());
        basicItem(ModItems.RYE_BREAD.get());

    }
}