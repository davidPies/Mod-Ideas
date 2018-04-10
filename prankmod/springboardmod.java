package springboardmod;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = springboardmod.MODID, name = springboardmod.NAME, version = springboardmod.VERSION)
@Mod.EventBusSubscriber(modid = springboardmod.MODID)
public class springboardmod
{
    public static final String MODID = "springboardmod";
    public static final String NAME = "Springboard Mod";
    public static final String VERSION = "1.0";
    
    public static ISBRegistry[] items = new ISBRegistry[]{new SBBook(), new ItemMatch(), new ItemNitro(), new ItemThrownNitro()};
    public static ISBRegistry[] blocks = new ISBRegistry[]{new BlockBook()};
	
    @SidedProxy(clientSide="springboardmod.Client", serverSide="springboardmod.Server")
    public static SharedProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	this.addEntity(EntityNitro.class, "EntityNitro", 2);
    	//SBRegistry.addAllPre();
    	//RecipeList
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	//SBRegistry.addAllInit();
    	//MinecraftForge.EVENT_BUS.register(new SBEvents());
    	//GameRegistry.addShapelessRecipe(new ResourceLocation(springboardmod.MODID, "csbbook"), null, new ItemStack(Block.getBlockFromItem(items[0].getItem()), 1, 0), Ingredient.fromItem(Items.BOOK), Ingredient.fromItem(new ItemStack(Items.DYE, 1, 4).getItem()));
    	//GameRegistry.addShapelessRecipe(new ResourceLocation(springboardmod.MODID, "csbmatch"), null, new ItemStack(items[1].getItem(), 5, 0), Ingredient.fromItem(Items.STICK), Ingredient.fromItem(new ItemStack(Items.COAL, 1, 0).getItem()), Ingredient.fromItem(Items.FLINT));
    	this.addEntityInit();
    	GameRegistry.addShapelessRecipe(new ResourceLocation(springboardmod.MODID, "csbbook"), null, new ItemStack(Block.getBlockFromItem(items[0].getItem()), 3, 0), Ingredient.fromItem(Items.BOOK), Ingredient.fromStacks(new ItemStack(Items.DYE, 1, 4)));
    	GameRegistry.addShapelessRecipe(new ResourceLocation(springboardmod.MODID, "csbmatch"), null, new ItemStack(items[1].getItem(), 5, 0), Ingredient.fromItem(Items.STICK), Ingredient.fromStacks(new ItemStack(Items.COAL, 1, 0)), Ingredient.fromItem(Items.FLINT));
    	MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent	
	public static void registryIEvent(RegistryEvent.Register<Item> e) {
    	//for(int i = 0; i < blocks.length; i++) {
		//	//addBlock(blocks[i]);
    	//}
		for(int i = 0; i < items.length; i++) {
			//GameRegistry.addShapelessRecipe(new ResourceLocation(springboardmod.MODID, "csbbook"), new ResourceLocation(springboardmod.MODID, "csb"), new ItemStack(new SBBook().getItem()), new Ingredient[] {Ingredient.fromStacks(new ItemStack(Items.BOOK, 1, 0), new ItemStack(Items.DYE, 1, 11))});
			//addItem(items[i]);
			if(i > 0) {
				addItem(items[i]);
			}
			e.getRegistry().register(items[i].getItem());
		}
	}
    @SubscribeEvent	
	public static void registryBEvent(RegistryEvent.Register<Block> e) {
		for(int i = 0; i < blocks.length; i++) {
			//addBlock(blocks[i]);
			e.getRegistry().register(Block.getBlockFromItem(items[0].getItem()));
		}
	}
    @SubscribeEvent	
    public static void modelEvent(ModelRegistryEvent e) {
		for(int i = 0; i < items.length; i++) {
			addTexure(items[i]);
		}
	}
	private static void addTexure(ISBRegistry item) {
		ModelLoader.setCustomModelResourceLocation(item.getItem(), 0, new ModelResourceLocation(springboardmod.MODID + ":" + item.getName(), "inventory"));
	}
	private static void addEntity(Class<? extends Entity> entity, String name, int id) {
		EntityRegistry.registerModEntity(new ResourceLocation(springboardmod.MODID, name), entity, name, 123 + id, springboardmod.MODID, 16, 1, false, 225631, 212121);
	}
	private static void addEntityInit() {//Class<Entity> entity, String name, int id) {
		RenderingRegistry.registerEntityRenderingHandler(EntityNitro.class, new IRenderFactory<EntityNitro>() {

			@Override
			public Render createRenderFor(RenderManager manager) {
				return new RenderSnowball(manager, springboardmod.items[3].getItem(), Minecraft.getMinecraft().getRenderItem());
			}});
	}
	public static void addItem(ISBRegistry reg) {
		reg.getItem().setCreativeTab(Tabs.Item);
		reg.getItem().setUnlocalizedName(reg.getName());
		reg.getItem().setRegistryName(springboardmod.MODID, reg.getName());
	}
    public static void addNormalBlock(ISBRegistry reg) {
		reg.getSBBlock().setCreativeTab(Tabs.Block);
		reg.getSBBlock().setUnlocalizedName(reg.getName());
		reg.getSBBlock().setRegistryName(springboardmod.MODID, reg.getName());
	}
    @SubscribeEvent	
	public static void furnaceEvent(FurnaceFuelBurnTimeEvent e) {
    	if(e.getItemStack().getItem() instanceof ItemNitro) {
    		e.setBurnTime(300);
    	}
    }
}
