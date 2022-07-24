package com.efub.lakkulakku.domain.comments.dto;

import com.efub.lakkulakku.domain.comments.entity.Comment;
import lombok.Data;

import java.util.UUID;

@Data
public class RecommentInfoDto {

    private UUID diaryId;
    private UUID parentId;
    private UUID recommentId;
    private String content;

    public RecommentInfoDto(Comment recomment) {
        this.diaryId = recomment.getDiary().getId();
        this.parentId = recomment.getParentId();
        this.recommentId = recomment.getId();
        this.content = recomment.getContent();

    }
}