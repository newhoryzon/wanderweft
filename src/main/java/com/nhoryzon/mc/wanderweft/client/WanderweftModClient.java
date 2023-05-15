package com.nhoryzon.mc.wanderweft.client;

import com.nhoryzon.mc.wanderweft.registry.BlocksRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(value= EnvType.CLIENT)
public class WanderweftModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		BlocksRegistry.registerRenderLayer();
	}

}