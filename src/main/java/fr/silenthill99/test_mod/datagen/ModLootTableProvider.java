package fr.silenthill99.test_mod.datagen;

import fr.silenthill99.test_mod.init.ModBlocks;
import fr.silenthill99.test_mod.init.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.RUBY_BLOCK);
        addDrop(ModBlocks.RAW_RUBY_BLOCK);
        addDrop(ModBlocks.RUBY_ORE, block -> oreDrops(block, ModItems.RAW_RUBY));
        addDrop(ModBlocks.DEEPSLATE_RUBY_ORE, block -> oreDrops(block, ModItems.RAW_RUBY));
        addDrop(ModBlocks.NETHER_RUBY_ORE, block -> oreDrops(block, ModItems.RAW_RUBY));
        addDrop(ModBlocks.END_STONE_RUBY_ORE, block -> oreDrops(block, ModItems.RAW_RUBY));
    }
}
