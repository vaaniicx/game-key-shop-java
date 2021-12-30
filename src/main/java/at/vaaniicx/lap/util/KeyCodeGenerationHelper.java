package at.vaaniicx.lap.util;

import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Component;

@Component
public class KeyCodeGenerationHelper {

    private static final byte FRAGMENT_LENGTH = 5;
    private static final byte FRAGMENTS = 5;
    private static final char SEPARATOR = '-';

    public static String generateKeyCode() {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < FRAGMENTS; i++) {
            builder.append(RandomString.make(FRAGMENT_LENGTH));

            if (i != FRAGMENTS - 1) {
                builder.append(SEPARATOR);
            }
        }
        
        return builder.toString();
    }
}
