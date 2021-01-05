package com.company;

import java.util.Scanner;

public class Main {


    public static String filename = "tasks.obj";

    public static void main(String[] args) {
        Todo todoList = new Todo();

        //A string to hold the choice that will be entered by the user
        String menuChoice = "-17";

        try {
            Scanner input = new Scanner(System.in);

            // reading the date from task data file
            // if this is the first time, a message will be shown that no data file is found
            todoList.readFromFile(filename);

            Messages.showMessage("Welcome to ToDoList", false);

            while (!menuChoice.equals("4")) {
                Messages.mainMenu(todoList.notCompletedCount(), todoList.completedCount());
                menuChoice = input.nextLine();

                switch (menuChoice) {
                    case "1":
                        Messages.listAllTasksMenu();
                        todoList.listAllTasks(input.nextLine());
                        break;
                    case "2":
                        todoList.readTaskFromUser();
                        break;
                    case "3":
                        todoList.listAllTasksWithIndex();
                        Messages.editTaskSelection();
                        todoList.editTask(input.nextLine());
                        break;
                    case "4":
                        break;

                    default:
                        Messages.unknownMessage();
                }
            }

            // saving the task details in data file
            // if this is the first time, a new task file will be created
            todoList.saveToFile(filename);
            Messages.byeMessage();

        } catch (Exception e) {
            Messages.showMessage("UNCAUGHT EXCEPTION THROWN", true);
            System.out.println("Trying to write the unsaved data of all tasks in data file");
            todoList.saveToFile(filename);
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
