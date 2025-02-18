package org.example.cinehub_backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.cinehub_backend.dto.response.BaseResponse;
import org.example.cinehub_backend.service.MovieService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BaseResponse<Boolean>> addMovie(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String director,
            @RequestParam MultipartFile image,
            @RequestParam String token)
    {
        boolean result = movieService.addMovie(title, description, genre, director, image, token);

        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .success(result)
                .message(result ? "Film başarıyla eklendi." : "Film eklenirken bir hata oluştu.")
                .data(result)
                .build());
    }
}

