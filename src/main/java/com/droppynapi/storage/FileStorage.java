package com.droppynapi.storage;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

public interface FileStorage {
    void init();

    void save(MultipartFile file);

    Resource load(String filename);

    void deleteAll();

}
