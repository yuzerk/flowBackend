package com.flower.xin.common.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.*;
import java.util.UUID;

public class OssUtil {
    private final static String endpoint = "oss-cn-beijing.aliyuncs.com";

    private final static String accessKeyId = "*";

    private final static String accessKeySecret = "*";

    private final static String ossBucketName = "flower-xin";

    private final static OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

    public static String upload(byte[] data) {
        String uuid = UUID.randomUUID().toString() + ".jpg";
        ossClient.putObject(ossBucketName, uuid, new ByteArrayInputStream(data));
        return ossBucketName + "." + endpoint + "/" + uuid;
    }

    private static byte[] InputStream2ByteArray(String filePath) throws IOException {

        InputStream in = new FileInputStream(filePath);
        byte[] data = toByteArray(in);
        in.close();

        return data;
    }

    private static byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        upload(InputStream2ByteArray("/Users/eiven/Downloads/绿巨人.JPG"));
        Thread.sleep(10000000000000000L);
    }
}
