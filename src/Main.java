import java.util.Scanner;

public class Main {

    /**
     * This program in console mode allows :
     * <ul>
     *     <li>Add a bodybuilding set by specifying: the type of exercise, the number of repetitions, the weight used for the set</li>
     *     <li>Save all sets in a single csv file</li>
     *     <li>Ask for statistics on a particular type of exercise: weight versus repetition</li>
     * </ul>
     * @param args          Requires no argument
     */
    public static void main(String[] args) {
        // Instantiation of the Scanner used throughout the program
        Scanner scInput = new Scanner(System.in);

        // Instantiation of the FileCSV used throughout the program
        FileCSV fileCsv = new FileCSV();

        // Instantiation of the Menu used throughout the program
        Menu menu = new Menu();
        menu.router(scInput, fileCsv);

        scInput.close();
    }
}
