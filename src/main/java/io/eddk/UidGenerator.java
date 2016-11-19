package io.eddk;

import java.io.BufferedInputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UidGenerator {

    private static final Pattern VALID_PATTERN = Pattern
            .compile("(facebook|google|local),(initech|initrode),[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE);
    private static final String ERROR = "error";
    private static final String DELIMITER = ",";
    private static final String LINE_FEED = System.getProperty("line.separator");

    public static void main(String[] args) {

        System.out.println("please enter your lines, press enter for next line, blank line to stop:");

        UidGenerator uidGenerator = new UidGenerator();

        StringBuffer buffer = new StringBuffer();
        uidGenerator.start(buffer);
        System.out.println("***RESULT***");
        System.out.println(buffer);
    }

    public void start(StringBuffer buffer) {
        Scanner stdin = new Scanner(new BufferedInputStream(System.in));
        while (stdin.hasNextLine()) {
            String nextLine = stdin.nextLine();
            if (nextLine.isEmpty()) {
                break;
            }
            try {
                // alternatively can process the whole input
                buffer.append(process(nextLine)).append(LINE_FEED);
            } catch (NoSuchAlgorithmException ex) {
                // log some errors
                break;
            }
        }
    }

    public String process(String inputLine) throws NoSuchAlgorithmException {

        if (!isValidFormat(inputLine)) {
            return ERROR;
        }
        return inputLine.concat(DELIMITER).concat(createUniqueUserId(inputLine));
    }

    public String createUniqueUserId(String inputLine) throws NoSuchAlgorithmException {

        String[] tokens = inputLine.split(DELIMITER);
        String sanitizedInputLine = tokens[0].toLowerCase().concat(DELIMITER).concat(tokens[1].toLowerCase())
                .concat(DELIMITER).concat(tokens[2]);
        return HashUtils.sha1(sanitizedInputLine);

    }

    public boolean isValidFormat(String inputLine) {
        if (inputLine == null || inputLine.isEmpty()) {
            return false;
        }
        if (!VALID_PATTERN.matcher(inputLine).matches()) {
            return false;
        }
        return true;
    }
}
