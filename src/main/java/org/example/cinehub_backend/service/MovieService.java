package org.example.cinehub_backend.service;

import lombok.RequiredArgsConstructor;
import org.example.cinehub_backend.entity.Movie;
import org.example.cinehub_backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public boolean addMovie(String title, String description, String genre, String director, MultipartFile image, String token) {
        try {

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }


            String originalFileName = image.getOriginalFilename();
            String fileExtension = originalFileName != null ? originalFileName.substring(originalFileName.lastIndexOf(".")) : "";
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;


            Path path = Paths.get(uploadDir + File.separator + uniqueFileName);


            image.transferTo(path.toFile());


            Movie movie = new Movie();
            movie.setTitle(title);
            movie.setDescription(description);
            movie.setGenre(genre);
            movie.setDirector(director);
            movie.setImagePath(path.toString());

            movieRepository.save(movie);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
