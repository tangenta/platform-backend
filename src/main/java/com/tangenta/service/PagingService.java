package com.tangenta.service;

import com.tangenta.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PagingService {
    public <T, ID> List<T> paging(List<T> totalList, int number, ID from,
                                  Function<T, ID> idExtractor) {
        int index = 0;
        for (T t: totalList) {
            if (idExtractor.apply(t).equals(from)) {
                break;
            }
            index++;
        }
        if (index == totalList.size())
            return totalList.stream().limit(number).collect(Collectors.toList());
        return totalList.stream().skip(index + 1).limit(number).collect(Collectors.toList());
    }
}
