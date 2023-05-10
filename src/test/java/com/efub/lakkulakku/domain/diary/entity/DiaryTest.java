//package com.efub.lakkulakku.domain.diary.entity;
//
//import com.efub.lakkulakku.domain.comment.entity.Comment;
//import com.efub.lakkulakku.domain.image.entity.Image;
//import com.efub.lakkulakku.domain.likes.entity.Likes;
//import com.efub.lakkulakku.domain.sticker.entity.Sticker;
//import com.efub.lakkulakku.domain.text.entity.Text;
//import com.efub.lakkulakku.domain.users.entity.Users;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//@ExtendWith(SpringExtension.class) //Junit4의 Runwith과 같은 기능을 하는 Junit5 어노테이션
//class DiaryTest {
//
//	@Builder
//	public Diary(Users user, LocalDate date, List<Comment> comments,
//				 List<Image> images, List<Likes> likes, List<Text> texts, List<Sticker> stickers) {
//		this.user = user;
//		this.date = date;
//		this.comments = comments;
//		this.images = images;
//		this.likes = likes;
//		this.texts = texts;
//		this.stickers = stickers;
//
//		Users user = new Users();
//		user.setName("test user");
//
//		// Create a diary
//		Diary diary = Diary.builder()
//				.user(user)
//				.date(LocalDate.now())
//				.build();
//
//		// Create some comments for the diary
//		Comment comment1 = new Comment();
//		comment1.update("test comment 1");
//		Comment comment2 = new Comment();
//		comment2.update("test comment 2");
//		List<Comment> commentList = new ArrayList<>();
//		commentList.add(comment1);
//		commentList.add(comment2);
//		diary.getComments().addAll(commentList);
//
//		// Create a template for the diary
//		Template template = new Template();
//		template.setName("test template");
//		diary.setTemplate(template);
//
//		// Create some images for the diary
//		Image image1 = new Image();
//		image1.setUrl("test url 1");
//		Image image2 = new Image();
//		image2.setUrl("test url 2");
//		List<Image> imageList = new ArrayList<>();
//		imageList.add(image1);
//		imageList.add(image2);
//		diary.getImages().addAll(imageList);
//
//		// Create some likes for the diary
//		Likes like1 = new Likes();
//		Likes like2 = new Likes();
//		List<Likes> likesList = new ArrayList<>();
//		likesList.add(like1);
//		likesList.add(like2);
//		diary.getLikes().addAll(likesList);
//
//		// Create some texts for the diary
//		Text text1 = new Text();
//		text1.setContent("test text 1");
//		Text text2 = new Text();
//		text2.setContent("test text 2");
//		List<Text> textList = new ArrayList<>();
//		textList.add(text1);
//		textList.add(text2);
//		diary.getTexts().addAll(textList);
//
//		// Create some stickers for the diary
//		Sticker sticker1 = new Sticker();
//		sticker1.setName("test sticker 1");
//		Sticker sticker2 = new Sticker();
//		sticker2.setName("test sticker 2");
//		List<Sticker> stickerList = new ArrayList<>();
//		stickerList.add(sticker1);
//		stickerList.add(sticker2);
//		diary.getStickers().addAll(stickerList);
//
//		// Set the diary's comment count and like count
//		diary.setCntComment(commentList.size());
//		diary.setCntLike(likesList.size());
//
//		// Save the diary to the database
//
//
//
//
//
//	}
//
//	@Test
//	@DisplayName("다이어리 생성 테스트")
//	void createDiary(){
//		final String title = "title";
//		final List<Comment> comments = null;
//		final List<Image> images = null;
//		final List<Likes> likes = null;
//		final List<Text> texts = null;
//		final List<Sticker> stickers = null;
//	}
//
//
//
//}