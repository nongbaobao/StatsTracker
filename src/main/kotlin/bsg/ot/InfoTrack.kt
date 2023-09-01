package bsg.ot

class InfoTrack {
    private var prevStats: MutableList<Int> = ArrayList()
    var stats: ArrayList<Int> = ArrayList()
    var changed: String? = null

    init {
        games.add("Blitz SG Solo")
        games.add("Blitz SG Team")
        games.add("Skywars Solo Normal")
        games.add("Skywars Solo Insane")
        games.add("Skywars Team Normal")
        games.add("Skywars Team Imsane")
    }

    private fun compare(slot: Int): Boolean {
        val oldData = prevStats.indexOf(slot)
        val newData = stats.indexOf(slot)
        if (oldData == 0) {
            update(newData, slot)
            return false
        }
        if (oldData == newData) return false
        update(newData, slot)
        return true
    }

    fun check() {
        var online: Boolean
        for (slot in 1..6) {
            online = compare(slot)
            if (online) {
                //数据变动后的游戏名称
                changed = games[slot]
            }
        }
    }

    private fun update(data: Int, slot: Int) {
        prevStats[slot] = data
    }

    companion object {
        var  games: MutableList<String> = ArrayList()
    }
}
