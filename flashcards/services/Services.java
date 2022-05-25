package flashcards.services;

import flashcards.entity.Flashcard;
import flashcards.errors.DefinitionAlreadyExists;
import flashcards.errors.TermAlreadyExists;
import flashcards.io.Log;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Services {

    public static Scanner SCANNER = new Scanner(System.in);
    public Map<String, Flashcard> FLASHCARDS = new HashMap<>();


    //checking is term duplicated
    private boolean isTermAlreadyIn(String term) {
        return FLASHCARDS.get(term) == null;
    }

    //checking is definition duplicated
    private boolean isDefinitionAlreadyIn(String definition) {
        return FLASHCARDS.values().stream().anyMatch(s -> s.getDefinition().equals(definition));
    }

    //checking if does new definition match any previous definition
    public Optional<String> isMatchingAnyDefinition(String definition) {
        return FLASHCARDS.entrySet().stream()
                .filter(stringStringEntry -> stringStringEntry.getValue().getDefinition().equals(definition))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    //if the term is valid return term else return -1 and throw a custom exception
    protected String enterTerm() {
        try {
            String term = Log.nextLine();
            if (isTermAlreadyIn(term)) {
                return term;
            }
            throw new TermAlreadyExists(String.format(Msg.THE_TERM_ALREADY_EXISTS, term));
        } catch (TermAlreadyExists e) {
            Log.println(e.getMessage());
            return "-1";
        }
    }

    //if the definition is valid return definition else return -1 and throw custom exception
    protected String enterDefinition() {
        try {
            String definition = Log.nextLine();
            if (!isDefinitionAlreadyIn(definition)) {
                return definition;
            }
            throw new DefinitionAlreadyExists(String.format(Msg.THE_DEFINITION_ALREADY_EXISTS, definition));
        } catch (DefinitionAlreadyExists e) {
            Log.println(e.getMessage());
            return "-1";
        }
    }

    protected int howManyTimesToAsk() {
        String num = null;
        while (true) {
            try {
                Log.println(Msg.NUM_OF_QUESTION);
                num = Log.nextLine();
                return Integer.parseInt(num);
            } catch (Exception e) {
                Log.printf(Msg.NOT_VALID_INPUT, num);
            }
        }
    }

    protected String randomQuestion() {
        Random random = new Random();
        List<String> terms = new ArrayList<>(FLASHCARDS.keySet());
        if (terms.size() > 0) {
            return terms.get(random.nextInt(FLASHCARDS.size()));
        }
        return "-1";
    }

    protected int maxNumOfWrongAnswers() {
        OptionalInt max = FLASHCARDS.values().stream().mapToInt(Flashcard::getWrongAnswers).max();
        if (max.isPresent()) {
            return max.getAsInt();
        }
        return 0;
    }

    protected Set<String> hardestCards(int max) {
        if (max > 0) {
            return FLASHCARDS.values().stream()
                    .filter(flashcard -> flashcard.getWrongAnswers() == max)
                    .map(Flashcard::getTerm)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    protected String formattingHardestCards(int max) {
        Set<String> hardestCards = hardestCards(max);
        StringBuilder sb = new StringBuilder();
        hardestCards.forEach(s -> sb.append("\"").append(s).append("\"").append(", "));
        return sb.substring(0, sb.length() - 2);
    }
}
