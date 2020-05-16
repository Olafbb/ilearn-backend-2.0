package com.ilearn.service.dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.users.FullAccount;
import com.ilearn.config.InfoConfig;
import lombok.Getter;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Getter
@Service
public class DropBoxService {
    private DbxRequestConfig config;
    private DbxClientV2 client;
    private FullAccount account;

    private InfoConfig info;

    public DropBoxService(InfoConfig info) {
        this.info = info;
        config = DbxRequestConfig.newBuilder("dropbox/i-learn-application").build();
        client = new DbxClientV2(config, info.getDropboxToken());
    }

    public void sendFile(InputStream inputStream, String fileName) {
        File file = new File(info.getDropboxSavePath() + fileName);
        try {
            FileUtils.copyInputStreamToFile(inputStream, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (InputStream in = new FileInputStream(file.getPath())) {
            FileMetadata metadata = client.files().uploadBuilder(file.getPath())
                    .uploadAndFinish(in);
        } catch (DbxException | IOException e) {
            e.printStackTrace();
        }
    }
}
