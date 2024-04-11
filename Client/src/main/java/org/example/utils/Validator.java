package org.example.utils;


import org.example.data.Coordinates;
import org.example.data.Difficulty;
import org.example.data.Discipline;
import org.example.exceptions.ValidationException;

/**
 * Валидатор для проверки корректности вводимых данных для объектов LabWork и их компонентов.
 */
public class Validator {
    /**
     * Проверяет корректность имени.
     *
     * @param name Имя для проверки.
     * @throws ValidationException если имя некорректно.
     */
    public void validateName(String name) throws ValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException();
        }
    }

    public void validateCoordinates(Coordinates coordinates) throws ValidationException {
        if (coordinates == null) {
            throw new ValidationException();
        }

        if (coordinates.getY() > 106) {
            throw new ValidationException();
        }
    }

    public void validateMinimalPoint(Long minimalPoint) throws ValidationException {
        if (minimalPoint == null || minimalPoint <= 0) {
            throw new ValidationException();
        }
    }

    public void validateMaximumPoint(Long maximumPoint) throws ValidationException {
        if (maximumPoint == null || maximumPoint <= 0) {
            throw new ValidationException();
        }
    }

    public void validateDiscipline(Discipline discipline) throws ValidationException {
        if (discipline == null || discipline.getName().isBlank()) {
            throw new ValidationException();
        }
    }

    public void validateSelfStudyHours(Long selfStudyHours) throws ValidationException {
        if (selfStudyHours == null || selfStudyHours < 1) {
            throw new ValidationException();
        }
    }

    public void validateDifficulty(Difficulty difficulty) throws ValidationException {
        if (difficulty == null) {
            throw new ValidationException();
        }
    }
}
