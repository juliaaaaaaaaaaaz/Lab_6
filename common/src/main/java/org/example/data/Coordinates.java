package org.example.data;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Представление координат для элемента LabWork.
 */
@Setter
@Getter
public class Coordinates implements Serializable {

    private float x;

    private double y; //Максимальное значение поля: 106

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
