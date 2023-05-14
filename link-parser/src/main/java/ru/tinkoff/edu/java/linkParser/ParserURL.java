package ru.tinkoff.edu.java.linkParser;

import ru.tinkoff.edu.java.linkParser.parsers.AbstractParser;
import ru.tinkoff.edu.java.linkParser.parsers.GitHubParser;
import ru.tinkoff.edu.java.linkParser.parsers.StackOverflowParser;
import ru.tinkoff.edu.java.linkParser.records.Result;

public class ParserURL {
    AbstractParser parserStack = new StackOverflowParser();
    AbstractParser parserGit = new GitHubParser();
    AbstractParser abstractParser = AbstractParser.link(parserGit, parserStack);

    public Result parse(String url){
        return abstractParser.parse(url);
    }
}
