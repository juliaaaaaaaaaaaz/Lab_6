package org.example.data;

import lombok.Getter;

@Getter
public enum Messages {
    LAB_WORK_SUCCESS_ADD("Lab work added successfully."),
    LAB_WORK_SUCCESS_CLEAR("Lab work collection cleared successfully."),
    LAB_WORK_SUCCESS_SAVE("Lab work collection saved successfully."),
    WELCOME("Welcome to the LabWork Manager!"),
    ENTER_HELP("Enter 'help' for the list of available commands."),
    ENTER_NAME("Enter name: "),
    ENTER_X("Enter coordinates x (Long): "),
    ENTER_Y("Enter coordinates y (Double): "),
    INVALID_COORDINATES("Invalid input. Please enter valid coordinates. (max y = 106)"),
    ENTER_MINIMAL_POINT("Enter minimalPoint: "),
    ENTER_DIFFICULTY("Enter difficulty (EASY, NORMAL, TERRIBLE): "),
    INVALID_DIFFICULTY("Invalid input. Please enter a valid difficulty (EASY, NORMAL, TERRIBLE, IMPOSSIBLE)."),
    ENTER_DISCIPLINE("Enter discipline name: "),
    INVALID_NUMBER("Invalid input. Please enter a valid number."),
    ENTER_SELF_STUDY_HOURS("Enter selfStudyHours: "),
    ENTER_MAX_POINT("Enter maximumPoint: "),
    ENTER_PRACTICE_HOURS("Enter practiceHours: "),
    INVALID_NAME("Invalid input. Please enter a valid name."),
    INVALID_MINIMAL_POINT("Invalid input. Please enter a valid minimal point(grater than 0)."),
    INVALID_MAX_POINT("Invalid input. Please enter a valid maximum point(grater than 0)."),
    FAILED_LOADING_FROM_FILE("Failed to load lab work collection from file."),
    FAILED_SAVE_TO_FILE("Failed to save lab work collection to file."),
    ERROR("Error: "),
    INVALID_PRACTICE_HOURS("Invalid input. Please enter a valid integer."),
    STARTING_WITH_EMPTY_COLLECTION("Starting with an empty collection."),
    NO_COMMAND_PROVIDED("No command provided."),
    OUTPUT("[OUTPUT]: "),
    COMMAND_NOT_RECOGNIZED("Command not recognized."),
    ERROR_UPDATING_LABWORK("Error updating lab work: "),
    LAB_WORK_WITH_ID("Lab work with ID "),
    UPDATE_SUCCESS(" updated successfully."),
    UPDATE_FAILED("Failed to update lab work with ID "),
    REMOVED("Removed "),
    ELEMENTS_LOWER(" elements lower than the provided lab work."),
    REMOVED_D_ELEMENTS("Removed %d elements greater than provided."),
    INVALID_ID_FORMAT("Invalid ID format."),
    REMOVE_SUCCESS("Lab work removed successfully."),
    NO_LAB_WORK_FOUND_WITH_THE_PROVIDED_ID("No lab work found with the provided id."),
    SCRIPT_SUCCESS("Script executed successfully."),
    LAB_WORK_SUCCESS_ADD_IF_MIN("New lab work added as it has the minimum practice hours."),
    LAB_WORK_NOT_MIN("Lab work not added as it does not have the minimum practice hours.");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

}
