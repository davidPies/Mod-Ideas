package satcraft;

@Mod(modid = satcraft.MODID, version = satcraft.VERSION, name = satcraft.NAME)
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
    	proxy.registerRenderers();
    	//trumphair = new hair("trumphair", hairt, 0, EntityEquipmentSlot.HEAD);
    	//GameRegistry.registerItem(trumphair, "trumphair");
    	EntityRegistry.registerModEntity(spongebob.class, "spongebob", 0, MODID, 64, 5, true);
    	EntityRegistry.registerEgg(spongebob.class, 0, 1000);
    }
    @EventHandler
    public void init(FMLInitializationEvent e)
    {
    
    }
}
