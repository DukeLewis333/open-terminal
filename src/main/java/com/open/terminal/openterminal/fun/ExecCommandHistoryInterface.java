package com.open.terminal.openterminal.fun;

import java.io.InputStream;
import java.util.List;

/**
 * @description: 执行命令历史功能接口
 * @author huangjialong
 * @date 2026/1/5 11:27
 * @version 1.0
 */
public interface ExecCommandHistoryInterface {
    /**
     * 从远程服务器加载历史记录的方法
     * 该方法用于获取并加载存储在远程服务器上的历史数据
     * 涉及网络请求和数据处理操作
     */
    void loadRemoteHistory();

    /**
     * 从输入流中读取历史命令记录
     *
     * @param is 输入流，可能包含历史命令记录数据
     * @param isZsh 布尔值，指示是否为zsh shell的历史记录格式
     * @return 返回一个字符串列表，包含读取到的历史命令记录
     */
    List<String> readHistoryStream(InputStream is, boolean isZsh);

    /**
     * 显示历史命令对话框的方法
     * 用于展示用户之前执行过的命令列表
     *
     * @param commands 包含历史命令的字符串列表
     *                每个字符串代表一条用户曾经执行过的命令
     */
    void showHistoryDialog(List<String> commands);

    /**
     * 从历史记录中执行命令的方法
     * 该方法用于执行曾经输入过的命令
     *
     * @param command 要执行的命令字符串，该命令应该是历史记录中存在的命令
     */
    void executeCommandFromHistory(String command);
}
