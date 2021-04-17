package com.foodshare.mapper;

import com.foodshare.entity.Image;
import com.foodshare.entity.ImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ImageMapper {
    long countByExample(ImageExample example);

    int deleteByExample(ImageExample example);

    int deleteByPrimaryKey(String md5);

    int insert(Image record);

    int insertSelective(Image record);

    List<Image> selectByExampleWithRowbounds(ImageExample example, RowBounds rowBounds);

    List<Image> selectByExample(ImageExample example);

    Image selectByPrimaryKey(String md5);

    int updateByExampleSelective(@Param("record") Image record, @Param("example") ImageExample example);

    int updateByExample(@Param("record") Image record, @Param("example") ImageExample example);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);

    List<Image> selectCondition(@Param("example") ImageExample example, @Param("fields") String fields);
}