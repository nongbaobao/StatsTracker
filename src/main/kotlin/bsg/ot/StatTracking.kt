package bsg.ot

import bsg.ot.Config.apiKey
import net.hypixel.api.HypixelAPI
import net.hypixel.api.apache.ApacheHttpClient
import net.hypixel.api.reply.PlayerReply
import java.util.*
import java.util.concurrent.ExecutionException

class StatTracking {
    companion object {
        val INSTANCE = StatTracking
        var API: HypixelAPI? = null
        init {
            API = HypixelAPI(ApacheHttpClient(UUID.fromString(apiKey)))
        }

        // game list: BSG, Duel, MW, SW, WoolWars
        // 每次查询时call
        fun getInfo(PlayerUUID: UUID): String? {
            val player = getPlayer(PlayerUUID)
            val instance = InfoTrack()
            instance.stats.add(1, player!!.getIntProperty("stats.HungerGames.kills_solo_normal", 0))
            instance.stats.add(2, player.getIntProperty("stats.HungerGames.kills_teams_normal", 0))
            StatsTracker.logger.info("BSG > " + player.getIntProperty("stats.HungerGames.kills_teams_normal", 0))
            StatsTracker.logger.info("BSG > " + player.getIntProperty("stats.HungerGames.kills_solo_normal", 0))

            //duel

            //mw

            //sw
            instance.stats.add(3, player.getIntProperty("stats.SkyWars.kills_solo_normal", 0))
            instance.stats.add(4, player.getIntProperty("stats.SkyWars.kills_solo_insane", 0))
            instance.stats.add(5, player.getIntProperty("stats.SkyWars.kills_team_normal", 0))
            instance.stats.add(6, player.getIntProperty("stats.SkyWars.kills_team_insane", 0))
            instance.check()
            StatsTracker.logger.info("SkyWars Deaths > " + player.getIntProperty("stats.SkyWars.deaths", 0))
            StatsTracker.logger.info("SkyWars Deaths > " + player.getIntProperty("stats.SkyWars.deaths", 0))
            return instance.changed
        }

        private fun getPlayer(uuid: UUID): PlayerReply.Player? {
            val apiReply: PlayerReply
            return try {
                apiReply = API!!.getPlayerByUuid(uuid).get()
                apiReply.player
            } catch (e: ExecutionException) {
                System.err.println("API request failed!")
                e.cause!!.printStackTrace()
                null
            } catch (e: InterruptedException) {
                e.printStackTrace()
                Thread.currentThread().interrupt()
                null
            } finally {
                API!!.shutdown()
            }
        }
    }
}
