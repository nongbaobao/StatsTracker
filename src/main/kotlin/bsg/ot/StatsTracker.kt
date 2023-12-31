package bsg.ot

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import net.mamoe.mirai.Bot
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.permission.AbstractPermitteeId
import net.mamoe.mirai.console.permission.PermissionService.Companion.permit
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.buildMessageChain
import net.mamoe.mirai.utils.info
import java.util.*

object StatsTracker : KotlinPlugin(
    JvmPluginDescription(
        id = "cn.bsgot.Application",
        name = "StatsTracker",
        version = "0.1.0",
    ) {
        author("herry, Falling_Spirite")
    }
) {

    var enabled = false

    override fun onEnable() {
        logger.info { "Plugin loaded" }

        // command
        TrackerCommand.register()

        // permit
        AbstractPermitteeId.AnyMember(910668166).permit(TrackerCommand.permission)

        suspend {
            MessageMain.tracker()
        }
    }
}