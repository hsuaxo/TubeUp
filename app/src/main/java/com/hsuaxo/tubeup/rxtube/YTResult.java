package com.hsuaxo.tubeup.rxtube;

import java.util.ArrayList;
import java.util.List;

public class YTResult {

    public List<YTContent> items;

    public static YTResult empty() {
        final YTResult result = new YTResult();
        result.items = new ArrayList<>();
        return result;
    }
}
