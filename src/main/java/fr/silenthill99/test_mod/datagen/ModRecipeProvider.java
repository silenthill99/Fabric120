package fr.silenthill99.test_mod.datagen;

import fr.silenthill99.test_mod.init.ModBlocks;
import fr.silenthill99.test_mod.init.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModItems.RAW_RUBY), RecipeCategory.MISC, ModItems.RUBY,
                        0.7f, 100)
                .criterion("unlock", InventoryChangedCriterion.Conditions.items(ModItems.RAW_RUBY))
                .group("ruby")
                .offerTo(exporter, "ruby_from_blasting_raw_ruby");
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItems.RAW_RUBY), RecipeCategory.MISC, ModItems.RUBY,
                        0.7f, 200)
                .criterion("unlock", InventoryChangedCriterion.Conditions.items(ModItems.RAW_RUBY))
                .group("ruby")
                .offerTo(exporter, "ruby_from_smelting_raw_ruby");
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RUBY, RecipeCategory.MISC,
                ModBlocks.RUBY_BLOCK, "ruby_block_from_ruby", null, "ruby_from_ruby_block",
                null);
    }
}
