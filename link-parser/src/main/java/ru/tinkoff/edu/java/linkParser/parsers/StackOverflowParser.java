package ru.tinkoff.edu.java.linkParser.parsers;

import ru.tinkoff.edu.java.linkParser.records.Result;
import ru.tinkoff.edu.java.linkParser.records.StackOverFlowRecord;

public class StackOverflowParser extends AbstractParser{

    @Override
    public Result parse(String url) {
        url = parseUrl(url);
        if(url == null){
            return null;
        }
        String[] dom = url.split("/");
        if(dom.length == 4){
            if(dom[0].equals("stackoverflow.com") && dom[1].equals("questions")){
                try {
                    return new StackOverFlowRecord(Long.parseLong(dom[2]));
                } catch (NumberFormatException e) {
                    return checkNext(url);
                }
            }
        }
        return checkNext(url);
    }
}
