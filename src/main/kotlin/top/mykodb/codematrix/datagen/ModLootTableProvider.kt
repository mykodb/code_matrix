package top.mykodb.codematrix.datagen

import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider

import top.mykodb.codematrix.Mod.MODID

object ModLootTableProvider {
    class LangEnUs(output: PackOutput): LanguageProvider(output, MODID, "en_us") {
        override fun addTranslations() {

        }
    }
    class LangZhCn(output: PackOutput): LanguageProvider(output, MODID, "ch_cn") {
        override fun addTranslations() {

        }
    }
}