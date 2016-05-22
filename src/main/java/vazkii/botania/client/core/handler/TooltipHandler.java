/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jul 26, 2014, 2:33:04 AM (GMT)]
 */
package vazkii.botania.client.core.handler;

import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ItemKeepIvy;
import vazkii.botania.common.item.ItemRegenIvy;

public final class TooltipHandler {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onTooltipEvent(ItemTooltipEvent event) {
		if(event.getItemStack().getItem() == Item.getItemFromBlock(Blocks.DIRT) && event.getItemStack().getItemDamage() == 1) {
			event.getToolTip().add(I18n.format("botaniamisc.coarseDirt0"));
			event.getToolTip().add(I18n.format("botaniamisc.coarseDirt1"));
		} else if(event.getItemStack().getItem() == Item.getItemFromBlock(Blocks.MOB_SPAWNER) && event.getEntityPlayer().capabilities.isCreativeMode)
			event.getToolTip().add(I18n.format("botaniamisc.spawnerTip"));

		if(ItemNBTHelper.detectNBT(event.getItemStack()) && ItemNBTHelper.getBoolean(event.getItemStack(), ItemRegenIvy.TAG_REGEN, false))
			event.getToolTip().add(I18n.format("botaniamisc.hasIvy"));
		if(ItemNBTHelper.detectNBT(event.getItemStack()) && ItemNBTHelper.getBoolean(event.getItemStack(), ItemKeepIvy.TAG_KEEP, false))
			event.getToolTip().add(I18n.format("botaniamisc.hasKeepIvy"));
	}

}
