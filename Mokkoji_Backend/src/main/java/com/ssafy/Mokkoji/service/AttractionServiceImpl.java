package com.ssafy.Mokkoji.service;

import com.ssafy.Mokkoji.domain.AttractionInfo;
import com.ssafy.Mokkoji.domain.Gugun;
import com.ssafy.Mokkoji.dto.request.AttractionSearch;
import com.ssafy.Mokkoji.repository.AttractionInfoRepository;
import com.ssafy.Mokkoji.repository.GugunRepository;
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
    public List<AttractionInfo> getAllAttraction(AttractionSearch attractionSearch) {
        return attractionInfoRepository.getAllAttractionList(attractionSearch);
    }

    @Override
    public List<Gugun> getAllGugunBySidoCode(int sidoCode) {
        return gugunRepository.getAllGugunBySidoCode(sidoCode);
    }
}
