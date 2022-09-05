package io.github.yunbamboos.filter;

import java.util.ArrayList;
import java.util.List;

public record FilterList(List<IFilter> filters) implements IFilterList {

    public List<IFilter> getFilters(){
        return new ArrayList<>(filters);
    }

}
