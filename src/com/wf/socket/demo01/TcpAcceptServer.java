package com.wf.socket.demo01;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 接收端
 */
public class TcpAcceptServer {
    public static void main(String[] args) throws IOException {
        //通过ServerSocket对象 创建接收端套接字
        ServerSocket serverSocket=null;
        //套接字
        Socket socket=null;
        //字节输入流
        InputStream in=null;

        //默认参数定义端口号  发送方依据这个端口号发送
        serverSocket=new ServerSocket(9000);
        socket = serverSocket.accept();
        //socket对象 获取输入流，拿到文件
        in = socket.getInputStream();

        //将发送者发送过来的 文件通过文件字节输出流 输出到指定路径下
        FileOutputStream fos = new FileOutputStream(new File("C:/f/wf/recevice.jpg"));
        int len;
        byte[] bytes=new byte[1024*10];
        while ((len=in.read(bytes))!=-1){
            fos.write(bytes,0,len);
        }

        //通知发送者接受完毕了
        OutputStream outputStream = socket.getOutputStream();

        outputStream.write("我是文件接收者：我已成功接受".getBytes());
        //接受结束后，关闭资源好习惯 这部分写的简单
        //一般建议先进行一个非空判断再关闭
        fos.close();
        serverSocket.close();
        socket.close();
        in.close();
    }
}



