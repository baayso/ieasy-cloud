package com.baayso.springcloud.file.utils;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.csource.common.FastdfsException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

import com.baayso.springcloud.file.domain.FastDFSFile;

/**
 * FastDFS文件管理工具类。
 *
 * @author ChenFangjie (2020/3/5 15:50)
 * @since 0.1
 */
public class FastDFSUtils {

    static {
        String path = new ClassPathResource("fdfs_client.conf").getPath();

        try {
            ClientGlobal.init(path); // 加载FastDFS配置文件
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (FastdfsException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传。
     *
     * @param fastDFSFile 文件上传信息
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws IOException, FastdfsException {
        // 获取Storage的连接信息
        StorageClient storageClient = getStorageClient();

        // 附加信息
        NameValuePair[] metaList = new NameValuePair[1];
        metaList[0] = new NameValuePair("作者", fastDFSFile.getAuthor());

        /*
         * 通过StorageClient访问Storage，实现文件上传，并且获取文件上传后的存储信息
         *
         * file_buff: 文件字节数组
         * file_ext_name: 文件的扩展名，如：png
         * meta_list: 附加信息，如：拍摄终端，拍摄地点，作者
         *
         * 返回结果：
         * result[0]: 文件上传后所存储的Storage的组名称，例如：group1
         * result[1]: 文件存储在Storage上的路径，例如：M00/02/33/xxx.png
         */
        return storageClient.uploadFile(fastDFSFile.getContent(), fastDFSFile.getExt(), metaList);
    }

    /**
     * 获取文件信息。
     *
     * @param groupName      文件组名，例如：group1
     * @param remoteFileName 文件的存储路径，例如：M00/02/33/xxx.png
     */
    public static FileInfo getFileInfo(String groupName, String remoteFileName) throws IOException, FastdfsException {
        // 获取Storage的连接信息
        StorageClient storageClient = getStorageClient();

        // 获取文件信息
        return storageClient.getFileInfo(groupName, remoteFileName);
    }

    /**
     * 文件下载。
     *
     * @param groupName      文件组名，例如：group1
     * @param remoteFileName 文件的存储路径，例如：M00/02/33/xxx.png
     */
    public static InputStream download(String groupName, String remoteFileName) throws IOException, FastdfsException {
        // 获取Storage的连接信息
        StorageClient storageClient = getStorageClient();

        byte[] buffer = storageClient.downloadFile(groupName, remoteFileName);

        return new ByteArrayInputStream(buffer);
    }

    /**
     * 文件删除。
     *
     * @param groupName      文件组名，例如：group1
     * @param remoteFileName 文件的存储路径，例如：M00/02/33/xxx.png
     */
    public static int delete(String groupName, String remoteFileName) throws IOException, FastdfsException {
        // 获取Storage的连接信息
        StorageClient storageClient = getStorageClient();

        return storageClient.deleteFile(groupName, remoteFileName);
    }

    /**
     * 获取Tracker信息。
     */
    public static String getTrackerURL() {
        // 获取TrackerServer的连接信息
        TrackerServer trackerServer = getTrackerServer();

        String ip = trackerServer.getInetSocketAddress().getHostString();
        int trackerHttpPort = ClientGlobal.getTrackerHttpPort();

        return "http://" + ip + ":" + trackerHttpPort;
    }

    /**
     * 获取Storage信息。
     *
     * @param groupName      文件组名，例如：group1
     * @param remoteFileName 文件的存储路径，例如：M00/02/33/xxx.png
     *
     * @return {@linkplain org.csource.fastdfs.StorageServer}
     */
    public static StorageServer getStorageInfo(String groupName, String remoteFileName) throws IOException {
        // 创建访问Tracker的客户端对象
        TrackerClient trackerClient = new TrackerClient();

        // 通过TrackerClient访问TrackerServer服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();

        // 获取Storage信息并返回
        return trackerClient.getStoreStorage(trackerServer);
    }

    /**
     * 获取Storage的IP和端口信息。
     *
     * @param groupName      文件组名，例如：group1
     * @param remoteFileName 文件的存储路径，例如：M00/02/33/xxx.png
     */
    public static ServerInfo[] getStorageServer(String groupName, String remoteFileName) throws IOException {
        // 创建访问Tracker的客户端对象
        TrackerClient trackerClient = new TrackerClient();

        // 通过TrackerClient访问TrackerServer服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();

        // 获取Storage的IP和端口信息并返回
        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
    }

    /**
     * 获取 TrackerServer。
     *
     * @return {@linkplain org.csource.fastdfs.TrackerServer}
     */
    public static TrackerServer getTrackerServer() {
        // 创建访问Tracker的客户端对象
        TrackerClient trackerClient = new TrackerClient();

        // 通过TrackerClient访问TrackerServer服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();

        return trackerServer;
    }

    /**
     * 通过 TrackerServer 获取 StorageClient。
     *
     * @param trackerServer {@linkplain org.csource.fastdfs.TrackerServer}
     *
     * @return {@linkplain org.csource.fastdfs.StorageClient}
     */
    public static StorageClient getStorageClient(TrackerServer trackerServer) {
        // 通过TrackerServer的连接信息可以获取Storage的连接信息，创建StorageClient对象存储Storage的连接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);

        return storageClient;
    }

    /**
     * 获取 StorageClient。
     *
     * @return {@linkplain org.csource.fastdfs.StorageClient}
     */
    public static StorageClient getStorageClient() {
        // 获取TrackerServer的连接信息
        TrackerServer trackerServer = getTrackerServer();

        // 通过TrackerServer的连接信息可以获取Storage的连接信息，创建StorageClient对象存储Storage的连接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);

        return storageClient;
    }

    public static void main(String[] args) {

        try (InputStream inputStream = download("group1", "M00/02/33/xxx.png");
             OutputStream outputStream = new FileOutputStream("D/:1_1.png")) {

            byte[] buffer = new byte[1024];

            while (inputStream.read(buffer) != -1) {
                outputStream.write(buffer);
            }

            outputStream.flush();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (FastdfsException e) {
            e.printStackTrace();
        }
    }

}
