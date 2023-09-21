package com.ssafy.Mokkoji.core.attraction.service;

import com.ssafy.Mokkoji.core.attraction.domain.AttractionInfo;
import com.ssafy.Mokkoji.core.attraction.domain.Gugun;
import com.ssafy.Mokkoji.core.attraction.dto.request.AttractionSearch;
import com.ssafy.Mokkoji.core.attraction.repository.AttractionInfoRepository;
import com.ssafy.Mokkoji.core.attraction.repository.GugunRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionInfoRepository attractionInfoRepository;

    private final GugunRepository gugunRepository;

    @Override
    public List<AttractionInfo> getAllAttraction(final AttractionSearch attractionSearch) {
        return attractionInfoRepository.getAllAttractionList(attractionSearch);
    }

    @Override
    public List<Gugun> getAllGugunBySidoCode(final int sidoCode) {
        return gugunRepository.getAllGugunBySidoCode(sidoCode);
    }
}
