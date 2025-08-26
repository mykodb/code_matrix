package top.mykodb.codematrix.events

import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent

object CreativeTabEvents {
    @SubscribeEvent
    fun onBuildCreativeTabContents(event: BuildCreativeModeTabContentsEvent) {
        /**
         *  if (event.tabKey === CreativeModeTabs.BUILDING_BLOCKS) {
         *      event.accept(Items.ACACIA_BOAT)
         *  }
         */
    }
}