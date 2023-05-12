package com.nhoryzon.mc.adventurerspaths.registry;

import com.nhoryzon.mc.adventurerspaths.AdventurersPathsMod;
import com.nhoryzon.mc.adventurerspaths.common.item.ModBlockItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.function.Supplier;

import static com.nhoryzon.mc.adventurerspaths.common.item.ModItemSettings.base;

public enum ItemsRegistry {

    /* Block Items */

    DUMMY_BLOCK("dummy_block", () -> new ModBlockItem(BlocksRegistry.DUMMY_BLOCK.get())),

    /* Items */

    DUMMY_ITEM("dummy_item", () -> new Item(base()));

    private final String pathName;
    private final Supplier<Item> itemSupplier;
    private final Integer burnTime;
    private Item item;

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
            Registry.register(Registries.ITEM, new Identifier(AdventurersPathsMod.MOD_ID, value.pathName), value.get());
            if (value.burnTime != null && value.burnTime > 0) {
                FuelRegistry.INSTANCE.add(value.get(), value.burnTime);
            }
        }
        ItemGroupEvents.modifyEntriesEvent(AdventurersPathsMod.ITEM_GROUP).register(entries ->
                entries.addAll(Arrays.stream(values()).map(item -> item.get().getDefaultStack()).toList()));
    }

    public Item get() {
        if (item == null) {
            item = itemSupplier.get();
        }
        return item;
    }

    public String getId() {
        return Registries.ITEM.getId(get()).toString();
    }

}