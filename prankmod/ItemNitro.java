package springboardmod;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemNitro extends Item implements ISBRegistry{
	public ItemNitro() {
	}
	@Override
	public Item getItem() {
		return this;
	}
	public void onUpdate(ItemStack stack, World worldIn, Entity player, int itemSlot, boolean isSelected)
    {
		this.explode(worldIn, player);
    }
	@Override
	public Block getSBBlock() {
		return null;
	}
	@Override
	public String getName() {
		return "nitro";
	}
	public String getItemStackDisplayName(ItemStack stack)
    {
        return "Nitrofluid";
    }
	public void explode(World world, Entity entityIn) {
		BlockPos ppos = entityIn.getPosition();
		//ppos.getAllInBox(ppos.add(-3, -3, -3), ppos.add(3, 3, 3)).iterator().next();
		for(int i = 1; i < 4; i++) {
			this.doExplode(world, (TileEntityFurnace) world.getTileEntity(ppos.up(i)));
			this.doExplode(world, (TileEntityFurnace) world.getTileEntity(ppos.down(i)));
			this.doExplode(world, (TileEntityFurnace) world.getTileEntity(ppos.north(i)));
			this.doExplode(world, (TileEntityFurnace) world.getTileEntity(ppos.south(i)));
			this.doExplode(world, (TileEntityFurnace) world.getTileEntity(ppos.east(i)));
			this.doExplode(world, (TileEntityFurnace) world.getTileEntity(ppos.west(i)));
		}
	}
	public void doExplode(World world, TileEntityFurnace tile) {
		if(tile != null && tile instanceof TileEntityFurnace) {
			if(tile.getStackInSlot(0).getItem() == this || tile.getStackInSlot(1).getItem() == this) {
				if(!world.isRemote) {
					world.createExplosion(null, tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), (float)5.25, true);
				}
			}
		}
	}
}
