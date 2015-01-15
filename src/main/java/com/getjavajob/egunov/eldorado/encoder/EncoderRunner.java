package com.getjavajob.egunov.eldorado.encoder;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class EncoderRunner {

    public static void main(String[] args) {

        Encoder encoder = new Encoder();
        System.out.println("Enter reading file path");
        Scanner scanner = new Scanner(System.in);
        String readFilePath = scanner.nextLine();
        System.out.println("Enter result path");
        String writeFilePath = scanner.nextLine();
        try {
            encoder.encode(readFilePath, writeFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
