import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

class FileCSV {
    private final String pathCsv = "resources/datas.csv";
    private HashMap<String, Exercise> collectionExercise;
    private String chosenExercise;

    FileCSV() {
        this.collectionExercise = new HashMap<>();
    }

    /**
     * Get the chosen exercise
     * @return                  the chosen exercise
     */
    String getChosenExercise() {
        return this.chosenExercise;
    }

    /**
     * Set the chosen exercise
     * @param chosenExercise    the chosen exercise
     */
    void setChosenExercise(String chosenExercise) {
        this.chosenExercise = chosenExercise;
    }

    /**
     * Get the exercise collection of the chosen exercise
     * @return                  the exercise collection of the chosen exercise
     */
    Exercise getCollectionOfChosenExercise() {
        return this.collectionExercise.get(this.chosenExercise);
    }

    /**
     * Read CSV file and fill the exercises collections
     */
    void readCsvFile() {
        try(Scanner scCSV = new Scanner(new File(this.pathCsv))) {
            int lineCounter = 0;
            String exercise;
            int repeatNumber;
            float weight;

            while (scCSV.hasNextLine()) {
                String line = scCSV.nextLine();
                if (lineCounter > 0) {
                    String[] arrOfLine = line.split(";",3);
                    if (isValidString(arrOfLine[0]) && isValidInt(arrOfLine[1]) && isValidFloat(arrOfLine[2])) {
                        exercise = arrOfLine[0];
                        repeatNumber = Integer.parseInt(arrOfLine[1]);
                        weight = Float.parseFloat(arrOfLine[2]);
                        Set setOfLine = new Set(exercise, repeatNumber, weight);
                        if (this.collectionExercise.containsKey(exercise)) {
                            this.collectionExercise.get(exercise).addSet(setOfLine);
                        } else {
                            Exercise exerciseOfLine = new Exercise(exercise);
                            exerciseOfLine.addSet(setOfLine);
                            this.collectionExercise.put(exercise, exerciseOfLine);
                        }
                    } else {
                        System.out.println("Erreur dans la donnée de la ligne " + lineCounter + " du fichier " + this.pathCsv);
                    }
                }
                ++lineCounter;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lets you know if the chosen exercise has data
     * @return                  true if the chosen exercise has data otherwise false
     */
    boolean hasDataForChosenExercise() {
        Exercise exercise = this.getCollectionOfChosenExercise();
        return exercise != null;
    }

    /**
     * Check if the object is a String
     * @param testObject        an object to test
     * @return                  true if the object is a string otherwise false
     */
    private boolean isValidString(Object testObject) {
        return (testObject instanceof String);
    }

    /**
     * Check if the object is a Integer
     * @param testString        a string to test
     * @return                  true if the string can be converted to integer otherwise false
     */
    private boolean isValidInt(String testString) {
        try {
             Integer.parseInt(testString);
             return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if the object is a Float
     * @param testString        a string to test
     * @return                  true if the string can be converted to float otherwise false
     */
    private boolean isValidFloat(String testString) {
        try {
            Float.parseFloat(testString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Append a line in the CSV file
     * @param line              the line to save in CSV file
     */
    private void writeLineInCsvFile(String line) {
        try (PrintStream fileOut = new PrintStream(new FileOutputStream(this.pathCsv, true))) {
            fileOut.println(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a set in the CSV file
     * @param set               the set to save in CSV file
     */
    void addSet(Set set) {
        String line = set.getExercise() + ";" + set.getRepeatNumber() + ";" + set.getWeight();
        this.writeLineInCsvFile(line);
    }

}
