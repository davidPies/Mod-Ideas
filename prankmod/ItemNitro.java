package springboardmod;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemNitro extends Item implements ISBRegistry{ // I made ISBRegistry to register blocks and items (items/blocks are extended from the game)
	public ItemNitro() {
		//constructor
	}
	@Override
	public Item getItem() { // implements from ISBRegistry
		return this; //returns this class
	}
	public void onUpdate(ItemStack stack, World worldIn, Entity player, int itemSlot, boolean isSelected) //extended from the game
  	{
		this.explode(worldIn, player); // my function
   	}
	@Override
	public Block getSBBlock() { // implements from ISBRegistry
		return null; // isn't a block -- so it returns nothing and is checked in the main class whether it is null
	}
	@Override
	public String getName() { // implements from ISBRegistry
		return "name1"; // changed name to remove context (even though the name is a bad practice)
	}
	public String getItemStackDisplayName(ItemStack stack) //extended from the game
    	{
        	return "Name 1"; // display name
    	}
	public void explode(World world, Entity entityIn) { // my function
		BlockPos ppos = entityIn.getPosition(); // gets the player position
		for(int i = 1; i < 4; i++) { // explodes when the item in the furnace is within a 3 block radius of the player
			this.doExplode(world, (TileEntityFurnace) world.getTileEntity(ppos.up(i)));
			this.doExplode(world, (TileEntityFurnace) world.getTileEntity(ppos.down(i)));
			this.doExplode(world, (TileEntityFurnace) world.getTileEntity(ppos.north(i)));
			this.doExplode(world, (TileEntityFurnace) world.getTileEntity(ppos.south(i)));
			this.doExplode(world, (TileEntityFurnace) world.getTileEntity(ppos.east(i)));
			this.doExplode(world, (TileEntityFurnace) world.getTileEntity(ppos.west(i)));
		}
	}
	public void doExplode(World world, TileEntityFurnace tile) { // my function
		if(tile != null && tile instanceof TileEntityFurnace) { // checks if the target exists
			if(tile.getStackInSlot(0).getItem() == this || tile.getStackInSlot(1).getItem() == this) {// checks if the item is in the furnace
				if(!world.isRemote) {
					// creates an explosion at the position of the furnace
					world.createExplosion(null, tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), (float)5.25, true);
				}
			}
		}
	}
}
