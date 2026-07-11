package com.mycompany.quanlygara.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;

public class LuongNhapKhongDau extends InputStream {
    private final InputStream original;
    private byte[] buffer = new byte[0];
    private int bufferIndex = 0;

    public LuongNhapKhongDau(InputStream original) {
        this.original = original;
    }

    @Override
    public int read() throws IOException {
        if (bufferIndex >= buffer.length) {
            if (fillBuffer() == -1) {
                return -1;
            }
        }
        return buffer[bufferIndex++] & 0xFF;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        if (bufferIndex >= buffer.length) {
            if (fillBuffer() == -1) {
                return -1;
            }
        }

        int available = buffer.length - bufferIndex;
        int bytesToRead = Math.min(len, available);
        System.arraycopy(buffer, bufferIndex, b, off, bytesToRead);
        bufferIndex += bytesToRead;
        return bytesToRead;
    }

    private int fillBuffer() throws IOException {
        ByteArrayOutputStream lineBytes = new ByteArrayOutputStream();
        while (true) {
            int b = original.read();
            if (b == -1) {
                if (lineBytes.size() == 0) {
                    return -1;
                }
                break;
            }
            lineBytes.write(b);
            if (b == '\n') {
                break;
            }
        }

        byte[] bytes = lineBytes.toByteArray();
        String lineStr = new String(bytes);

        
        String unaccentedStr = TienIchChuoi.removeAccents(lineStr);
        buffer = unaccentedStr.getBytes();
        bufferIndex = 0;
        return buffer.length;
    }
}
