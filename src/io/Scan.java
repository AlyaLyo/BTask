package io;

import java.io.FileReader;
import java.io.IOException;

public class Scan {

    private FileReader reader;
    private boolean eof = false;

    public Scan (String fileName) throws IOException {
        reader = new FileReader(fileName);
        if (reader.read() == -1) {
            this.eof = true;
        }
        reader = new FileReader(fileName);
    }

}
