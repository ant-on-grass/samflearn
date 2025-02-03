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

    @PostMapping("/{userId}/{courseId}")
    public ResponseEntity<Void> createLike(
            @PathVariable Long userId,
            @PathVariable Long courseId
    ) {
        likeService.createLikeService(userId, courseId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{likeId}/{userId}/{courseId}")
    public ResponseEntity<Void> deleteLike(
            @PathVariable Long likeId,
            @PathVariable Long userId,
            @PathVariable Long courseId
    ) {
        likeService.deleteLikeService(likeId, userId, courseId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
