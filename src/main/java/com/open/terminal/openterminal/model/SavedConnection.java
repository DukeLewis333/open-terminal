package com.open.terminal.openterminal.model;

import java.io.Serializable;
import java.util.UUID;

public class SavedConnection implements Serializable {
    public static final String CONN_MANAGER = "CONN_MANAGER";
    private String id;
    private String name; // 连接名称 (如 "生产环境DB")
    private String host;
    private int port;
    private String user;
    private String password; // 实际生产中建议加密存储
    private long lastConnected; // 最后连接时间

    public SavedConnection() {
        this.id = UUID.randomUUID().toString();
    }

    public SavedConnection(String name, String host, int port, String user, String password) {
        this();
        this.name = name;
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    // Getters and Setters...
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }
    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public long getLastConnected() { return lastConnected; }
    public void setLastConnected(long lastConnected) { this.lastConnected = lastConnected; }
}
