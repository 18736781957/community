package com.ygl.community.service;

import com.ygl.community.dto.QuestionDTO;
import com.ygl.community.mapper.QuestionMapper;
import com.ygl.community.mapper.UserMapper;
import com.ygl.community.model.Question;
import com.ygl.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    public List<QuestionDTO> list() {
        List<Question> questionList = questionMapper.list();
        List<QuestionDTO>  questionDTOList=new ArrayList<>();
        for (Question question:questionList){
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//这个牛逼
            questionDTO.setUser(user);
//            questionDTO.setId(question.getId());
//            questionDTO.setTitle(question.getTitle());
//            questionDTO.setCommentCount(question.getCommentCount());
//            questionDTO.setGmtCreate(question.getGmtCreate());
//            questionDTO.setDescription(question.getDescription());
//            questionDTO.setTag(question.getTag());
//            questionDTO.setUser(user);
//            questionDTO.setViewCount(question.getViewCount());
//            questionDTO.setGmtModified(question.getGmtModified());
//            questionDTO.setCreator(question.getCreator());
            questionDTOList.add(questionDTO);

        }
        return questionDTOList;
    }
}
