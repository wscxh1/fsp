package com.foodshare.mapper;

import com.foodshare.entity.ThreadPosition;
import com.foodshare.entity.ThreadPositionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadPositionMapper {
    long countByExample(ThreadPositionExample example);

    int deleteByExample(ThreadPositionExample example);

    int deleteByPrimaryKey(Long threadId);

    int insert(ThreadPosition record);

    int insertSelective(ThreadPosition record);

    List<ThreadPosition> selectByExampleWithRowbounds(ThreadPositionExample example, RowBounds rowBounds);

    List<ThreadPosition> selectByExample(ThreadPositionExample example);

    ThreadPosition selectByPrimaryKey(Long threadId);

    int updateByExampleSelective(@Param("record") ThreadPosition record, @Param("example") ThreadPositionExample example);

    int updateByExample(@Param("record") ThreadPosition record, @Param("example") ThreadPositionExample example);

    int updateByPrimaryKeySelective(ThreadPosition record);

    int updateByPrimaryKey(ThreadPosition record);

    List<ThreadPosition> selectCondition(@Param("example") ThreadPositionExample example, @Param("fields") String fields);
}