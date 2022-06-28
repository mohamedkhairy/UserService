package dependencies

object Kotlinx {
//    private const val kotlinxDatetimeVersion = "0.1.1"
//    const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:$kotlinxDatetimeVersion"
    private const val coroutinesCoreVersion = "1.6.2"
    val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesCoreVersion" }

}