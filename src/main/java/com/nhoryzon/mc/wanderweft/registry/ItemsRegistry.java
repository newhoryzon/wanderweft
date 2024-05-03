package com.nhoryzon.mc.wanderweft.registry;

import com.nhoryzon.mc.wanderweft.WanderweftMod;
import com.nhoryzon.mc.wanderweft.common.item.ModBlockItem;
import com.nhoryzon.mc.wanderweft.common.item.ModItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.function.Supplier;

public enum ItemsRegistry implements IBaseRegistry<Item> {

    /* Block Items */

    DUMMY_BLOCK("dummy_block", () -> new ModBlockItem(BlocksRegistry.DUMMY_BLOCK.get())),

    /* Items */

    DUMMY_ITEM("dummy_item");

    private final String pathName;
    private final Supplier<Item> itemSupplier;
    private final Integer burnTime;
    private Item item;

    ItemsRegistry(String pathName) {
        this(pathName, () -> new Item(ModItemSettings.base()));
    }

    ItemsRegistry(String pathName, Supplier<Item> itemSupplier) {
        this(pathName, itemSupplier, null);
    }

    ItemsRegistry(String pathName, Supplier<Item> itemSupplier, Integer burnTime) {
        this.pathName = pathName;
        this.itemSupplier = itemSupplier;
        this.burnTime = burnTime;
    }

    public static void registerAll() {
        for (ItemsRegistry value : values()) {
            Registry.register(Registries.ITEM, new Identifier(WanderweftMod.MOD_ID, value.pathName), value.get());
            if (value.burnTime != null && value.burnTime > 0) {
                FuelRegistry.INSTANCE.add(value.get(), value.burnTime);
            }
        }
        ItemGroupEvents.modifyEntriesEvent(WanderweftMod.ITEM_GROUP).register(entries ->
                entries.addAll(Arrays.stream(values()).map(item -> item.get().getDefaultStack()).toList()));
    }

    @Override
    public Item get() {
        if (item == null) {
            item = itemSupplier.get();
        }
        return item;
    }

    @Override
    public String getId() {
        return Registries.ITEM.getId(get()).toString();
    }

    @Override
    public int getRawId() {
        return Registries.ITEM.getRawId(get());
    }

}