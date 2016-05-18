/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Mar 3, 2014, 7:10:32 PM (GMT)]
 */
package vazkii.botania.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;

public class EntitySignalFlare extends Entity {

	private static final String COLOR_TAG = "color";
	private static final DataParameter<Integer> COLOR = EntityDataManager.createKey(EntitySignalFlare.class, DataSerializers.VARINT);

	public EntitySignalFlare(World par1World) {
		super(par1World);
		setSize(0F, 0F);
	}

	@Override
	protected void entityInit() {
		dataManager.register(COLOR, 0);
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(ticksExisted++ >= 100)
			setDead();

		if(!isDead) {
			if(ticksExisted % 10 == 0)
				playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1F, 1F);

			int color = getColor();
			if(color < 16 && color >= 0) {
				int hex = EnumDyeColor.byMetadata(color).getMapColor().colorValue;
				int r = (hex & 0xFF0000) >> 16;
				int g = (hex & 0xFF00) >> 8;
				int b = (hex & 0xFF);

				Botania.proxy.setWispFXDistanceLimit(false);
				for(int i = 0; i < 3; i++)
					Botania.proxy.wispFX(worldObj, posX, posY, posZ + 0.5, r / 255F, g / 255F, b / 255F, (float) Math.random() * 5 + 1F, (float) (Math.random() - 0.5F), 10F * (float) Math.sqrt(256F / (256F - (float) posY)), (float) (Math.random() - 0.5F));

				for(int i = 0; i < 4; i++)
					Botania.proxy.wispFX(worldObj, posX + 0.5, 256, posZ + 0.5, r / 255F, g / 255F, b / 255F, (float) Math.random() * 15 + 8F, (float) (Math.random() - 0.5F) * 8F, 0F, (float) (Math.random() - 0.5F) * 8F);
				Botania.proxy.setWispFXDistanceLimit(true);
			}
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		setColor(nbttagcompound.getInteger(COLOR_TAG));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		nbttagcompound.setInteger(COLOR_TAG, getColor());
	}

	public void setColor(int color) {
		dataManager.set(COLOR, color);
	}

	public int getColor() {
		return dataManager.get(COLOR);
	}

}
