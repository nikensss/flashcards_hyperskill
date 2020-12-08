package flashcards;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FlashCardService {

    private final Map<String, String> cards = new LinkedHashMap<>();

    private final Map<String, Integer> attempts = new LinkedHashMap<>();

    private final Scanner scanner = new Scanner(System.in);

    private final List<String> log = new ArrayList<>();

    public void init() {
        cards.clear();
        log.add("");
    }

//
//    public void fillFlashCards() {
//        println("Input the number of cards:");
//
//        int numberOfCards = Integer.parseInt(scanner.nextLine());
//        logInput(numberOfCards);
//
//        for (int i = 0; i < numberOfCards; i++) {
//            String card = "", definition = "";
//            int attempts = 0;
//            println("The card #" + (i + 1));
//
//            do {
//                if (attempts > 0) {
//                    println("The card \"" + card + "\" already exists. Try again:");
//                }
//                card = scanner.nextLine();
//                logInput(card);
//
//                attempts += 1;
//            } while (cards.containsKey(card));
//
//            attempts = 0;
//            println("The definition of the card #" + (i + 1));
//            do {
//                if (attempts > 0) {
//                    println("The definition \"" + definition + "\" already exists. Try again:");
//                }
//                definition = scanner.nextLine();
//                logInput(definition);
//                attempts += 1;
//            } while (cards.containsValue(definition));
//
//            cards.put(card, definition);
//        }
//    }
//
//    public void requestDefinitions() {
//        Scanner scanner = new Scanner(System.in);
//
//        for (Map.Entry<String, String> e : cards.entrySet()) {
//            println("Print the definition of \"" + e.getKey() + "\"");
//            String inputDefinition = scanner.nextLine();
//            logInput(inputDefinition);
//            if (e.getValue().equals(inputDefinition)) {
//                println("Correct answer");
//                continue;
//            }
//
//            try {
//                String solutionOf = getCardWithDefinition(inputDefinition);
//                println("Wrong answer. The correct one is \"" + e.getValue() + "\"," +
//                        " you've just written the definition of \"" + solutionOf
//                        + "\"");
//            } catch (IllegalStateException ex) {
//                println("Wrong answer. The correct one is \"" + e.getValue() + "\"");
//            }
//        }
//    }

    private Map.Entry<String, String> getRandomEntry() {
        int rand = new Random().nextInt(cards.entrySet().size());
        int i = 0;
        for (Map.Entry<String, String> e : cards.entrySet()) {
            if (i == rand) {
                return e;
            }
            i += 1;
        }
        throw new IllegalStateException("we shouldn't be here...");
    }

    private String getCardWithDefinition(String definition) {
        for (Map.Entry<String, String> e : cards.entrySet()) {
            if (e.getValue().equals(definition)) {
                return e.getKey();
            }
        }

        throw new IllegalStateException("Unknown definition: " + definition);
    }

    public void add() {
        String card, definition;

        println("The card:");
        card = scanner.nextLine();
        logInput(card);
        if (cards.containsKey(card)) {
            println("The card \"" + card + "\" already exists.");
            return;
        }

        println("The definition of the card:");
        definition = scanner.nextLine();
        logInput(definition);
        if (cards.containsValue(definition)) {
            println("The definition \"" + definition + "\" already exists.");
            return;
        }

        cards.put(card, definition);
        attempts.put(card, 0);
        println("The pair (\"" + card + "\":\"" + definition + "\") has been added");
    }

    public void remove() {
        println("Which card?");
        String card = scanner.nextLine();
        logInput(card);
        String definition = cards.remove(card);

        if (definition == null) {
            println("can't remove \"" + card + "\": there is no such card.");
            return;
        }
        attempts.remove(card);
        println("The card has been removed.");
    }

    public void importCards() {
        println("File name:");
        String fileName = scanner.nextLine();
        logInput(fileName);
        importCards(fileName);
    }

    public void importCards(String fileName) {
        File cardsFile = new java.io.File(System.getProperty("user.dir") + File.separator + fileName);
        if (!cardsFile.exists()) {
            println("File not found");
            return;
        }
        try (Scanner fileReader = new Scanner(cardsFile)) {
            int readCards = 0;
            while (fileReader.hasNext()) {
                String line = fileReader.nextLine();
                String[] cardWithDefinitionAndAttempts = line.split(":");
                if (cardWithDefinitionAndAttempts.length != 3) {
                    continue;
                }
                cards.put(cardWithDefinitionAndAttempts[0], cardWithDefinitionAndAttempts[1]);
                attempts.put(cardWithDefinitionAndAttempts[0], Integer.parseInt(cardWithDefinitionAndAttempts[2]));
                readCards += 1;
            }

            println(readCards + " cards have been loaded.");
        } catch (FileNotFoundException e) {
            println("File not found");
        }
    }

    public void exportCards() {
        println("File name:");
        String fileName = scanner.nextLine();
        logInput(fileName);
        exportCards(fileName);
    }

    public void exportCards(String fileName) {
        File cardsFile = new java.io.File(System.getProperty("user.dir") + File.separator + fileName);
        try (PrintWriter pw = new PrintWriter(new FileWriter(cardsFile), false)) {
            for (String key : cards.keySet()) {
                pw.printf("%s:%s:%d\n", key, cards.get(key), attempts.get(key));
            }
            println(cards.keySet().size() + " cards have been saved");
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void ask() {
        println("How many times to ask?");
        int questions = Integer.parseInt(scanner.nextLine());
        logInput(questions);

//        Set<Map.Entry<String, String>> entries = cards.entrySet();

        for (int i = 0; i < questions; i++) {
            Map.Entry<String, String> e = getRandomEntry();
            println("Print the definition of \"" + e.getKey() + "\"");
            String inputDefinition = scanner.nextLine();
            logInput(inputDefinition);
            if (e.getValue().equals(inputDefinition)) {
                println("Correct!");
            } else {
                try {
                    String solutionOf = getCardWithDefinition(inputDefinition);
                    println("Wrong. The right answer is \"" + e.getValue() + "\"," +
                            " but your definition is correct for \"" + solutionOf
                            + "\"");
                } catch (IllegalStateException ex) {
                    println("Wrong. The right answer is \"" + e.getValue() + "\"");
                } finally {
                    attempts.put(e.getKey(), attempts.get(e.getKey()) + 1);
                }
            }
        }
    }

    public void saveLog() {
        println("File name:");
        String logFile = scanner.nextLine();
        logInput(logFile);
        saveLog(logFile);
    }

    public void saveLog(String fileName) {
        File logFile = new java.io.File(System.getProperty("user.dir") + File.separator + fileName);
        try (PrintWriter pw = new PrintWriter(new FileWriter(logFile), false)) {
            for (String line : log) {
                pw.printf("%s\n", line);
            }
            System.out.println("The log has been saved");
            pw.print("The log has been saved");
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void printHardestCard() {
        int mostAttempts = 0;
        List<String> hardestCards = new ArrayList<>();
        for (String key : attempts.keySet()) {
            if (attempts.get(key) < mostAttempts) {
                continue;
            }

            if (attempts.get(key) == mostAttempts && mostAttempts != 0) {
                hardestCards.add(key);
                continue;
            }

            if (attempts.get(key) > mostAttempts) {
                mostAttempts = attempts.get(key);
                hardestCards.clear();
                hardestCards.add(key);
            }
        }

        if (hardestCards.size() == 0) {
            println("There are no cards with errors.");
        }

        if (hardestCards.size() == 1) {
            String card = hardestCards.get(0);
            println("The hardest card is \"" + card + "\". You have " + mostAttempts + " errors answering it.");
        }

        if (hardestCards.size() > 1) {
            String cards = hardestCards
                    .stream()
                    .map(c -> "\"" + c + "\"")
                    .collect(Collectors.toList())
                    .toString()
                    .substring(1);
            cards = cards.substring(0, cards.length() - 1);
            println("The hardest cards are " + cards + ". You have " + mostAttempts + " errors answering them.");
        }
    }

    public void resetStats() {
        attempts.replaceAll((k, v) -> 0);
        println("Card statistics have been reset.");
    }

    public List<String> getLog() {
        return log;
    }

    private void println(String message) {
        log.add(message);
        System.out.println(message);
    }

    private void logInput(int n) {
        logInput("" + n);
    }

    private void logInput(String message) {
        log.add(message);
    }

}
