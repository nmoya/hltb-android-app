package com.nikolasmoya.hltb.parsers;

import com.nikolasmoya.hltb.constants.Environment;
import com.nikolasmoya.hltb.models.HltbItem;
import com.nikolasmoya.hltb.timeToBeat.Game;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HltbParser extends BaseParser<HltbItem>
{
    @Override
    public HltbItem parse(Document doc)
    {
        ArrayList<Game> games = new ArrayList<>();

        String imgSrc, title;

        int id = 0;
        Elements links = doc.select("a");
        for (Element l : links)
        {
            Elements coverImgs = l.select("img");
            if (coverImgs.size() > 0)
            {
                title = l.attr("title");
                imgSrc = Environment.HLTB_BASE_URL + coverImgs.attr("src");
                games.add(new Game(id++, imgSrc, title));
            }
        }

        int index = 0;
        Elements stats = doc.select(".search_list_details_block");
        for (Element s : stats)
        {
            Game currGame = games.get(index++);
            Elements timeStats = s.select(".search_list_tidbit");
            int counter = 0;
            for (Element time : timeStats)
            {
                if (counter % 2 == 0)
                {
                    counter++;
                    continue;
                }
                int confidence = Integer.valueOf(time.attr("class").replace("search_list_tidbit center time_", ""));
                currGame.setConfidence(confidence);
                String _time = time.text();
                switch (counter)
                {
                    case 1:
                        currGame.setMainStory(_time);
                        break;
                    case 3:
                        currGame.setMainExtra(_time);
                        break;
                    case 5:
                        currGame.setCompletionist(_time);
                        break;
                    case 7:
                        currGame.setCombined(_time);
                        break;
                }
                counter++;
            }
        }
        if (games.size() > 0)
        {
            Game g = games.get(0);
            return new HltbItem(g.getId(), g.getImgSrc(), g.getTitle(), g.getMainStory());
        }
        return null;
    }
}
