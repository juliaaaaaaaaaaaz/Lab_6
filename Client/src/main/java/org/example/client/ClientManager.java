package org.example.client;

import org.example.commandArguments.CommandData;
import org.example.commandArguments.Response;

import java.io.*;
import java.net.*;

/**
 * Управляет подключением к серверу, отправкой команд и получением ответов.
 */

public class ClientManager {

    private final String host;
    private final int port;
    private DatagramSocket socket;
    private InetAddress address;

    public ClientManager(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Устанавливает соединение с сервером и проверяет его доступность.
     */

    public void connect() {
        try {
            this.socket = new DatagramSocket();
            this.socket.setSoTimeout(5000); // Установка тайм-аута ожидания ответа от сервера
            this.address = InetAddress.getByName(host);
        } catch (Exception e) {
            System.out.println("Error connection to the server: " + e.getMessage());
        }
    }

    /**
     * Отправляет команду на сервер.
     *
     * @param commandData Данные команды для отправки.
     */

    public void sendCommand(CommandData commandData) {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(byteStream);
            os.writeObject(commandData);
            byte[] sendBuffer = byteStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, port);
            socket.send(sendPacket);
        } catch (IOException e) {
            System.out.println("Sending command failed: " + e.getMessage());
        }
    }

    /**
     * Прием ответа от сервера.
     *
     * @return Ответ от сервера.
     */

    public Response receiveResponse() {
        try {
            byte[] receiveBuffer = new byte[20000];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);

            ByteArrayInputStream byteStream = new ByteArrayInputStream(receivePacket.getData());
            ObjectInputStream is = new ObjectInputStream(byteStream);
            return (Response) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Receiving response failed: " + e.getMessage());
            return new Response(false, "Error receiving response.");
        }
    }

    /**
     * Разрывает соединение с сервером.
     */

    public void disconnect() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}
