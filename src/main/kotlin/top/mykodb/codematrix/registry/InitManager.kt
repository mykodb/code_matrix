package top.mykodb.codematrix.registry


/**
 * 用于管理注册初始化
 */
object InitManager {
    private val modules: MutableList<() -> Unit> = mutableListOf()
    private val moduleClasses: List<Any> by lazy {
        listOf(
            ModDataComponent,
            ModBlocks,
            ModItems,
            ModItemGroup,
        )
    }


    fun registerModule(moduleInit: () -> Unit) {
        modules.add(moduleInit)
    }

    fun initializeAllModules() {
        modules.forEach { it() }
    }

    fun loadAllModules() {
        moduleClasses.forEach { _ -> /* 访问它，触发init块 */ }
    }
}