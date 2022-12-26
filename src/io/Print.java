package io;

import java.io.FileWriter;
import java.io.IOException;

public class Print {

    private FileWriter writer;

    public Print (String fileName, String output) throws IOException {
        writer = new FileWriter(fileName);
        writer.write(output);
        writer.flush();
    }

}
