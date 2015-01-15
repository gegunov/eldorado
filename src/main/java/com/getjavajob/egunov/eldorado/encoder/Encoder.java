package com.getjavajob.egunov.eldorado.encoder;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class Encoder {
    private String dstEncoding = "UTF-8";
    private String srcEncoding = "Cp1251";
    private long fileSize = 10 * 1024 * 1024; //10 мб

    public void encode(String fileToRead, String fileToWrite) throws FileNotFoundException {
        String str;
        File readFile = new File(fileToRead);
        if (readFile.exists()) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileToRead), srcEncoding));
                 BufferedWriter bufWriter = new BufferedWriter(
                         new OutputStreamWriter(readFile.length() < fileSize ? (new FileOutputStream(fileToWrite)) :
                                 (new GZIPOutputStream(new FileOutputStream(fileToWrite + ".gz"))), dstEncoding))) {
                while ((str = in.readLine()) != null) {
                    bufWriter.write(str);
                    bufWriter.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new FileNotFoundException();
        }
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getDstEncoding() {
        return dstEncoding;
    }

    public void setDstEncoding(String dstEncoding) {
        this.dstEncoding = dstEncoding;
    }

    public String getSrcEncoding() {
        return srcEncoding;
    }

    public void setSrcEncoding(String srcEncoding) {
        this.srcEncoding = srcEncoding;
    }
}

