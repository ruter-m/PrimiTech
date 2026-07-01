package net.ruterm.primitech.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.ruterm.primitech.PrimiTech;
import net.ruterm.primitech.block.ModBlocks;
import net.ruterm.primitech.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CLUB.get())
                .pattern("  W")
                .pattern(" W ")
                .pattern("W  ")
                .define('W', ItemTags.LOGS)
                .unlockedBy("has_log", has(ItemTags.LOGS))
                .group("club")
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CLUB.get())
                .pattern("W  ")
                .pattern(" W ")
                .pattern("  W")
                .define('W', ItemTags.LOGS)
                .unlockedBy("has_log", has(ItemTags.LOGS))
                .group("club")
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(PrimiTech.MODID, "club_flipped"));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.RYE_BREAD.get())
                .pattern("   ")
                .pattern("BBB")
                .pattern("   ")
                .define('B', ModItems.RYE)
                .unlockedBy("has_rye", has(ModItems.RYE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.FERMENTED_BREAD_BUCKET.get())
                .requires(Items.SUGAR)
                .requires(Items.SUGAR)
                .requires(Items.SUGAR)
                .requires(Items.WATER_BUCKET)
                .requires(ModItems.RYE_BREAD)
                .requires(ModItems.RYE_BREAD)
                .requires(ModItems.RYE_BREAD)
                .unlockedBy("has_rye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RYE.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.KVASS_BUCKET.get())
                .requires(ModItems.KVASS)
                .requires(ModItems.KVASS)
                .requires(ModItems.KVASS)
                .requires(ModItems.KVASS)
                .requires(Items.BUCKET)
                .unlockedBy("has_bucket", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BUCKET))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(PrimiTech.MODID, "kvass_bucket_from_bottles"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.KVASS.get(), 4)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(ModItems.KVASS_BUCKET)
                .unlockedBy("has_bucket", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BUCKET))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(PrimiTech.MODID, "kvass_from_bucket"));
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, PrimiTech.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}