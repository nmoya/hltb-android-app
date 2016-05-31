package com.nikolasmoya.hltb.parsers;

import org.json.JSONObject;

public abstract class BaseParser<T>
{
    public abstract T parse(JSONObject response);
}
