package org.example.utils;


import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Утилита для генерации уникальных ID.
 */
public class IdGenerator {
    private static final Set<Long> generatedIds = new HashSet<>();

    /**
     * Генерирует уникальный ID.
     *
     * @return Уникальный ID.
     */
    public static long generateUniqueId() {
        long id;
        do {
            id = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        } while (generatedIds.contains(id));
        generatedIds.add(id);
        return id;
    }
}
