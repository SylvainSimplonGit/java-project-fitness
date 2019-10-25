class Set {
    /**
     * The name of exercise
     */
    private String exercise;
    /**
     * The number of repetitions of the exercise
     */
    private int repeatNumber;
    /**
     * The weight used in the exercise
     */
    private float weight;


    Set(String exercise, int repeatNumber, float weight) {
        this.exercise = exercise;
        this.repeatNumber = repeatNumber;
        this.weight = weight;
    }

    /**
     * Get the number of repetitions of the set
     * @return                  the number of repetitions of the exercise
     */
    int getRepeatNumber() {
        return repeatNumber;
    }

    /**
     * Get the name of exercise of the set
     * @return                  the name of exercise
     */
    String getExercise() {
        return exercise;
    }

    /**
     * Get the weight of the set
     * @return                  the weight used in the exercise
     */
    float getWeight() {
        return weight;
    }

}
