package com.nhoryzon.mc.wanderweft;

import com.nhoryzon.mc.wanderweft.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WanderweftMod implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("Adventurers' Paths");
    public static final String MOD_ID = "wanderweft";
    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier(MOD_ID, "main"))
            .displayName(i18n("itemGroup.main"))
            .icon(() -> new ItemStack(Items.EXPERIENCE_BOTTLE)).build();

    public static Configuration CONFIG = new Configuration();

    public static MutableText i18n(String key, Object... args) {
        return Text.translatable(MOD_ID + "." + key, args);
    }

    @Override
    public void onInitialize() {
        initConfiguration();

        BlocksRegistry.registerAll();
        ItemsRegistry.registerAll();
        BlockEntityTypesRegistry.registerAll();
        EntityAttributesRegistry.registerAll();
    }

    protected void initConfiguration() {
        CONFIG = Configuration.load();
    }

}