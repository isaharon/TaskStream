package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        printDeadlinesUsingStreams(tasksData);

        ArrayList<Task> filteredList = filterTasksBySting(tasksData, "11");
        printData(filteredList);
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStreams(ArrayList<Task> tasksData) {
        tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .sorted((t1, t2) -> t1.getDescription().toLowerCase().compareTo(t2.getDescription().toLowerCase()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterTasksBySting(ArrayList<Task> tasksData, String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>)tasksData.stream()
                .filter((t) -> t.getDescription().contains(filterString))
                .collect(toList());
        return filteredList;
    }
}
