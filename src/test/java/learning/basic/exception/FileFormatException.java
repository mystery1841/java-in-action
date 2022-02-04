package learning.basic.exception;

import java.io.IOException;

public class FileFormatException extends IOException {
    public FileFormatException() {}
    public FileFormatException(String gripe) {
        super(gripe);
    }

}
