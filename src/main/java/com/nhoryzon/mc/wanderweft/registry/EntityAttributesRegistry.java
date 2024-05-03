package com.nhoryzon.mc.wanderweft.registry;

import com.nhoryzon.mc.wanderweft.WanderweftMod;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.function.Function;

public enum EntityAttributesRegistry implements IBaseRegistry<EntityAttribute> {

    ATTACK_RANGE("generic.attack_range", clamped(0.1, 0.1, 0.5)),
    CHAINING_ATTACK("generic.chaining_attack", clamped(1, 1, 6)),
    ILLAGER_DAMAGE("generic.illager_damage_bonus", clamped(0.1, 0.1, 1)),
    ARTHROPODS_DAMAGE("generic.arthropods_damage_bonus", clamped(0.1, 0.1, 1)),
    UNDEAD_DAMAGE("generic.undead_damage_bonus", clamped(0.1, 0.1, 1)),
    NETHER_DAMAGE("generic.nether_damage_bonus", clamped(0.1, 0.1, 1)),
    STUN_ATTACH_CHANCE("generic.stun_attack_chance", clamped(0.04, 0.04, 0.24)),
    SWEEPING_ATTACH_CHANCE("generic.sweeping_attack_chance", clamped(0.25, 0.25, 0.75)),
    LUCKY_ATTACK_CHANCE("generic.lucky_attack_chance", clamped(0.05, 0.05, 0.1)),
    SOUL_CHANCE("generic.soul_chance", clamped(0.10, 0.10, 0.5)),
    CRITICAL_HIT_CHANCE("generic.crit_hit_chance", clamped(0.05, 0.05, 0.4)),
    HEALING_EFFICIENCY("generic.healing_efficiency", clamped(0.05, 0.05, 0.4)),
    BLOCK_CHANCE("generic.block_chance", clamped(0.04, 0.04, 0.4)),
    COOLDOWN_REDUCTION("generic.cooldown_reduction", clamped(0.02, 0.02, 0.2)),
    ITEM_RARITY("generic.item_rarity", clamped(0.05, 0.05, 0.3)),
    EFFECT_AREA_SIZE("generic.effect_area_size", clamped(0.02, 0.02, 0.2));

    private final String pathName;
    private final Function<String, EntityAttribute> functionSupplier;
    private EntityAttribute entityAttribute;

    private static Function<String, EntityAttribute> clamped(double startValue, double min, double max) {
        return (translationKey) -> new ClampedEntityAttribute(translationKey, startValue, min, max).setTracked(true);
    }

    EntityAttributesRegistry(String pathName, Function<String, EntityAttribute> functionSupplier) {
        this.pathName = pathName;
        this.functionSupplier = functionSupplier;
    }

    public static void registerAll() {
        for (EntityAttributesRegistry value : values()) {
            Registry.register(Registries.ATTRIBUTE, value.makeIdentifier(), value.get());
        }
    }

    private Identifier makeIdentifier() {
        return new Identifier(WanderweftMod.MOD_ID, pathName);
    }

    @Override
    public EntityAttribute get() {
        if (entityAttribute == null) {
            entityAttribute = functionSupplier.apply(String.format("attribute.name.%s", pathName));
        }
        return entityAttribute;
    }

    @Override
    public String getId() {
        return Optional.ofNullable(Registries.ATTRIBUTE.getId(get())).orElse(makeIdentifier()).toString();
    }

    @Override
    public int getRawId() {
        return Registries.ATTRIBUTE.getRawId(get());
    }

}
