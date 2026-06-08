package net.ruterm.primitech.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;

public class ClubItem extends SwordItem {


    public ClubItem() {
        super(Tiers.WOOD, (new Item.Properties()).attributes(SwordItem.createAttributes(Tiers.WOOD, 5.5F, -3.6F)));
    }
}