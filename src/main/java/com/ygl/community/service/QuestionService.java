package com.ygl.community.service;

import com.ygl.community.dto.PaginationDTO;
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

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();//所有的数据条数、
        paginationDTO.setPagination(totalCount, page, size);//把所有的数据条数 当前页  每页的数量大小 全部传给setPagination方法进行计算
        if (page < 1) {
            page = 1;
        }

        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }

        //从第几个数开始：(page-1)*size
        Integer offset = (page - 1) * size;

        List<Question> questionList = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);//这个牛逼
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

        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }
}
