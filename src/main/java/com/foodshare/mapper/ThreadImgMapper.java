package com.foodshare.mapper;

import com.foodshare.entity.ThreadImgExample;
import com.foodshare.entity.ThreadImgKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ThreadImgMapper {
    long countByExample(ThreadImgExample example);

    int deleteByExample(ThreadImgExample example);

    int deleteByPrimaryKey(ThreadImgKey key);

    int insert(ThreadImgKey record);

    int insertSelective(ThreadImgKey record);

    List<ThreadImgKey> selectByExampleWithRowbounds(ThreadImgExample example, RowBounds rowBounds);

    List<ThreadImgKey> selectByExample(ThreadImgExample example);

    int updateByExampleSelective(@Param("record") ThreadImgKey record, @Param("example") ThreadImgExample example);

    int updateByExample(@Param("record") ThreadImgKey record, @Param("example") ThreadImgExample example);

    List<ThreadImgKey> selectCondition(@Param("example") ThreadImgExample example, @Param("fields") String fields);
}