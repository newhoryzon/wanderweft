package com.nhoryzon.mc.adventurerspaths.integration.modmenu;

import com.nhoryzon.mc.adventurerspaths.AdventurersPathsMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.List;

@Environment(value= EnvType.CLIENT)
public enum Category {

    DUMMY_BASE_CATEGORY("Dummy Base Category", false,
            Entry.bool("Dummy Bool Setting", () -> AdventurersPathsMod.CONFIG.isDummyBoolSetting(),
                    newValue -> AdventurersPathsMod.CONFIG.setDummyBoolSetting(newValue), true,
                    "A dummy bool setting that is defaulted to \"true\".",
                    "This is a literal description and it",
					"can be displayed on multiple",
					"lines.")),
					
    DUMMY_FIRST_SUBCATEGORY("Dummy First Subcategory", true,
            Entry.inte("Dummy Integer Setting", () -> AdventurersPathsMod.CONFIG.getDummyIntegerSetting(),
                    newValue -> AdventurersPathsMod.CONFIG.setDummyIntegerSetting(newValue), 100, 0, Integer.MAX_VALUE,
                    "A dummy integer setting that has default value to 100 and can be",
					"set with a value between 0 and " + Integer.MAX_VALUE + "."),
            Entry.bool("Dummy Bool Setting", () -> AdventurersPathsMod.CONFIG.isDummyBoolSetting(),
                    newValue -> AdventurersPathsMod.CONFIG.setDummyBoolSetting(newValue), true,
                    "The dummy bool setting can be set here too...",
                    "on multiple lines.")),
    DUMMY_SECOND_SUBCATEGORY("Dummy Second Subcategory", true,
            Entry.list("Dummy List Setting", () -> AdventurersPathsMod.CONFIG.getDummyListSetting(),
                    newValue -> AdventurersPathsMod.CONFIG.setDummyListSetting(newValue), List.of("default", "list", "values"),
                    "A dummy list that contains by default : ",
					"[\"default\", \"list\", \"values\"].")),

    DUMMY_PARENT_CATEGORY("Dummy Parent Category", false, new Category[]{ DUMMY_FIRST_SUBCATEGORY, DUMMY_SECOND_SUBCATEGORY },
            Entry.doubl("Dummy Double Setting", () -> AdventurersPathsMod.CONFIG.getDummyDoubleSetting(),
                    newValue -> AdventurersPathsMod.CONFIG.setDummyDoubleSetting(newValue), 0.1, 0.0, 1.0,
                    "A dummy double setting that... so, ",
                    "now you understand how Category java enum works !"));


    private final String text;
    private final Entry<?>[] entries;
    private final Category[] children;
    private final boolean isChild;

    Category(String text, boolean isChild, Entry<?>... entries) {
        this.text = text;
        this.entries = entries;
        this.children = new Category[0];
        this.isChild = isChild;
    }

    Category(String text, boolean isChild, Category[] children, Entry<?>... entries) {
        this.text = text;
        this.entries = entries;
        this.children = children;
        this.isChild = isChild;
    }

    public String text() {
        return text;
    }

    public Entry<?>[] entries() {
        return entries;
    }

    public Category[] children() {
        return children;
    }

    public boolean isChild() {
        return isChild;
    }

}
