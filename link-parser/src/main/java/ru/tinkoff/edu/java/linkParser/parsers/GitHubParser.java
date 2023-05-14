package ru.tinkoff.edu.java.linkParser.parsers;

import ru.tinkoff.edu.java.linkParser.records.GitHubRecord;
import ru.tinkoff.edu.java.linkParser.records.Result;

public class GitHubParser extends AbstractParser{

    @Override
    public Result parse(String url) {
        url = parseUrl(url);
        if(url == null){
            return null;
        }
        String[] dom = url.split("/");
        if(dom.length == 4 || dom.length == 3){
            if(dom[0].equals("github.com")){
                return new GitHubRecord(dom[1], dom[2]);
            }
        }
        return checkNext(url);
    }
}
