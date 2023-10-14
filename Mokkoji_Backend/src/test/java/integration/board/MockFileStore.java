package integration.board;

import com.ssafy.Mokkoji.core.board.domain.BoardImage;
import com.ssafy.Mokkoji.core.board.domain.FileStore;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MockFileStore implements FileStore<BoardImage> {

    @Override
    public String getFilePath(String fileName) {
        return "/hello/" + fileName;
    }

    @Override
    public List<BoardImage> storeImages(List<MultipartFile> files) throws IOException {
        List<BoardImage> boardImages = new ArrayList<>();
        boardImages.add(BoardImage.builder().storedFileName("hello").userFileName("hello").build());
        return new ArrayList<>(boardImages);
    }

    @Override
    public void deleteFile(String fileName) {

    }

    @Override
    public BoardImage storeImage(MultipartFile file) throws IOException {
        return BoardImage.builder().build();
    }
}
