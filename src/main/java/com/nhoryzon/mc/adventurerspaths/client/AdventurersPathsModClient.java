package com.nhoryzon.mc.adventurerspaths.client;

import com.nhoryzon.mc.adventurerspaths.registry.BlocksRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(value= EnvType.CLIENT)
public class AdventurersPathsModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		BlocksRegistry.registerRenderLayer();
	}

}