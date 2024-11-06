package com.itmk.web.goods_comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itmk.web.goods_comment.entity.GoodsComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自由如風
 */
public interface GoodsCommentMapper extends BaseMapper<GoodsComment> {

    //查询评论列表数据
    List<GoodsComment> commentList(@Param("goodsId") Long goodsId);

    //分页page
    IPage<GoodsComment> getList(Page<GoodsComment> page);
}
