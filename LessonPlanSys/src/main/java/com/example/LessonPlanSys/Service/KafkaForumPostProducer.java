package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.ForumPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class KafkaForumPostProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaForumPostProducer.class);

    private final KafkaTemplate<String, ForumPost> kafkaTemplate;
    @Autowired
    public KafkaForumPostProducer(KafkaTemplate<String, ForumPost> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPost(ForumPost post){
        logger.info("Post sent!");

        Message<ForumPost> msg = MessageBuilder
                .withPayload(post)
                .setHeader(KafkaHeaders.TOPIC,"new-forum-posts")
                .build();

        this.kafkaTemplate.send(msg);
    }
    public void updatePost(int forumpost_id, ForumPost updatedPost){
        logger.info("Post updated!");
        kafkaTemplate.send("forum-post-update", String.valueOf(forumpost_id), updatedPost);
    }

    public void deletePost(int forumpost_id){
        logger.info("Post deleted!");
        ForumPost fp = null;
        kafkaTemplate.send("forum-post-delete", String.valueOf(forumpost_id),fp);
    }
}
