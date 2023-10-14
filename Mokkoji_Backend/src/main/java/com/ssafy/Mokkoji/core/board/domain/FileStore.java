package com.ssafy.Mokkoji.core.board.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileStore <T> {

    String getFilePath(String fileName);

    List<T> storeImages(List<MultipartFile> files) throws IOException;

    void deleteFile(String fileName);

    T storeImage(MultipartFile file) throws IOException;
}
