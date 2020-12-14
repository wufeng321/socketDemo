package com.wf.socket.demo01;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


/**
 * 发送者
 */
public class TcpSendClient {
    public static void main(String[] args) throws Exception {
        //1.通过socket对象  创建socket连接
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9000);
        //2，创建一个字节输出流
        OutputStream os = socket.getOutputStream();
        //3.字节文件输入流 读取文件
        //避免出错 写需要发送文件的绝对路径
        FileInputStream fis = new FileInputStream(new File("C:/Users/答案x/Desktop/作业/lebron.jpg"));
        //4.字节文件输出流 写出文件
        byte[] buffer=new byte[1024*10];
        int len;
        while ((len=fis.read(buffer))!=-1){
            //socket编程基于I/O流 所以
            //输出流写转为字节数组的文件  =向接收方发送文件
            os.write(buffer,0,len);
        }
        //通知服务器我传送完了 调用shutdownOutput() 否则影响接下来代码执行
        socket.shutdownOutput();

        //确定服务器接受完毕，断开连接
        //获取接收方响应回来的 接受完毕！ 响应
        InputStream inputStream = socket.getInputStream();
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        byte[] bytes2=new byte[1024*10];
        int len2;
        while ((len2=inputStream.read(bytes2))!=-1){
            baos.write(bytes2,0,len2);
        }
        //显示接收方的响应
        System.out.println(baos.toString());

        //发送结束后，最后关闭所有资源
        //关闭资源好习惯 这部分写的简单
        //一般建议先进行一个非空判断再关闭
        baos.close();
        fis.close();
        os.close();
        socket.close();

    }
}

