package com.gnardini.tvshowcontroller.networking

import com.gnardini.tvshowcontroller.utils.StorageUtils

class HostManager {

    fun getHost() = String.format(HOST_FORMAT, getServerIp())

    fun getServerIp() = StorageUtils.getStringFromSharedPreferences(SERVER_IP_KEY, SERVER_IP)

    fun saveServerIp(serverIp: String) =
        StorageUtils.storeInSharedPreferences(SERVER_IP_KEY, serverIp)

    companion object {

        private val SERVER_IP = "192.168.0.18"
        private val HOST_FORMAT = "http://%s:5000"

        private val SERVER_IP_KEY = "SERVER_IP_KEY"
    }
}
