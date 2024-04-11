package org.example;

import org.example.di.DIContainer;
import org.example.server.ServerManager;

/**
 * Главный класс для запуска серверной части приложения.
 */

public class Main {
    /**
     * Точка входа в серверное приложение. Инициализирует контейнер зависимостей и запускает сервер.
     *
     * @param args Аргументы командной строки, не используются в данной программе.
     */

    public static void main(String[] args) {
        DIContainer diContainer = new DIContainer();
        ServerManager serverManager = diContainer.getService(ServerManager.class);
        serverManager.startServer();
    }
}
