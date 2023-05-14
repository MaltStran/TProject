package ru.tinkoff.edu.java.linkParser.parsers;

import ru.tinkoff.edu.java.linkParser.records.Result;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractParser {
    private AbstractParser nextParser;


    public static AbstractParser link(AbstractParser first, AbstractParser... chain){
        AbstractParser head = first;
        for (AbstractParser nextInChain: chain) {
            head.nextParser = nextInChain;
            head = nextInChain;
        }
        return first;
    }


    public abstract Result parse(String url);


    protected Result checkNext(String url){
        if(nextParser == null){
            return null;
        }
        return nextParser.parse(url);
    }


    public String parseUrl(String stringUrl){
        try {
            URL url = new URL(stringUrl);
            return url.getHost() + url.getPath();
        } catch (MalformedURLException e) {
            return stringUrl;
        }
    }
}
