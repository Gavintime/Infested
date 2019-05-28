package net.keckle.infested;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.InfestedBlock;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class InfestedMain implements ModInitializer {

	//ease of use modid string
	private static final String MOD_ID = "infested";
	public static String getMod_ID() {
		return MOD_ID;
	}
	

	//CREATE BLOCKS
	//infested mossy cobble
	public static final InfestedBlock infMossCobble = new InfestedBlock(Blocks.MOSSY_COBBLESTONE, Block.Settings.of(Material.CLAY).strength(0.0F, 0.75F));
	//infested bookshelf
	public static final InfestedBlock infBookShelf = new InfestedBlock(Blocks.BOOKSHELF, Block.Settings.of(Material.CLAY).strength(0.0F,0.75F));


	//CREATE ITEM GROUP
	//create item group for infested items
	private static final ItemGroup infGroup = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "inf_item_group"), () -> new ItemStack(Blocks.MOSSY_COBBLESTONE));


	//initialization
	@Override
	public void onInitialize() {
		
		//REGISTER BLOCKS
		//infested moss cobble and bookshelf
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "infested_mossy_cobblestone"), infMossCobble);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "infested_bookshelf"), infBookShelf);


		//REGISTER ITEMS
		//infested moss cobble and bookshelf
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "infested_mossy_cobblestone"), new BlockItem(infMossCobble, new Item.Settings().itemGroup(infGroup)));
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "infested_bookshelf"), new BlockItem(infBookShelf, new Item.Settings().itemGroup(infGroup)));
		
	}
}
