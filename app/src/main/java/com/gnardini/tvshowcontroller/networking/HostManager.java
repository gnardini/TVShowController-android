package com.gnardini.tvshowcontroller.networking;

import com.gnardini.tvshowcontroller.utils.StorageUtils;

public class HostManager {

    private static final String SERVER_IP = "192.168.0.18";
    private static final String HOST_FORMAT = "http://%s:5000";

    private static final String SERVER_IP_KEY = "SERVER_IP_KEY";

    public String getHost() {
        return String.format(HOST_FORMAT, getServerIp());
    }

    public String getServerIp() {
        return StorageUtils.getStringFromSharedPreferences(SERVER_IP_KEY, SERVER_IP);
    }

    public void saveServerIp(String serverIp) {
        StorageUtils.storeInSharedPreferences(SERVER_IP_KEY, serverIp);
    }
}
