package top.mykodb.codematrix.config

import net.minecraft.client.gui.screens.Screen
import net.neoforged.fml.ModContainer
import net.neoforged.fml.config.ModConfig
import net.neoforged.fml.event.config.ModConfigEvent
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.neoforge.client.gui.ConfigurationScreen
import net.neoforged.neoforge.client.gui.IConfigScreenFactory
import net.neoforged.neoforge.common.ModConfigSpec
import org.apache.commons.lang3.tuple.Pair
import thedarkcolour.kotlinforforge.neoforge.forge.LOADING_CONTEXT

object ModConfiguration {
    
    val examplePair: Pair<ExampleConfig, ModConfigSpec> = ModConfigSpec.Builder().configure(::ExampleConfig)
    val exampleList: ExampleConfig = examplePair.getLeft()

    
    fun onLoad(event: ModConfigEvent.Loading) {


    }

    fun onFileChange(event: ModConfigEvent.Reloading) {

    }

    // 客户端配置gui
    fun onclientSetup(event: FMLClientSetupEvent) {
        event.enqueueWork {
            val modContainer = LOADING_CONTEXT.activeContainer
            modContainer.registerExtensionPoint(
                IConfigScreenFactory::class.java,
                IConfigScreenFactory { mod: ModContainer, parent: Screen -> ConfigurationScreen(mod, parent) }
            )
        }
    }
    fun register(modContainer: ModContainer){
        modContainer.registerConfig(ModConfig.Type.COMMON, examplePair.getRight(),"example.toml")
    }
}