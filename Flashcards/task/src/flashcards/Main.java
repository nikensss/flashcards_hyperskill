package flashcards;

import java.util.Scanner;

public class Main {
    private static final FlashCardService flashCardService = new FlashCardService();

    public static void main(String[] args) {
        Action[] actions = parseActions(args);
        Action importAction = null, exportAction = null;
        for (Action a : actions) {
            if (a.isImport()) {
                importAction = a;
            } else if (a.isExport()) {
                exportAction = a;
            }
        }
        flashCardService.init();
        if (importAction != null) {
            flashCardService.importCards(importAction.getTarget());
        }
        String action;
        do {
            action = getAction();
            flashCardService.getLog().add(action);
            processAction(action, exportAction);
        } while (!action.equals("exit"));
    }

    private static Action[] parseActions(String... args) {
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("Input arguments not multiple of 2!");
        }

        Action[] actions = new Action[args.length / 2];
        for (int i = 0; i < args.length; i += 2) {
            actions[i / 2] = new Action(args[i], args[i + 1]);
        }

        return actions;
    }

    private static void processAction(String action, Action exportAction) {
        switch (action) {
            case "add":
                flashCardService.add();
                break;
            case "remove":
                flashCardService.remove();
                break;
            case "import":
                flashCardService.importCards();
                break;
            case "export":
                flashCardService.exportCards();
                break;
            case "ask":
                flashCardService.ask();
                break;
            case "exit":
                System.out.println("Bye bye!");
                if (exportAction != null) {
                    flashCardService.exportCards(exportAction.getTarget());
                }
                break;
            case "log":
                flashCardService.saveLog();
                break;
            case "hardest card":
                flashCardService.printHardestCard();
                break;
            case "reset stats":
                flashCardService.resetStats();
                break;
            default:
                System.out.println("Not an action. Try again...");
                break;
        }
    }

    private static String getAction() {
        Scanner scanner = new Scanner(System.in);
        final String inputTheAction = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):";
        System.out.println(inputTheAction);
        flashCardService.getLog().add(inputTheAction);
        return scanner.nextLine();
    }

}

class Action {
    private static final String IMPORT = "-import";
    private static final String EXPORT = "-export";

    private final String action;
    private final String target;

    Action(String action, String target) {
        this.action = action;
        this.target = target;
    }

    public boolean isImport() {
        return action.equals(IMPORT);
    }

    public boolean isExport() {
        return action.equals(EXPORT);
    }

    public String getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "action='" + action + "', target='" + target + "'";
    }
}