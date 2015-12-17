package com.nikolasmoya.hltb;

import org.jsoup.nodes.Document;

public interface HttpRequestListener
{
    void onHttpResponse(Document response);
}
