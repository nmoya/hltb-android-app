package com.nikolasmoya.hltb.legacy;

import org.jsoup.nodes.Document;

public interface HttpRequestListener
{
    void onHttpResponse(Document response);
}
