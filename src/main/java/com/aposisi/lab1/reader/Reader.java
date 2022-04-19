package com.aposisi.lab1.reader;

import java.io.IOException;
import java.io.InputStream;

public interface Reader {
    byte[] read(InputStream inputStream) throws IOException;
}
