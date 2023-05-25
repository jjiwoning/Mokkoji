package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.domain.AttractionInfo;
import com.ssafy.enjoytrip.domain.Gugun;
import com.ssafy.enjoytrip.dto.request.AttractionSearch;
import com.ssafy.enjoytrip.repository.AttractionInfoRepository;
import com.ssafy.enjoytrip.repository.GugunRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionInfoRepository attractionInfoRepository;

    private final GugunRepository gugunRepository;

    @Override
    public List<AttractionInfo> getAllAttraction(AttractionSearch attractionSearch) {
        return attractionInfoRepository.getAllAttractionList(attractionSearch);
    }

    @Override
    public List<Gugun> getAllGugunBySidoCode(int sidoCode) {
        return gugunRepository.getAllGugunBySidoCode(sidoCode);
    }
}
