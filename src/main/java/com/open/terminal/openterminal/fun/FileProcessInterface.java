package com.open.terminal.openterminal.fun;

import com.open.terminal.openterminal.model.RemoteFile;

import java.nio.file.Path;

/**
 * @description: 文件处理功能接口
 * @author huangjialong
 * @date 2026/1/4 17:48
 * @version 1.0
 */
public interface FileProcessInterface {
    /**
     * 使用文件选择器打开远程文件的方法
     * @param file 要打开的远程文件对象
     */
    void openRemoteFileWithChooser(RemoteFile file);

    /**
     * 下载远程文件并显示进度
     * @param remotePath 远程文件路径
     * @param localPath 本地保存路径
     * @param totalSize 文件总大小（用于计算进度）
     * @throws Exception 可能抛出的异常
     */
    void downloadRemoteFileWithProgress(String remotePath, Path localPath, long totalSize) throws Exception;

    /**
     * 处理文件列表的方法
     * 该方法用于执行与文件列表相关的操作，具体实现可能包括文件读取、处理、显示等功能
     */
    void handleFileList();

    /**
     * 处理文件上传的方法
     * 上传文件逻辑,包括单个文件和目录递归上传，上传到远程机器的当前目录
     */
    void handleUploadFile();

    /**
     * 从远程服务器加载指定路径下的文件
     * 该方法用于从远程位置获取文件数据，并将其加载到本地系统中
     *
     * @param path 远程服务器中文件的路径，用于定位需要加载的文件
     *             路径格式应符合远程服务器的规范要求
     */
    void loadRemoteFiles(String path);
}
