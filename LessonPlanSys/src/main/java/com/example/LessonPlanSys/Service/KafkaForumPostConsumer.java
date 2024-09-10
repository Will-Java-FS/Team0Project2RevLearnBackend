package com.example.LessonPlanSys.Service;
import com.example.LessonPlanSys.Model.ForumPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

@Service
public class KafkaForumPostConsumer {
    private final Logger logger = LoggerFactory.getLogger(KafkaForumPostConsumer.class);
    private ForumPostService fps;

    @Autowired
    public KafkaForumPostConsumer(ForumPostService fps){
        this.fps = fps;
    }

    @KafkaListener(topics = "new-forum-posts", groupId = "sent-forum-post-listener")
    public void addPost(ForumPost post){
        logger.info("Post Added: {}", post.toString());
        fps.addForumPost(post);
    }

    @KafkaListener(topics = "forum-post-update", groupId = "update-forum-post-listener")
    public void updatePost(@Header(KafkaHeaders.RECEIVED_KEY) String id, @Payload ForumPost post){
        logger.info("Post Updated: {}", post.toString());
        logger.info("Id: {}", post.toString());
         fps.updateForumPost(Integer.parseInt(id), post);
    }

    @KafkaListener(topics = "forum-post-delete", groupId = "update-forum-post-listener")
    public void deletePost(@Header(KafkaHeaders.RECEIVED_KEY) String id, @Payload ForumPost post){
        logger.info("Post Deleted!");
        fps.deleteForumPost(Integer.parseInt(id));
    }
}
