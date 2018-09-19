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


    /**
     * 
     * Constructor for CommandProcessor. Takes inputs from main method and
     * processes the commands.
     * 
     * @param memorySize
     *            size of MemoryManager received from main method
     * 
     * @param hashSize
     *            size of Hash received from main method
     * 
     * @param file
     *            the input file containing the commands to be read and
     *            processed
     * 
     */
    public CommandProcessor(String memorySize, String hashSize, String file) {
        reviewHash = new Hash(Integer.valueOf(hashSize));
        movieHash = new Hash(Integer.valueOf(hashSize));
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
            if (inputs[1].equals("reviewer")) {
                commandString = commandString.replaceFirst("reviewer", "");
                add(commandString, reviewHash);
            }
            else {
                commandString = commandString.replaceFirst("movie", "");
                add(commandString, movieHash);
            }
            return true;
        }
        if (inputs[0].equals("delete")) {
            if (inputs[1].equals("reviewer")) {
                commandString = commandString.replaceFirst("reviewer", "");
                delete(commandString, reviewHash);
            }
            else {
                commandString = commandString.replaceFirst("movie", "");
                delete(commandString, movieHash);
            }
            return true;
        }
        if (inputs[0].equals("print")) {
            print(commandString);
            return true;
        }
        return false;
    }


    /**
     * handles the print commands
     * 
     * @param printCommand
     *            print command string
     */
    private void print(String printCommand) {
        reviewHash.print();
        movieHash.print();
    }


    /**
     * Handles the add command
     * 
     * @param addCommand
     *            add command string
     */
    private void add(String addCommand, Hash hash) {
        String name = addCommand.replaceFirst("add", "");
        name = formatString(name);
        boolean check = hash.search(name.trim());
        if (!check) {
            Handle handle = new Handle(10, 19, name);
            hash.add(name, handle);
            System.out.println("|" + name
                + "| has been added to the Name database.");
        }
        else {
            System.out.println("|" + name
                + "| duplicates a record already in the Name database.");
        }
    }


    /**
     * Handles the delete command
     * 
     * @param deleteCommand
     *            delete command string
     */
    private void delete(String deleteCommand, Hash hash) {
        String name;
        name = deleteCommand.replaceFirst("delete", "");
        name = formatString(name);
        boolean check = hash.remove(name);
        if (check) {
            System.out.println("|" + name
                + "| has been deleted from the Name database.");
        }
        else {
            System.out.println("|" + name + "| not deleted because it does"
                + " not exist in the Name database.");
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
