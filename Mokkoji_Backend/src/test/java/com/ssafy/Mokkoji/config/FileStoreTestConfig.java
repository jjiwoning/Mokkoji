package com.ssafy.Mokkoji.config;

import com.ssafy.Mokkoji.core.board.domain.BoardImage;
import com.ssafy.Mokkoji.core.board.domain.FileStore;
import integration.board.MockFileStore;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class FileStoreTestConfig {

    @Bean
    public FileStore<BoardImage> fileStore() {
        return new MockFileStore();
    }
}
