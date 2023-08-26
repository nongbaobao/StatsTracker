package bsg.ot

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value

object Config: AutoSavePluginData("config") {
    val apiKey: String by value()
    val trackers: MutableList<String> by value()
}