package com.example.naveen.wifidirect.listener;



import java.net.Socket;

public interface SocketObjectListener {
    void setServerSocket(Socket socket);
    void setClientSocket(Socket socket);
}
