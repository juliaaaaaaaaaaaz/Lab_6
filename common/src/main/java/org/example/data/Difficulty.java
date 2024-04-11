package org.example.data;

import java.io.Serializable;

/**
 * Перечисление сложностей для элемента LabWork.
 */
public enum Difficulty implements Serializable {
    EASY,
    NORMAL,
    IMPOSSIBLE,
    TERRIBLE
}