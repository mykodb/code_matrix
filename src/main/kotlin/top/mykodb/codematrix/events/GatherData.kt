package top.mykodb.codematrix.events

import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.data.event.GatherDataEvent
import top.mykodb.codematrix.datagen.ModLootTableProvider

object GatherData {
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent){
        val generator = event.generator
        val output = event.generator.packOutput
        generator.addProvider(event.includeClient(), ModLootTableProvider.LangEnUs(output))
        generator.addProvider(event.includeClient(), ModLootTableProvider.LangZhCn(output))
    }
}