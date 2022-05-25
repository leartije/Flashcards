package flashcards.entity;

public class Flashcard {

    private final String term;
    private final String definition;
    private int wrongAnswers;

    public Flashcard(String term, String definition, int wrongAnswers) {
        this.term = term;
        this.definition = definition;
        this.wrongAnswers = wrongAnswers;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswersByOne() {
        this.wrongAnswers++;
    }

    public void resetStats() {
        this.wrongAnswers = 0;
    }

    @Override
    public String toString() {
        return term + "|" + definition + "|" + wrongAnswers;
    }

}
