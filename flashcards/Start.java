package flashcards;

import flashcards.entity.Flashcard;
import flashcards.io.SaveLoad;
import flashcards.services.Msg;
import flashcards.services.Services;

import java.util.Objects;
import java.util.Set;

import static flashcards.io.Log.*;
import static flashcards.services.Msg.*;

public class Start extends Services {

    private static final String ADD = "add";
    private static final String REMOVE = "remove";
    private static final String ASK = "ask";
    private static final String EXPORT = "export";
    private static final String IMPORT = "import";
    private static final String HARDEST_CARD = "hardest card";
    private static final String RESET_STATS = "reset stats";
    private static final String LOG = "log";
    private static final String EXIT = "exit";
    private static final String CL_IMPORT = "-import";
    private static final String CL_EXPORT = "-export";

    private String clExport;
    private String clImport;

    private final SaveLoad saveLoad = new SaveLoad();

    public void menu(String[] args) {
        argsParser(args);
        if (clImport != null) {
            saveLoad.load(clImport, FLASHCARDS);
        }
        while (true) {
            println(MAIN_MENU);
            String options = nextLine();
            switch (options) {
                case ADD:
                    addCard();
                    break;
                case REMOVE:
                    removeCard();
                    break;
                case ASK:
                    ask();
                    break;
                case EXPORT:
                    save();
                    break;
                case IMPORT:
                    load();
                    break;
                case HARDEST_CARD:
                    hardestCars();
                    break;
                case RESET_STATS:
                    resetStats();
                    break;
                case LOG:
                    log();
                    break;
                case EXIT:
                    if (clExport != null) {
                        saveLoad.save(clExport, FLASHCARDS);
                    }
                    println(BYE);
                    SCANNER.close();
                    System.exit(0);
                default:
                    System.out.println(options + " is wrong input");
            }
        }
    }

    private void addCard() {
        println(THE_CARD);
        String term = enterTerm();
        if (!Objects.equals(term, "-1")) {
            println(DEFINITION_OF_THE_CARD);
            String definition = enterDefinition();
            if (!Objects.equals(definition, "-1")) {
                Flashcard current = new Flashcard(term, definition, 0);
                FLASHCARDS.put(term, current);
                printf(THE_PAIR_HAS_BEEN_ADDED, term, definition);
            }
        }
    }

    private void removeCard() {
        println(WHICH_CARD_TO_REMOVE);
        String card = nextLine();
        if (FLASHCARDS.get(card) != null) {
            FLASHCARDS.remove(card);
            println(THE_CARD_HAS_BEEN_REMOVED);
            return;
        }
        printf(THERE_IS_NO_SUCH_CARD, card);
    }

    private void ask() {
        int numOfTime = howManyTimesToAsk();
        for (int i = 0; i < numOfTime; i++) {
            String randomCard = randomQuestion();
            if (!Objects.equals(randomCard, "-1")) {
                printf(ENTER_DEFINITION, randomCard);
                Flashcard currentFlashcard = FLASHCARDS.get(randomCard);
                String definition = nextLine();
                if (currentFlashcard.getDefinition().equals(definition)) {
                    println(CORRECT);
                    continue;
                }
                if (isMatchingAnyDefinition(definition).isPresent()) {
                    printf(WRONG_BUT, currentFlashcard.getDefinition(),
                            isMatchingAnyDefinition(definition).get());
                    currentFlashcard.setWrongAnswersByOne();
                    continue;
                }

                printf(WRONG, FLASHCARDS.get(randomCard).getDefinition());
                currentFlashcard.setWrongAnswersByOne();
            }
        }
    }

    private void hardestCars() {
        int max = maxNumOfWrongAnswers();
        Set<String> hardest = hardestCards(max);
        if (max > 0 && hardest.size() == 1) {
            String hardestCards = formattingHardestCards(max);
            printf(Msg.HARDEST_CARD, hardestCards, max);
            return;
        }
        if (max > 0 && hardest.size() > 1) {
            String hardestCards = formattingHardestCards(max);
            printf(HARDEST_CARDS, hardestCards, max);
            return;
        }
        println(NO_ERRORS);
    }

    private void resetStats() {
        FLASHCARDS.values().forEach(Flashcard::resetStats);
        println(RESET);
    }

    private void save() {
        println(FILE_NAME);
        String fileName = nextLine();
        saveLoad.save(fileName, FLASHCARDS);
    }

    private void load() {
        println(FILE_NAME);
        String fileName = nextLine();
        saveLoad.load(fileName, FLASHCARDS);
    }

    private void log() {
        println(FILE_NAME);
        String name = nextLine();
        saveLoad.saveLog(name, log);
        println(THE_LOG_HAS_BEEN_SAVED);
    }

    protected void argsParser(String[] args) {
        if (args.length == 0) {
            return;
        }
        for (int i = 0; i < args.length; i++) {
            if (CL_IMPORT.equals(args[i])) {
                clImport = args[i + 1];
            }
            if (CL_EXPORT.equals(args[i])) {
                clExport = args[i + 1];
            }
        }
    }
}
