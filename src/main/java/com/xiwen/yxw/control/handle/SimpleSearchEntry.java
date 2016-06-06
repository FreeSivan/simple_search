package com.xiwen.yxw.control.handle;

import com.xiwen.yxw.domain.RequestBean;
import com.xiwen.yxw.domain.ResponseBean;
import com.xiwen.yxw.search.SearchIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/search")
public class SimpleSearchEntry {

    @Autowired
    private SearchIndex searchIndex;

    @RequestMapping("/main")
    public String searchMainPage() {
        return "search/main";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView search(RequestBean searchBean) {
        try {
            ResponseBean bean = searchIndex.searchIndex(searchBean);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("search/result");
            mav.addObject("search", bean);
            return mav;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
