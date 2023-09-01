package bsg.ot

import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.console.command.UserCommandSender
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.buildMessageChain
import java.lang.Exception

object TrackerCommand:  CompositeCommand(
    StatsTracker,
    "tracker"
){

    @SubCommand("addTracker")
    suspend fun UserCommandSender.addTracker(id: String) {
        if (MinecraftUtil.hasUser(id)) {
            val uuid = MinecraftUtil.getUUID(id)
            Config.trackers.add(uuid)
            subject.sendMessage("添加成功！")
        } else {
            subject.sendMessage("找不到该id！")
        }
    }

    @SubCommand("removeTracker")
    suspend fun UserCommandSender.removeTracker(id: String) {
        if (MinecraftUtil.hasUser(id)) {
            val uuid = MinecraftUtil.getUUID(id)
            try {
                if (Config.trackers.remove(uuid)) {
                    subject.sendMessage("移除成功！")
                }
            }catch (_: Exception) {
                subject.sendMessage("未添加过该玩家！")
            }
        } else {
            subject.sendMessage("找不到该id！")
        }
    }

    @SubCommand("toggle")
    suspend fun UserCommandSender.toggle() {
        if (Config.trackers.isEmpty()) {
            subject.sendMessage("开启失败, 追踪列表为空!")
        }

        StatsTracker.enabled = !StatsTracker.enabled
    }

    @SubCommand("list")
    suspend fun UserCommandSender.list() {
        val msg: MessageChain = buildMessageChain {
            +"=====Stats Tracker====="
            +"目前追踪的玩家有: \n"
            for (name in Config.trackers) {
                +"$name \n"
            }
        }

        subject.sendMessage(msg)
    }


}