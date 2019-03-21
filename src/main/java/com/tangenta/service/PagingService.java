package com.tangenta.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PagingService {
    public <T> List<T> paging(List<T> totalList, int number, int from) {
        return totalList.stream().skip(from).limit(number).collect(Collectors.toList());
    }
}
