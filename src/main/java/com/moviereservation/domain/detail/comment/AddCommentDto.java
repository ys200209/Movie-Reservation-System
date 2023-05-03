package com.moviereservation.domain.detail.comment;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class AddCommentDto {
    private final String memberId;
    private final String content;
}
