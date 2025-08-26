// build.gradle.kts
// 委托 解析"gradle.properties"配置
// 版本控制
val minecraftVersion: String by project
val mappingsVersion: String by project
val neoforgeVersion: String by project
val kotlinforforgeVersion: String by project
val jeiVersion: String by project
// 版本范围
val minecraftVersionRange: String by project
val neoforgeVersionRange: String by project
val loaderVersionRange: String by project
// 模组相关
val modId: String by project
val modVersion: String by project
val modGroupId: String by project

// 插件
plugins {
    id("java-library")
    id("maven-publish")
    id("idea")
    id ("net.neoforged.moddev") version "2.0.107"
    id("org.jetbrains.kotlin.jvm") version "2.1.21"

}
// 配置
configurations {
    create("localRuntime") {
        description = "依赖项仅用于本地运行时测试"
        isCanBeConsumed = false  // 不会被当前项目依赖
        isCanBeResolved = true   // 可以被当前项目解析
    }
    runtimeClasspath.get().extendsFrom(configurations["localRuntime"])
}

// 存储库
repositories {
    maven("https://thedarkcolour.github.io/KotlinForForge/") // kotlin for forge
    maven("https://maven.blamejared.com") // JEI
    // 如果下面的某些依赖项需要，请在此处添加其他存储库。
}

// 依赖
dependencies {
    implementation("net.neoforged:neoforge:${neoforgeVersion}")
    implementation("thedarkcolour:kotlinforforge-neoforge:${kotlinforforgeVersion}")

    // JEI 的可选 mod 依赖项示例
    // JEI API 被声明用于编译时，而完整的 JEI 工件在运行时使用
    compileOnly("mezz.jei:jei-${minecraftVersion}-common-api:${jeiVersion}")
    compileOnly("mezz.jei:jei-${minecraftVersion}-neoforge-api:${jeiVersion}")
    //我们将完整版本添加到 localRuntime，而不是 runtimeOnly，这样我们就不会发布对它的依赖
    "localRuntime"("mezz.jei:jei-${minecraftVersion}-neoforge:${jeiVersion}")
}

tasks.named<Wrapper>("wrapper") {
    distributionType = Wrapper.DistributionType.BIN
}

version = modVersion
group = modGroupId

base {
    archivesName = modId
}

java {
    // Java21 环境
    toolchain.languageVersion = JavaLanguageVersion.of(21)
    // 生成源码包
    withSourcesJar()
    // 生成文档包
    withJavadocJar()
}



neoForge {
    // 指定要使用的 NeoForge 版本。
    version = neoforgeVersion

    // 反编译混淆映射
    parchment.minecraftVersion = minecraftVersion
    parchment.mappingsVersion = mappingsVersion


    // 接入变压器
    accessTransformers {
        file("src/main/resources/META-INF/accesstransformer.cfg")
    }

    // 运行配置
    runs {
        create("client") {
            client()
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }
        create("server") {
            server()
            programArgument("--nogui")
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }
        create("gameTestServer") {
            type = "gameTestServer"
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }
        create("data") {
            data()
            programArguments.addAll("--mod", modId, "--all", "--output", file("src/generated/resources/").absolutePath, "--existing", file("src/main/resources/").absolutePath)
        }
        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
            logLevel = org.slf4j.event.Level.DEBUG
        }
    }
    mods {
        create(modId) {
            sourceSet(sourceSets.main.get())
        }
    }
}

// 包括数据生成器生成的资源。
sourceSets.main {
    resources.srcDir("src/generated/resources")
}

// 此代码块扩展指定资源目标中所有声明的替换属性。
// 缺少属性将导致错误。使用 ${} Groovy 表示法扩展属性。
val generateModMetadata = tasks.register("generateModMetadata", ProcessResources::class) {
    val replaceProperties = mapOf(
        "loader_version_range" to loaderVersionRange,
        "mod_id" to modId,
        "mod_version" to modVersion,
        "neoforge_version_range" to neoforgeVersionRange,
        "minecraft_version_range" to minecraftVersionRange,
    )
    inputs.properties(replaceProperties)
    expand(replaceProperties)
    from("src/main/templates")
    into("build/generated/sources/modMetadata")
}

// 将“generateModMetadata”的输出作为构建的输入目录
// 这适用于通过 Gradle 和 IDE 进行构建。
sourceSets.main.get().resources.srcDir(generateModMetadata)
// 为避免手动运行“generateModMetadata”，请在每次项目重新加载时运行它
neoForge.ideSyncTask(generateModMetadata)

// 允许使用 maven-publish 插件发布的示例配置
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("file://${project.projectDir}/repo")
        }
    }
}

// 使用 UTF-8 字符集进行 Java 编译
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}