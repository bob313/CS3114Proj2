import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author cdc97 bob313
 * @version 9.5.18
 *          Processes command file and feeds instructions Hash and
 *          MemoryManager.
 * 
 */

public class CommandProcessor {
    private Hash movieHash;
    private Hash reviewHash;
    private SparseMatrix matrix;


    /**
     * 
     * Constructor for CommandProcessor. Takes inputs from main method and
     * processes the commands.
     * 
     * @param hashSize
     *            size of Hash received from main method
     * 
     * @param file
     *            the input file containing the commands to be read and
     *            processed
     * 
     */
    public CommandProcessor(String hashSize, String file) {
        reviewHash = new Hash(Integer.valueOf(hashSize));
        movieHash = new Hash(Integer.valueOf(hashSize));
        matrix = new SparseMatrix();
        Scanner scan = null;
        try {
            scan = new Scanner(new File(file));
        }
        catch (FileNotFoundException e) {
            System.out.println("Command File Not Found");
            e.printStackTrace();
        }
        while (scan.hasNextLine()) {
            processCommand(scan.nextLine());
        }
    }


    /**
     * 
     * Processes a given line of the command file for the command found in the
     * line
     * 
     * @param commandString
     *            the line of the command file being processed
     * 
     * @return true if proper command present false if not
     * 
     */
    private boolean processCommand(String commandString) {
        // Analyze the input string and figure out which command
        String[] inputs = commandString.trim().split("\\s+");
        if (inputs[0].equals("add")) {
            add(commandString);
            matrix.listAdd(commandString);
            return true;
        }
        if (inputs[0].equals("delete")) {
            if (inputs[1].equals("reviewer")) {
                commandString = commandString.replaceFirst("reviewer", "");
                delete(commandString, reviewHash, "Reviewer");
            }
            else {
                commandString = commandString.replaceFirst("movie", "");
                delete(commandString, movieHash, "Movie");
            }
            return true;
        }
        if (inputs[0].equals("print")) {
            print(commandString);
            return true;
        }
        if (inputs[0].equals("list")) {
            printList(commandString.replaceFirst("list", ""));
        }
        if (inputs[0].equals("similar")) {
            printSimilar(commandString.replaceFirst("similar", ""));
        }
        return false;
    }


    /**
     * 
     * @param inputs
     *            is the string inputs
     *            prints the list
     */
    public void printList(String inputs) {
        inputs = inputs.trim();
        String[] base = inputs.split("\\s+");
        String name = base[1];
        for (int i = 2; i < base.length; i++) {
            name = name + " " + base[i].trim();
        }
        matrix.list(base[0], name);
    }


    /**
     * 
     * @param inputs
     *            is the string inputs
     *            prints the similar movies/reviewers
     */
    public void printSimilar(String inputs) {
        inputs = inputs.trim();
        String[] base = inputs.split("\\s+");
        inputs.replace(base[0], "");
        String name = base[1];
        for (int i = 2; i < base.length; i++) {
            name = name + " " + base[i].trim();
        }
        if (base[0].equals("movie")) {
            System.out.println("There is no movie similar to |" + name + "|");
        }
        else {
            System.out.println("There is no reviewer similar to |" + "|");
        }
    }


    /**
     * handles the print commands
     * 
     * @param printCommand
     *            print command string
     */
    private void print(String printCommand) {
        String[] inputs = printCommand.trim().split("\\s+");
        if (inputs[1].equals("ratings")) {
            matrix.print();
        }
        else if (inputs[2].equals("reviewer")) {
            System.out.println("Reviewers:");
            reviewHash.print();
        }
        else if (inputs[2].equals("movie")) {
            System.out.println("Movies:");
            movieHash.print();
        }
    }


    /**
     * Handles the add command
     * 
     * @param addCommand
     *            add command string
     */
    private void add(String addCommand) {
        String name = addCommand.replaceFirst("add", "");
        name = formatString(name);
        String[] key = name.split("<SEP>");
        boolean mcheck = movieHash.search(key[1].trim());
        boolean rcheck = reviewHash.search(key[0].trim());
        if (Integer.valueOf(key[2]) > 0 && Integer.valueOf(key[2]) < 11) {
            if (!rcheck) {
                Handle rhandle = new Handle(10, 19, key[0].trim());
                rhandle.setRecord(name.trim());
                if (reviewHash.getCount() >= reviewHash.getHashtable().length
                    / 2) {
                    System.out.println("Reviewer hash table size doubled to "
                        + reviewHash.getHashtable().length * 2 + " slots.");
                }
                reviewHash.add(key[0].trim(), rhandle);
            }
            if (!mcheck) {
                Handle mhandle = new Handle(10, 19, key[1].trim());
                mhandle.setRecord(name.trim());
                if (movieHash.getCount() >= movieHash.getHashtable().length
                    / 2) {
                    System.out.println("Movie hash table size doubled to "
                        + movieHash.getHashtable().length * 2 + " slots.");
                }
                movieHash.add(key[1].trim(), mhandle);
            }
            System.out.println("Rating added: |" + key[0] + "|, |" + key[1]
                + "|, " + key[2]);
        }
        else {
            System.out.println("Bad score |" + key[2]
                + "|. Scores must be between 1 and 10.");
        }
    }


    /**
     * Handles the delete command
     * 
     * @param deleteCommand
     *            delete command string
     */
    private void delete(String deleteCommand, Hash hash, String str) {
        String name;
        name = deleteCommand.replaceFirst("delete", "");
        name = formatString(name);
        String[] key = name.split("<SEP>");
        boolean check = hash.remove(key[0]);
        if (check) {
            System.out.println("|" + key[0] + "| has been deleted from the "
                + str + " database.");
        }
        else {
            System.out.println("|" + key[0] + "| not deleted because it does"
                + " not exist in the " + str + " database.");
        }
    }


    /**
     * Formats input strings to remove excess spaces and command words (i.e.
     * update, add, delete)
     * 
     * @param nameString
     *            unformatted string
     * @return formatted string
     */
    private String formatString(String nameString) {
        StringBuilder newString = new StringBuilder();
        char[] chars = nameString.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!Character.isWhitespace(chars[i])) {
                while (i < chars.length && !Character.isWhitespace(chars[i])) {
                    newString.append(chars[i]);
                    i++;
                }
                newString.append(" ");
            }
        }
        newString.deleteCharAt(newString.length() - 1);
        return newString.toString();
    }


    /**
     * gets the Hash of commandP
     * 
     * @param name
     *            of hash
     * @return CommandP's hash
     */
    public Hash getHash(String name) {
        if (name.equals("movie")) {
            return movieHash;
        }
        else if (name.equals("review")) {
            return reviewHash;
        }
        return null;
    }

}
