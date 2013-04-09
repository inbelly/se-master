package ml.generator;

import java.util.Random;

/**
 * Decompiled from walrus-jar...
 * 
 * @author ml
 * 
 */
public class PronounceableGenerator implements PasswordGenerator {
    @Override
    public String generate(int size) {
        Random rnd = new Random();

        String[] consonants = { "b", "c", "d", "f", "g", "h", "j", "k", "l",
                "m", "n", "p", "qu", "r", "s", "t", "v", "w", "x", "z", "ch",
                "cr", "fr", "nd", "ng", "nk", "nt", "ph", "pr", "rd", "sh",
                "sl", "sp", "st", "th", "tr", "dr", "br", "mr", "hr", "dg" };

        String[] vocals = { "a", "e", "i", "o", "u", "y", "io" };

        boolean alternate = true;
        String password = "";
        String chunk = "";
        for (int i = 0; i < size; i++) {
            chunk = alternate ? consonants[rnd.nextInt(consonants.length)]
                    : vocals[rnd.nextInt(vocals.length)];
            alternate = !alternate;
            password = password + chunk;
        }
        return password;
    }
}