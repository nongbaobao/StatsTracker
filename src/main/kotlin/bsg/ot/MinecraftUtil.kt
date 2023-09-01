package bsg.ot

import cn.hutool.json.JSONUtil
import okhttp3.OkHttpClient
import okhttp3.Request

object MinecraftUtil {

    fun hasUser(user: String): Boolean {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.mojang.com/users/profiles/minecraft/$user")
            .build()
        val response = client.newCall(request).execute()
        val json = JSONUtil.parseObj(response.body!!.string())
        response.close()
        return json.getStr("name") != null
    }

    fun getNameFromUUID(uuid: String): String? {
        val json = JSONUtil.parseObj(getJson("https://api.hypixel.net/player?key=${Config.apiKey}&uuid=$uuid"))
        if (json.getBool("success")) {
            return json.getJSONObject("player").getStr("displayname")
        }

        return null
    }

    fun getUUID(user: String): String {
        if (hasUser(user)) {
            val json = JSONUtil.parseObj(getJson("https://api.mojang.com/users/profiles/minecraft/$user"))
            return json.getStr("id")
        }

        return ""
    }

    fun getJson(url: String): String? {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val json = response.body?.string()
            response.close()
            return json
        }

        return null
    }

}