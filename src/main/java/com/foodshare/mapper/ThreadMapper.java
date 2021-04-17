package com.foodshare.mapper;

import com.foodshare.entity.Thread;
import com.foodshare.entity.ThreadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ThreadMapper {
    long countByExample(ThreadExample example);

    int deleteByExample(ThreadExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Thread record);

    int insertSelective(Thread record);

    List<Thread> selectByExampleWithRowbounds(ThreadExample example, RowBounds rowBounds);

    List<Thread> selectByExample(ThreadExample example);

    Thread selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Thread record, @Param("example") ThreadExample example);

    int updateByExample(@Param("record") Thread record, @Param("example") ThreadExample example);

    int updateByPrimaryKeySelective(Thread record);

    int updateByPrimaryKey(Thread record);

    List<Thread> selectCondition(@Param("example") ThreadExample example, @Param("fields") String fields);
}