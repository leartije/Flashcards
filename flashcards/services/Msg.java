package flashcards.services;

public class Msg {

    public static String THE_CARD = "The card:";
    public static String DEFINITION_OF_THE_CARD = "The definition of the card:";
    public static String ENTER_DEFINITION = "Print the definition of \"%s\":%n";
    public static String CORRECT = "Correct!";
    public static String WRONG = "Wrong. The right answer is \"%s\".%n";

    public static String WRONG_BUT = "Wrong. The right answer is \"%s\", " +
            "but your definition is correct for \"%s\".%n";

    public static String THE_TERM_ALREADY_EXISTS = "The card \"%s\" already exists.";
    public static String THE_DEFINITION_ALREADY_EXISTS = "The definition \"%s\" already exists.";

    public static String WHICH_CARD_TO_REMOVE = "Which card?";
    public static String MAIN_MENU = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):";

    public static String THE_CARD_HAS_BEEN_REMOVED = "The card has been removed.";
    public static String THERE_IS_NO_SUCH_CARD = "Can't remove \"%s\": there is no such card.%n";
    public static String HARDEST_CARD = "The hardest card is %s. You have %d errors answering it.%n";
    public static String HARDEST_CARDS = "The hardest cards are %s. You have %d errors answering them.%n";
    public static String NO_ERRORS = "There are no cards with errors.";
    public static String RESET = "Card statistics have been reset.";
    public static String FILE_NAME = "File name:";
    public static String THE_PAIR_HAS_BEEN_ADDED = "The pair (\"%s\":\"%s\") has been added.%n";
    public static String THE_LOG_HAS_BEEN_SAVED = "The log has been saved.";
    public static String BYE = "Bye, bye!";
}
