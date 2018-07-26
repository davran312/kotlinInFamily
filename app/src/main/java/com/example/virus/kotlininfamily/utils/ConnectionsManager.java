package com.example.virus.kotlininfamily.utils;

import com.example.virus.kotlininfamily.StartApplication;

public class ConnectionsManager {
    private static volatile ConnectionsManager Instance = null;

    public static ConnectionsManager getInstance(){
        ConnectionsManager localInstance = Instance;
        if(localInstance == null){
            synchronized (ConnectionsManager.class){
                localInstance = Instance;
                if(localInstance == null){
                    Instance = localInstance = new ConnectionsManager();

                }
            }
        }
        return localInstance;
    }
    public ConnectionsManager(){
        String deviceModel;
        String appVersion;
        String systemVersion;
        String configPath = StartApplication.INSTANCE.getFilesDirFixed().toString();
    }
}
