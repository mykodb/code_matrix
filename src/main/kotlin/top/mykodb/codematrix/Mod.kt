package top.mykodb.codematrix

import net.neoforged.fml.common.Mod
import thedarkcolour.kotlinforforge.neoforge.forge.LOADING_CONTEXT
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import top.mykodb.codematrix.Mod.MODID
import top.mykodb.codematrix.config.ModConfiguration
import top.mykodb.codematrix.events.CreativeTabEvents
import top.mykodb.codematrix.events.GatherData
import top.mykodb.codematrix.registry.InitManager
import top.mykodb.codematrix.registry.Registration

@Mod(value = MODID)
object Mod {
    const val MODID = "code_matrix"
    init {
        val modContainer = LOADING_CONTEXT.activeContainer
        val modBus = MOD_BUS
        // === 基础设施注册 ===
        Registration.registerAll(modBus)

        // === 内容初始化 ===
        InitManager.loadAllModules()
        InitManager.initializeAllModules()

        // === 事件监听器注册 ===
        modBus.apply {
            // 注册创造性标签页内容事件
            addListener(CreativeTabEvents::onBuildCreativeTabContents)
            // 注册数据生成事件
            addListener(GatherData::gatherData)
            // 注册配置相关的事件
            addListener(ModConfiguration::onLoad)
            addListener(ModConfiguration::onFileChange)
            addListener(ModConfiguration::onclientSetup)
        }

        // === 阶段 4: 配置注册 ===
        ModConfiguration.register(modContainer)
    }


}