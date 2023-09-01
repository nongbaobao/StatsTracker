package bsg.ot

import kotlinx.coroutines.coroutineScope
import net.mamoe.mirai.Bot
import net.mamoe.mirai.auth.BotAuthSession
import net.mamoe.mirai.contact.Group.Companion.setEssenceMessage
import net.mamoe.mirai.message.data.buildMessageChain
import java.util.*

object MessageMain {

    suspend fun tracker() {
        while (true) {
            Thread {
                if (StatsTracker.enabled) {
                    if (!Config.trackers.isEmpty()) {
                        for (uuid in Config.trackers) {
                            val playingGame = StatTracking.getInfo(UUID.fromString(uuid))
                            if (playingGame != null) {
                                val msg = buildMessageChain {
                                    +"=====Stats Tracker=====\n"
                                    +"检测到${MinecraftUtil.getNameFromUUID(uuid)}数据变动！\n"
                                    +"正在游玩: $playingGame"
                                }


                            }
                        }
                    }
                }
            }.start()
        }
    }

}