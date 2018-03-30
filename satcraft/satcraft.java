package satcraft;

import this;
import satcraft.satcraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = satcraft.MODID, version = satcraft.VERSION, name = satcraft.NAME)
public class satcraft
{
    public static final String MODID = "satcraft";
    public static final String VERSION = "1.0";
    public static final String NAME = "SAT craft";
    
    	public static Item pan;
    	public static Item registrationPapers;
        public static CreativeTabs tabSAT = new CreativeTabs("satItems"){
            @Override
            public ItemStack getTabIconItem(){
                return new ItemStack(satcraft.registrationPapers);
            }
        }
    @SidedProxy(clientSide="satcraft.client", serverSide="satcraft.server")
    public static sharedproxy proxy;
    
    @EventHandler
    public void pre(FMLPreInitializationEvent e){
    	registrationPapers = new ItemFood(0F, 0F, 0F);
        registrationPapers.setUnlocalizedName("registrationPapers");
        registrationPapers.setRegistryName("registrationPapers");
    	ForgeRegistries.ITEMS.register(registrationPapers);
    	EntityRegistry.registerModEntity(a.class, "spongebob", 0, MODID, 64, 5, true);
        
    }
    @EventHandler
    public void init(FMLInitializationEvent e)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(registrationPapers, 0, new ModelResourceLocation(MODID + ":" + registrationPapers.getUnlocalizedName().substring(5), "inventory"));
   
    }
}
