package com.efub.lakkulakku.domain.comments.service;

import com.efub.lakkulakku.domain.comments.entity.Comment;
import com.efub.lakkulakku.domain.comments.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;

    //private final UsersRepository usersRepository;
    //private final DiaryRepository diaryRepository;

    @Transactional
    public UUID commentsSave(UUID id, CommentsRequestDto dto) {
        //User user = userRepository.findById(id);
        //Diary diary = diaryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." ));
        //dto.setUser(user);
        //dto.setDiary(diary);
        Comment comment = dto.toEntity();
        commentsRepository.save(comment);
        return dto.getId();
    }

    @Transactional
    public UUID recommentsSave(UUID id, UUID parentId, CommentsRequestDto dto) {
        //User user = userRepository.findById(id);
        //Comment comment = commentsRepository.findById(parentId).orElseThrow(() -> ParentNotFoundException("상위 댓글이 존재하지 않습니다." ));
        //dto.setUser(user);
        //dto.setDiary(diary);
        Comment comment = dto.toEntity();
        commentsRepository.save(comment);
        return dto.getParentId();
    }

    @Transactional
    public void update(UUID id, CommentsRequestDto dto) {
        Comment comment = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. "));
        comment.update(dto.getContent());
    }

    @Transactional
    public void delete(UUID id) {
        Comment comment = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " ));
        commentsRepository.delete(comment);
    }


}
