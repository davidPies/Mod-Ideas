package satcraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = satcraft.satcraft.MODID, version = satcraft.VERSION, name = satcraft.NAME)
public class trumpmod
{
    public static final String MODID = "satcraft";
    public static final String VERSION = "1.0";
    public static final String NAME = "SAT craft";
    
    	public static Item pan;
    	public static Item pat;

    @SidedProxy(clientSide="satcraft.client", serverSide="satcraft.server")
    public static sharedproxy proxy;
    
    @EventHandler
    public void pre(FMLPreInitializationEvent e){
    	//trumphair = new hair("trumphair", hairt, 0, EntityEquipmentSlot.HEAD);
    	//GameRegistry.registerItem(trumphair, "trumphair");
    	EntityRegistry.registerModEntity(a.class, "spongebob", 0, MODID, 64, 5, true);
        
    }
    @EventHandler
    public void init(FMLInitializationEvent e)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(patty, 0, new ModelResourceLocation(MODID + ":" + patty.getUnlocalizedName().substring(5), "inventory"));
   
    }
}
