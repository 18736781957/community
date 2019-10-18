package com.ygl.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;//是否展示上一页
    private boolean showFirstPage;//是否展示首页
    private boolean showNext;//是否展示下一页
    private boolean showEndPage;//是否展示最后一页
    private Integer page;//当前页
    private List<Integer> pages = new ArrayList<>();//当前页面的 页 数组
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {

        

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = (totalCount / size) + 1;
        }


        this.page = page;
        //为当前页面列表数的逻辑
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            //为头部进行添加
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            //为尾部进行添加
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //判断是否是首页  是否展示上一页
        if (page == 1) {
            showPrevious = false;//当为第一页的时候  不展示上一页
        } else {
            showPrevious = true;//不是第一页的时候要展示上一页
        }
        //判断是否是最后一页  是否展示下一页
        if (page == totalPage) {//判断是否是等于最后一页
            showNext = false;//是最后一页的时候  不显示下一页
        } else {
            showNext = true;
        }
        //判断页数列表是否包含首页，如果不包含则展示 首页 指示
        if (pages.contains(1)) {//contains属性是Jquery里判断当前所有值里是否包含当前括号里的值
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //判断页数列表是否包含未页，如果不包含则展示 未页 指示
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
