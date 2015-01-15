package com.getjavajob.egunov.eldorado.cache;

import java.util.Scanner;

public class CacheRunner {
    public static void main(String[] args) {
        Cache cache = new Cache();
        Scanner scanner = new Scanner(System.in);
        String message;
        System.out.println("Вводите сообщения. Для выхода введите \"end\"");
        while (!(message = scanner.nextLine()).equals("end")) {
            if (cache.getMessage(message) != null) {
                System.out.println("Сообщение взято из кеша");
            } else {
                cache.addMessage(message);
                System.out.println("Сообщение добавлено в кеш");
            }
        }
        System.out.println("Спасибо за тест!");
    }
}
