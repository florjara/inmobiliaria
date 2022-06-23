package edu.egg.inmobiliaria.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class ImageService {

    private static final String DIRECTORY = "src/main/resources/static/uploads";

    public String copy(MultipartFile image) {
        try {
            String photoName = image.getOriginalFilename();
            Path photoPath = Paths.get(DIRECTORY, photoName).toAbsolutePath();
            Files.copy(image.getInputStream(), photoPath, StandardCopyOption.REPLACE_EXISTING);
            return photoName;
        } catch (IOException e) {
            throw new IllegalArgumentException("Error saving image");
        }
    }

}
