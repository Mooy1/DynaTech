package me.profelements.dynatech.items.electric.generators;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.profelements.dynatech.DynaTech;

public class DragonEggGenerator extends SlimefunItem implements EnergyNetProvider {

    public DragonEggGenerator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public int getGeneratedOutput(@Nonnull Location location, @Nonnull Config config) {
        if (DynaTech.inst().getGlobalTick()  % 10 == 0) {
            
            Block dragonEgg = location.getBlock().getRelative(BlockFace.UP);
            if (dragonEgg.getType() == Material.DRAGON_EGG) {
                BlockStorage.addBlockInfo(location, "egg", String.valueOf(true));
                return getEnergyProduction();
            } else {
                BlockStorage.addBlockInfo(location, "egg", String.valueOf(false));
            }

         }else if (BlockStorage.getLocationInfo(location, "egg").equals(String.valueOf(true))){
            return getEnergyProduction();
        }
        return 0; 
    }

    @Override
    public boolean isChargeable() {
        return false;
    }

    public static int getEnergyProduction() {
        return 32;
    }

    @Override
    public int getCapacity() {
        return 512;
    }
}
