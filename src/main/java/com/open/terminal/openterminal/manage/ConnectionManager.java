package com.open.terminal.openterminal.manage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.terminal.openterminal.model.SavedConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description: 连接信息管理功能类
 * @author：dukelewis
 * @date: 2026/1/4
 * @Copyright： https://github.com/DukeLewis
 */
public class ConnectionManager {
    private static final Logger log = LoggerFactory.getLogger(ConnectionManager.class);
    private static final String FILE_NAME = "connections.json";
    private static final File STORAGE_FILE = Paths.get(System.getProperty("user.home"), ".openterminal", FILE_NAME).toFile();
    private static ConnectionManager instance;
    private final ObjectMapper mapper = new ObjectMapper();
    private List<SavedConnection> connections = new ArrayList<>();

    public static class ConnectionManagerHolder {
        private ConnectionManagerHolder() {}
        private static final ConnectionManager INSTANCE = new ConnectionManager();
    }

    private ConnectionManager() {
        load();
    }

    public static ConnectionManager getInstance() {
        return ConnectionManagerHolder.INSTANCE;
    }

    private void load() {
        if (!STORAGE_FILE.exists()) {
            return;
        }
        try {
            connections = mapper.readValue(STORAGE_FILE, new TypeReference<List<SavedConnection>>() {});
        } catch (IOException e) {
            log.error("加载连接配置失败", e);
        }
    }

    public void save() {
        try {
            if (!STORAGE_FILE.getParentFile().exists()) {
                STORAGE_FILE.getParentFile().mkdirs();
            }
            mapper.writeValue(STORAGE_FILE, connections);
        } catch (IOException e) {
            log.error("保存连接配置失败", e);
        }
    }

    public List<SavedConnection> getAll() {
        return new ArrayList<>(connections);
    }

    public void addOrUpdate(SavedConnection conn) {
        connections.removeIf(c -> c.getId().equals(conn.getId()));
        connections.add(conn);
        save();
    }

    public void remove(String id) {
        connections.removeIf(c -> c.getId().equals(id));
        save();
    }
}
