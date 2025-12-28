package com.open.terminal.openterminal;

import com.open.terminal.openterminal.util.ThreadUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import com.jcraft.jsch.*;
import java.io.*;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

public class TerminalController {
    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(TerminalController.class);

    @FXML private TextArea terminalOutput;
    @FXML private TextField commandInput;

    private Session session;
    private Channel channel;
    private OutputStream outputStream;


    @FXML
    public void initialize() {
        commandInput.setOnAction(e -> sendCommand());
    }

    public void connectSSH(String host, int port, String user, String password) {
        ThreadUtil.submitTask(() -> {
            try {
                JSch jsch = new JSch();
                session = jsch.getSession(user, host, port);
                session.setPassword(password);

                Properties config = new Properties();
                config.put("StrictHostKeyChecking", "no");
                session.setConfig(config);

                Platform.runLater(() ->
                        appendOutput("正在连接到 " + host + ":" + port + "...\n")
                );

                session.connect(10000);

                Platform.runLater(() ->
                        appendOutput("连接成功!\n\n")
                );

                channel = session.openChannel("shell");

                InputStream inputStream = channel.getInputStream();
                outputStream = channel.getOutputStream();

                channel.connect();

                // 使用虚拟线程读取输出
                ThreadUtil.submitTask(() -> readOutput(inputStream));

            } catch (Exception e) {
                Platform.runLater(() ->
                        appendOutput("连接失败: " + e.getMessage() + "\n")
                );
                e.printStackTrace();
            }
        });
    }

    private void readOutput(InputStream inputStream) {
        try {
            byte[] buffer = new byte[1024];
            while (true) {
                while (inputStream.available() > 0) {
                    int bytesRead = inputStream.read(buffer, 0, 1024);
                    if (bytesRead < 0) break;

                    String text = new String(buffer, 0, bytesRead);
                    Platform.runLater(() -> appendOutput(text));
                }

                if (channel.isClosed()) {
                    if (inputStream.available() > 0) continue;
                    Platform.runLater(() ->
                            appendOutput("\n连接已关闭\n")
                    );
                    break;
                }

                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void sendCommand() {
        String command = commandInput.getText();
        if (!command.isEmpty() && outputStream != null) {
            ThreadUtil.submitTask(() -> {
                try {
                    outputStream.write((command + "\n").getBytes());
                    outputStream.flush();
                    Platform.runLater(() -> commandInput.clear());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void appendOutput(String text) {
        terminalOutput.appendText(text);
        terminalOutput.setScrollTop(Double.MAX_VALUE);
    }

    public void disconnect() {
        log.info("终端连接断开...");
        if (channel != null && channel.isConnected()) {
            channel.disconnect();
        }
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }
}
