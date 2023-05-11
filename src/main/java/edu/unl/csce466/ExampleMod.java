package edu.unl.csce466;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import edu.unl.csce466.event.ModEvents;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.LogicalSide;
import org.joml.Vector3d;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import edu.unl.csce466.screens.ImGuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.ClientCommandHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MODID)
@Mod.EventBusSubscriber(modid=ExampleMod.MODID, value=Dist.CLIENT)
public class ExampleMod{
	// Define mod id in a common place for everything to reference
	public static final String MODID = "examplemod";
	// Directly reference a slf4j logger
	private static final Logger LOGGER = LogUtils.getLogger();
	// Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	// Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

	// Creates a new Block with the id "examplemod:example_block", combining the namespace and path
	public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
	// Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
	public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));

	public static final ImGuiScreen IMGUI_SCREEN = ImGuiScreen.getInstance();

	public void Begin() {

	}

	public ExampleMod(){
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		// Register the commonSetup method for modloading
		modEventBus.addListener(this::commonSetup);

		// Register the Deferred Register to the mod event bus so blocks get registered
		BLOCKS.register(modEventBus);
		// Register the Deferred Register to the mod event bus so items get registered
		ITEMS.register(modEventBus);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);

		MinecraftForge.EVENT_BUS.register(IMGUI_SCREEN);
		// Register the item to a creative tab
		modEventBus.addListener(this::addCreative);
	}

	public static class Zeus {

		public void Init()
		{
			System.out.println("Zeus Activated");

			Player player = Minecraft.getInstance().player;
			player.sendSystemMessage(Component.literal("You feel a surge of electricity course through your veins..."));

			ModEvents.ForgeEvents.start = true;



			//PlayerInteractEvent.RightClickEmpty // do event handling
		}

		public void LevelUp()
		{
			Player player = Minecraft.getInstance().player;
			player.giveExperienceLevels(50);
		}

		public void Health()
		{
			Player player = Minecraft.getInstance().player;
			player.setAbsorptionAmount(100);
		}


	}


	private void commonSetup(final FMLCommonSetupEvent event){
		// Some common setup code
		LOGGER.info("HELLO FROM COMMON SETUP");
		LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
	}

	private void addCreative(CreativeModeTabEvent.BuildContents event){
		if (event.getTab() == CreativeModeTabs.BUILDING_BLOCKS)
			event.accept(EXAMPLE_BLOCK_ITEM);
	}

	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event){
		// Do something when the server starts
		LOGGER.info("HELLO from server starting");
	}

	// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
	@Mod.EventBusSubscriber(modid = MODID,  bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientModEvents {
		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event) {
			// Some client setup code
			LOGGER.info("HELLO FROM CLIENT SETUP");
			LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

			IMGUI_SCREEN.getInstance().init();
		}
	}

	@SubscribeEvent
	public void onKeyInput(InputEvent.Key event){
		if(Minecraft.getInstance().player == null) return;
		if(Minecraft.getInstance().screen != null ) return;
		if(event.getKey() == GLFW.GLFW_KEY_L){
			LOGGER.info("L");
			Minecraft.getInstance().setScreen(IMGUI_SCREEN);
		}
	}
}
