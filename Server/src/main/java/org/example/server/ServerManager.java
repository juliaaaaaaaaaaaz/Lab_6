package org.example.server;

import org.example.commandArguments.CommandData;
import org.example.commandArguments.Response;
import org.example.di.DIContainer;
import org.example.utils.CommandExecutor;
import org.example.utils.LabWorkCollection;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

/**
 * Управляет серверной частью приложения, включая прием и обработку команд от клиентов,
 * а также выполнение внутренних команд сервера, таких как сохранение и выход.
 */
public class ServerManager {
    private final int port;
    private DatagramSocket socket;
    private final DIContainer diContainer;
    private volatile boolean running = true;


    /**
     * Конструктор создает экземпляр серверного менеджера.
     *
     * @param port        Порт, на котором сервер будет принимать входящие соединения.
     * @param diContainer Контейнер зависимостей для доступа к сервисам и командам.
     */
    public ServerManager(int port, DIContainer diContainer) {
        this.port = port;
        this.diContainer = diContainer;


    }

    /**
     * Запускает сервер, ожидая входящие команды от клиентов и обрабатывая внутренние команды сервера.
     * Создает отдельный поток для чтения внутренних команд сервера из консоли.
     */
    public void startServer() {
        try {
            socket = new DatagramSocket(port);
            CommandExecutor commandExecutor = diContainer.getService(CommandExecutor.class);
            LabWorkCollection labWorkCollection = diContainer.getService(LabWorkCollection.class);

            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (running) {
                    if (scanner.hasNextLine()) {
                        String command = scanner.nextLine().trim();
                        switch (command) {
                            case "save":
                                labWorkCollection.saveToFile(); // Сохраняем коллекцию в файл
                                System.out.println("Collection saved.");
                                break;
                            case "exit":
                                running = false; // Устанавливаем флаг running в false для остановки сервера
                                socket.close(); // Закрываем сокет, чтобы прервать блокировку на socket.receive()
                                System.out.println("Server stopped.");
                                System.exit(0);
                                break;
                        }
                    }
                }
            }).start();
            while (true) {

                byte[] buffer = new byte[2020];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(packet.getData()))) {
                    CommandData commandData = (CommandData) ois.readObject();
                    String result = commandExecutor.executeCommand(commandData.commandName(), commandData.arguments());
                    Response response = new Response(true, result);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(response);
                    byte[] responseBytes = baos.toByteArray();

                    DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length, packet.getAddress(), packet.getPort());
                    socket.send(responsePacket);
                } catch (ClassNotFoundException e) {
                    System.out.println("Error: " + e.getMessage());

                }

            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
