package fr.silenthill99.test_mod.datagen;

import fr.silenthill99.test_mod.init.ModBlocks;
import fr.silenthill99.test_mod.init.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.BeetrootsBlock;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.RUBY_BLOCK);
        addDrop(ModBlocks.RAW_RUBY_BLOCK);
        addDrop(ModBlocks.SOUND_BLOCK);
        addDrop(ModBlocks.RUBY_ORE, block -> copperLikeOreDrops(block, ModItems.RAW_RUBY));
        addDrop(ModBlocks.DEEPSLATE_RUBY_ORE, block -> copperLikeOreDrops(block, ModItems.RAW_RUBY));
        addDrop(ModBlocks.NETHER_RUBY_ORE, block -> copperLikeOreDrops(block, ModItems.RAW_RUBY));
        addDrop(ModBlocks.END_STONE_RUBY_ORE, block -> copperLikeOreDrops(block, ModItems.RAW_RUBY));

        addDrop(ModBlocks.RUBY_STAIRS);
        addDrop(ModBlocks.RUBY_SLAB, this::slabDrops);
        addDrop(ModBlocks.RUBY_BUTTON);
        addDrop(ModBlocks.RUBY_PRESSURE_PLATE);
        addDrop(ModBlocks.RUBY_FENCE);
        addDrop(ModBlocks.RUBY_FENCE_GATE);
        addDrop(ModBlocks.RUBY_WALL);
        addDrop(ModBlocks.RUBY_DOOR, this::doorDrops);
        addDrop(ModBlocks.RUBY_TRAPDOOR);
        customCropDrops(ModBlocks.TOMATO_CROPS_BLOCK, ModItems.TOMATO, ModItems.TOMATO_SEEDS, 5);
    }

    public LootTable.Builder copperLikeOreDrops(Block drop, Item item) {
        return dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ItemEntry.builder(item)
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F)))
                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }

    private void customCropDrops(Block block, Item loot, Item seed, int age) {
        LootCondition.Builder builder = BlockStatePropertyLootCondition.builder(block)
                .properties(StatePredicate.Builder.create().exactMatch(BeetrootsBlock.AGE, age));
        addDrop(block, cropDrops(block, loot, seed, builder));
    }
}
