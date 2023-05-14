package ru.tinkoff.edu.java.linkParser;

import ru.tinkoff.edu.java.linkParser.parsers.AbstractParser;
import ru.tinkoff.edu.java.linkParser.parsers.GitHubParser;
import ru.tinkoff.edu.java.linkParser.parsers.StackOverflowParser;
import ru.tinkoff.edu.java.linkParser.records.Result;

public class ParserURL {
    public Result parse(String url){
        AbstractParser parserStack = new StackOverflowParser();
        AbstractParser parserGit = new GitHubParser();
        return AbstractParser.link(parserGit, parserStack).parse(url);
    }
}
