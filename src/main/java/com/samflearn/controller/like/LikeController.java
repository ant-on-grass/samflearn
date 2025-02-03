package com.samflearn.controller.like;

import com.samflearn.service.like.LikeService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Void> createLike(
            @RequestParam Long user,
            @RequestParam Long course
    ) {
        likeService.createLikeService(user, course);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteLike(
            @RequestParam Long like,
            @RequestParam Long user,
            @RequestParam Long course
    ) {
        likeService.deleteLikeService(like, user, course);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
