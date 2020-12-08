package flashcards;

public class FlashCard {

    private static final String CARD = "Card:";
    private static final String DEFINITION = "Definition:";
    private final String card;
    private final String definition;
    private int attempts;

    public FlashCard(String card, String definition) {
        this.card = card;
        this.definition = definition;
    }

    public String getCard() {
        return card;
    }

    public String getDefinition() {
        return definition;
    }

    public boolean isCorrect(String answer) {
        if (definition.equals(answer)) {
            return true;
        } else {
            attempts += 1;
            return false;
        }
    }

    public void resetAttempts() {
        attempts = 0;
    }

    @Override
    public String toString() {
        return CARD + "\n" + card + "\n" + DEFINITION + "\n" + definition;
    }
}
