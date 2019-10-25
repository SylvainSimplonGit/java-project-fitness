import java.util.ArrayList;
import java.util.Collections;

class Exercise {
    private String nameExercise;
    /**
     * Collection of Sets for the exercise
     */
    private ArrayList<Set> collectionSet;
    /**
     * multiplier which makes it possible to calculate the median values of collections of Integer
     */
    private final int multiplier = 1000;
    /**
     * Number of decimal places displayed
     */
    private final int numberOfDec = 1;

    Exercise(String name) {
        this.nameExercise = name;
        this.collectionSet = new ArrayList<>();
    }

    /**
     * Get the size of exercise collection
     * @return                  the size of exercise collection
     */
    private int getCountSet() {
        return this.collectionSet.size();
    }

    /**
     * Add a set to the exercise collection
     * @param set               the Set to add to the collection
     */
    void addSet(Set set) {
        this.collectionSet.add(set);
    }

    /**
     * Get the average value of the weights used during the exercise
     * @return                  the average value of weights
     */
    float getAverageOfWeight() {
        if (this.getCountSet() < 1) {
            return 0;
        } else {
            float sumWeight = 0.0f;
            for (int i = 0; i != this.getCountSet(); ++i) {
                sumWeight += this.collectionSet.get(i).getWeight();
            }
            return roundFloat(sumWeight / this.getCountSet(), numberOfDec);
        }
    }

    /**
     * Get the median value of the weights used during the exercise
     * @return                  the median value of weights
     */
    float getMedianOfWeight() {
        if (this.getCountSet() < 1) {
            return 0;
        } else {
            ArrayList<Integer> weights = new ArrayList<>();
            for (int i = 0; i != this.getCountSet(); ++i) {
                int valueWithoutDec = Math.round(this.collectionSet.get(i).getWeight() * multiplier);
                weights.add(valueWithoutDec);
            }
            return getMedianOfInteger(weights) / multiplier;
        }
    }

    /**
     * Get the max value of the weights used during the exercise
     * @return                  the max value of weights
     */
    float getMaxOfWeight() {
        if (this.getCountSet() < 1) {
            return 0;
        } else {
            float maxWeight = 0.0f;
            for (int i = 0; i != this.getCountSet(); ++i) {
                maxWeight = Math.max(this.collectionSet.get(i).getWeight(), maxWeight);
            }
            return maxWeight;
        }
    }

    /**
     * Get the average value of repeats made during the exercise
     * @return                  the average weights
     */
    float getAverageOfRepeat() {
        if (this.getCountSet() < 1) {
            return 0;
        } else {
            float sumRepeat = 0.0f;
            for (int i = 0; i != this.getCountSet(); ++i) {
                sumRepeat += this.collectionSet.get(i).getRepeatNumber();
            }
            return roundFloat(sumRepeat / this.getCountSet(), numberOfDec);
        }
    }

    /**
     * Get the median value of repeats made during the exercise
     * @return                  the median value of repeats
     */
    float getMedianOfRepeat() {
        if (this.getCountSet() < 1) {
            return 0;
        } else {
            ArrayList<Integer> repeats = new ArrayList<>();
            for (int i = 0; i != this.getCountSet(); ++i) {
                repeats.add(this.collectionSet.get(i).getRepeatNumber());
            }
            return getMedianOfInteger(repeats);
        }
    }

    /**
     * Get the max value of repeats made during the exercise
     * @return                  the max value of repeats
     */
    float getMaxOfRepeat() {
        if (this.getCountSet() < 1) {
            return 0;
        } else {
            float maxRepeat = 0.0f;
            for (int i = 0; i != this.getCountSet(); ++i) {
                maxRepeat = Math.max(this.collectionSet.get(i).getRepeatNumber(), maxRepeat);
            }
            return maxRepeat;
        }
    }

    /**
     * Get the average value of the weights used during the set
     * @return                  the average value of weights
     */
    float getAverageOfSet() {
        if (this.getCountSet() < 1) {
            return 0;
        } else {
            float sumSet = 0.0f;
            for (int i = 0; i != this.getCountSet(); ++i) {
                sumSet += this.collectionSet.get(i).getRepeatNumber() * this.collectionSet.get(i).getWeight();
            }
            return roundFloat(sumSet / this.getCountSet(), numberOfDec);
        }
    }

    /**
     * Get the median value of the weights used during the set
     * @return                  the median value of weights
     */
    float getMedianOfSet() {
        if (this.getCountSet() < 1) {
            return 0;
        } else {
            ArrayList<Integer> sets = new ArrayList<>();
            for (int i = 0; i != this.getCountSet(); ++i) {
                int valueWithoutDec = Math.round(this.collectionSet.get(i).getRepeatNumber() * this.collectionSet.get(i).getWeight() * multiplier);
                sets.add(valueWithoutDec);
            }
            return getMedianOfInteger(sets) / multiplier;
        }
    }

    /**
     * Get the max value of the weights used during the set
     * @return                  the max value of weights
     */
    float getMaxOfSet() {
        if (this.getCountSet() < 1) {
            return 0;
        } else {
            float maxSet = 0.0f;
            for (int i = 0; i != this.getCountSet(); ++i) {
                maxSet = Math.max(this.collectionSet.get(i).getRepeatNumber() * this.collectionSet.get(i).getWeight(), maxSet);
            }
            return maxSet;
        }
    }

    /**
     * Return the median value of an ArrayList of Integer
     * @param object            the collection of integers to which the median value will be extracted
     * @return                  the median value
     */
    private float getMedianOfInteger(ArrayList<Integer> object) {
        Collections.sort(object);
        int countSets = object.size();
        // If the ArrayList has one value, return the unique value
        if (countSets == 1)
            return object.get(0);
        // If the ArrayList has a pair number of value, return the average of N and N+1 value, otherwise return the N+1 value
        int indexOfHalfCollection = object.get(countSets / 2 - 1);
        int indexOfHalfOneMoreCollection = object.get(countSets / 2 + 1 - 1 );
        return (countSets %2 != 0) ? (float)indexOfHalfOneMoreCollection : (float)(indexOfHalfCollection + indexOfHalfOneMoreCollection) / 2;
    }

    /**
     * Round a float by specifying the number of decimal
     * reference : https://stackoverflow.com/a/35833800
     * @param number            the number to round
     * @param scale             the number of decimal
     * @return                  the number round with the number of decimal
     */
    private static float roundFloat(float number, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; ++i)
            pow *= 10;
        float tmp = number * pow;
        return ( (float) ( (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) ) ) / pow;
    }


}
