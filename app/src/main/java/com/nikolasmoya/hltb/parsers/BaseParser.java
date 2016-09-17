package com.nikolasmoya.hltb.parsers;

import org.json.JSONObject;
import org.jsoup.nodes.Document;

public abstract class BaseParser<T>
{
    public T parse(JSONObject response)
    {
        throw new UnsupportedOperationException("method not overridden");
    }

    public T parse(Document response)
    {
        throw new UnsupportedOperationException("method not overridden");
    }
}
