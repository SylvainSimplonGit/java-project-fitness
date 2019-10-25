import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Menu {
    /**
     * Message that will be displayed in the center of the 1st line of the menu
     */
    private final String header = "Fitness Coach";
    /**
     * Message that will be displayed in the center of the last line of the menu
     */
    private final String footer = "#############";
    /**
     * Width of screen to display the menu
     */
    private final int widthScreen = 55;

    private final TreeMap<Integer, String> menuMain;
    private final TreeMap<Integer, String> menuExo;
    private final TreeMap<Integer, String> menuStat;

    Menu() {
        menuMain = new TreeMap();
        menuMain.put(1, "Ajouter un set");
        menuMain.put(2, "Afficher les performances sur un exercice");
        menuMain.put(0, "Quitter le programme");

        menuExo = new TreeMap();
        menuExo.put(0, "Retour au menu précédent");
        menuExo.put(1,"SQUAT");
        menuExo.put(2,"LEG_EXTENSION");
        menuExo.put(3,"LEG_CURL");
        menuExo.put(4,"LEG_PRESS");
        menuExo.put(5,"CRUNCH");
        menuExo.put(6,"PLANK");
        menuExo.put(7,"BENCH_PRESS");
        menuExo.put(8,"TRICEPS_EXTENSION");
        menuExo.put(9,"BICEPS_CURL");

        menuStat = new TreeMap();
        menuStat.put(0, "Retour au menu précédent");
        menuStat.put(1, "Stats de poids (/ répétitions)");
        menuStat.put(2, "Stats de nombre répétitions");
        menuStat.put(3, "Stats de poids (/ set)");
    }

    /**
     * Display the line of menu with centered message
     * @param separate      Character repeated before and after message
     * @param message       Message displayed centered
     */
    private void displayMenuFrame(String separate, String message) {
        int lengthOfMessage = message.length() + 2;
        boolean isPairLength  = (this.widthScreen - lengthOfMessage) % 2 == 0;
        int lengthOfPadLeft = (this.widthScreen - lengthOfMessage ) / 2;
        int lengthOfPadRight = (isPairLength) ? lengthOfPadLeft : lengthOfPadLeft + 1;
        String line = separate.repeat(lengthOfPadLeft) + " " + message + " " + separate.repeat(lengthOfPadRight);
        System.out.println(line);
    }

    /**
     * Display message for invalid entry
     * @param scInput       Scanner used throughout the program
     */
    private static void displayMessageInvalidEntry(Scanner scInput) {
        scInput.nextLine();
        System.out.println("Une erreur est survenue !");
        System.out.println("Veuillez entrer de nouveau votre choix : ");
    }

    /**
     * Valid an integer entry
     * @param scInput       Scanner used throughout the program
     * @return              The entry validated
     */
    private static int waitEntryInt(Scanner scInput) {
        boolean noValid = true;
        int entry = 0;

        while (noValid) {
            try {
                entry = scInput.nextInt();
                noValid = false;
            } catch (Exception e) {
                displayMessageInvalidEntry(scInput);
                noValid = true;
            }
        }
        return entry;
    }

    /**
     * Valid an float entry
     * @param scInput       Scanner used throughout the program
     * @return              The entry validated
     */
    private static float waitEntryFloat(Scanner scInput) {
        boolean noValid = true;
        float entry = 0;

        while (noValid) {
            try {
                entry = scInput.nextFloat();
                noValid = false;
            } catch (Exception e) {
                displayMessageInvalidEntry(scInput);
                noValid = true;
            }
        }
        return entry;
    }

    /**
     * Display menu with added header and footer
     * @param menu          The menu to display
     */
    private void displayMenus(TreeMap<Integer, String> menu) {
        // Display Header
        displayMenuFrame("-", header);
        // Display Entries
        for (Map.Entry<Integer, String> line : menu.entrySet()) {
            System.out.println(line.getKey() + ". --> " + line.getValue());
        }
        // Display Footer
        displayMenuFrame("-", footer);
        // Display Choice
        System.out.println("Entrez votre choix : ");
    }

    /**
     * Display the called menu and validate the user entry
     * @param entry         The entry by which the menu was called
     * @param scInput       Scanner used throughout the program
     * @param menu          The called menu
     * @return              The entry validated chosen by the user
     */
    private int displayMenusToEntry(int entry, Scanner scInput, TreeMap<Integer, String> menu) {
        boolean isNotValid = true;
        do {
            displayMenus(menu);
            int choice = waitEntryInt(scInput);
            if (menu.containsKey(choice)) {
                entry = choice;
                isNotValid = false;
            } else {
                scInput.nextLine();
                System.out.println("Mauvaise saisie,veuillez entrer un choix valide !");
            }
        } while (isNotValid);

        return entry;
    }

    /**
     * Display menu top add a new set
     * @param choice        Choice of exercise
     * @param scInput       Scanner used throughout the program
     * @param file          CSV file in which the data are stored
     */
    private void displayMenuAddSet(int choice, Scanner scInput, FileCSV file) {
        String exercise = this.menuExo.get(choice);
        file.setChosenExercise(exercise);
        // Request the number of repetitions
        System.out.println("Veuillez entrez le nombre de répétition : ");
        // Valider que c'est un integer
        int repeatNumber = waitEntryInt(scInput);
        // Validate that it is an integer
        System.out.println("Veuillez entrez le poids utilisé : ");
        // Validate that it is a float
        float weight = waitEntryFloat(scInput);
        Set set = new Set(exercise, repeatNumber, weight);
        file.addSet(set);
    }

    /**
     * Display statistical data "Weight" of the chosen exercise
     * @param file          CSV file in which the data are stored
     */
    private void displayWeightStatistics(FileCSV file) {
        Exercise exercise = file.getCollectionOfChosenExercise();
        System.out.println("poids moyen soulevé : " + exercise.getAverageOfWeight() + " kg");
        System.out.println("poids médian soulevé : " + exercise.getMedianOfWeight() + " kg");
        System.out.println("poids max soulevé : " + exercise.getMaxOfWeight() + " kg");
    }

    /**
     * Display statistical data "Repetition" of the chosen exercise
     * @param file          CSV file in which the data are stored
     */
    private void displayRepeatStatistics(FileCSV file) {
        Exercise exercise = file.getCollectionOfChosenExercise();
        System.out.println("nombre moyen de répétitions : " + exercise.getAverageOfRepeat() + " kg");
        System.out.println("nombre médian de répétitions : " + exercise.getMedianOfRepeat() + " kg");
        System.out.println("nombre max de répétitions : " + exercise.getMaxOfRepeat() + " kg");
    }

    /**
     * Display statistical data "Set" of the chosen exercise
     * @param file          CSV file in which the data are stored
     */
    private void displaySetStatistics(FileCSV file) {
        Exercise exercise = file.getCollectionOfChosenExercise();
        System.out.println("poids moyen soulevé par set : " + exercise.getAverageOfSet() + " kg");
        System.out.println("poids médian soulevé par set : " + exercise.getMedianOfSet() + " kg");
        System.out.println("poids max soulevé par set : " + exercise.getMaxOfSet() + " kg");
    }

    /**
     * Display a message to pause after displaying a statistic
     * @param scInput       Scanner used throughout the program
     */
    private void pauseMenu(Scanner scInput) {
        scInput.nextLine();
        System.out.println("Appuyer sur une touche pour revenir au menu précédent");
        scInput.nextLine();
    }

    /**
     * Management of the displays of the different menus.
     * The access to the different menus is done by the value of variable 'choice'
     * <ul>
     *      <li>To access to the main menu, the values of variable 'choice' are :
     *      <ul>
     *          <li>-1 for the first entry in the menu</li>
     *          <li>10 coming from the entry <b>Add a set</b></li>
     *          <li>20 coming from the entry <b>Display performance on a exercise</b></li>
     *      </ul>
     *      </li>
     *      <li>
     *          To access to the menu <b>Add a set</b>, the value of variable 'choice' is 1
     *      </li>
     *      <li>
     *          To access to the menu choice an exercise, the value of variable 'choice' is 2
     *      </li>
     *      <li>
     *          To access to the menu <b>Display performance on a exercise</b>, the value of variable 'choice' is 3
     *      </li>
     *      <li>
     *          To display the calculation functions, the value of variable 'choice' will be equal or greater than 201
     *      </li>
     * </ul>
     * @param scInput       Scanner used throughout the program
     * @param file          CSV file in which the data are stored
     */
    void router(Scanner scInput, FileCSV file) {
        int choice = -1;
        int indexExo;

        while (choice != 0) {
            switch (choice) {
                case -1:    // menu entry
                case 10:    // Return from the Exo menu
                case 20:    // Return from the Stat menu
                    choice = displayMenusToEntry(-1, scInput, this.menuMain);
                case 0:     // Quit the program
                    break;
                case 1:     // Add a set
                    // Choice of the type of exercise
                    choice = displayMenusToEntry(1, scInput, this.menuExo) + 10;
                    indexExo = choice - 10;
                    // Verification that the choice is not to go back to the previous menu
                    if (choice != 10)
                        // Request Exercise Settings and Save Exercise to File
                         displayMenuAddSet(indexExo, scInput, file);
                    // Redirection to the Main menu from the Exo menu
                    choice = 10;
                    break;
                case 2:     // Show choose an exercise
                    // Choice of the type of exercise
                    choice = displayMenusToEntry(1, scInput, this.menuExo) + 20;
                    indexExo = choice - 20;
                    if (choice != 20) {
                        // Reading the file to retrieve the exercises
                        file.readCsvFile();
                        // Assignment of the selected exercise
                        file.setChosenExercise(this.menuExo.get(indexExo));
                        // Verify the presence of data for this exercise
                        if (!file.hasDataForChosenExercise()) {
                            System.out.println("Pas de données pour cet exercice !");
                            pauseMenu(scInput);
                            // Redirect to the Choose an exercise menu
                            choice = 2;
                        } else {
                            // Redirection to the Select Stat menu to display
                            choice = 3;
                        }
                    } else {
                        // Redirection to the Main menu from the Stats menu
                        choice = 20;
                    }
                    break;
                case 3:     // Show Stats menu
                    // Choosing the type of stat to display
                    displayMenuFrame("-", "Choix de l'exercice");
                    displayMenuFrame(" ", file.getChosenExercise());
                    choice = displayMenusToEntry(1, scInput, this.menuStat) + 200;
                    if (choice == 200)
                        choice = 2;
                    break;
                case 201:   // Show Weight Stats (by repetition)
                    displayWeightStatistics(file);
                    pauseMenu(scInput);
                    choice = 3;
                    break;
                case 202:   // Show number of repetitions Stats
                    displayRepeatStatistics(file);
                    pauseMenu(scInput);
                    choice = 3;
                    break;
                case 203:   // Show Weight Stats (by set)
                    displaySetStatistics(file);
                    pauseMenu(scInput);
                    choice = 3;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }
}
