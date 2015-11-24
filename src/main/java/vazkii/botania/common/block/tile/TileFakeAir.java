/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Apr 10, 2015, 10:24:48 PM (GMT)]
 */
package vazkii.botania.common.block.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import vazkii.botania.common.block.subtile.functional.SubTileBubbell;

public class TileFakeAir extends TileMod {

	private static final String TAG_FLOWER_X = "flowerX";
	private static final String TAG_FLOWER_Y = "flowerY";
	private static final String TAG_FLOWER_Z = "flowerZ";

	BlockPos flowerPos;

	public void setFlower(TileEntity tile) {
		flowerPos = tile.getPos();
	}

	public boolean canStay() {
		return SubTileBubbell.isValidBubbell(worldObj, flowerPos);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setInteger(TAG_FLOWER_X, flowerPos.getX());
		par1nbtTagCompound.setInteger(TAG_FLOWER_Y, flowerPos.getY());
		par1nbtTagCompound.setInteger(TAG_FLOWER_Z, flowerPos.getZ());
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		flowerPos = new BlockPos(
			par1nbtTagCompound.getInteger(TAG_FLOWER_X),
			par1nbtTagCompound.getInteger(TAG_FLOWER_Y),
			par1nbtTagCompound.getInteger(TAG_FLOWER_Z)
		);
	}

}
