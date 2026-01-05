package com.open.terminal.openterminal.fun;

/**
 * @description: 系统监控功能接口
 * @author huangjialong
 * @date 2026/1/5 11:22
 * @version 1.0
 */
public interface SystemMonitorInterface {
    /**
     * 启动系统监控功能
     * 该方法用于初始化并启动系统监控组件，开始收集系统性能数据
     * 如CPU使用率、内存占用、网络状态等关键指标
     */
    void startSystemMonitoring();

    /**
     * 解析原始输出并更新监控信息的方法
     * 该方法接收一个字符串类型的原始输出，对其进行解析并更新相关的监控数据
     *
     * @param rawOutput 需要解析的原始输出字符串，包含监控数据信息
     */
    void parseAndUpdateMonitor(String rawOutput);
}
