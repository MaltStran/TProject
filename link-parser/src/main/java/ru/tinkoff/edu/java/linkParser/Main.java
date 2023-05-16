package ru.tinkoff.edu.java.linkParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkParser githubParser = new GithubParser();
        LinkParser stackOverflowParser = new StackOverflowParser();
        githubParser.setNextParser(stackOverflowParser);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите ссылку или нажмите Enter для выхода:");
            String link = scanner.nextLine().trim();

            if (link.isEmpty()) {
                break;
            }

            String[] result = githubParser.parse(link);

            if (result != null) {
                System.out.println("Результат парсинга: " + String.join("  ", result));
            } else {
                System.out.println("Ссылка не поддерживается");
            }
        }
    }
}
