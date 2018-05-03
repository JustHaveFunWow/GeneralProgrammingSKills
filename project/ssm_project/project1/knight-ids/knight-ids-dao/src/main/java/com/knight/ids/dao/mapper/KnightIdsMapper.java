package com.knight.ids.dao.mapper;

import com.knight.ids.dao.model.KnightIds;
import com.knight.ids.dao.model.KnightIdsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KnightIdsMapper {
    long countByExample(KnightIdsExample example);

    int deleteByExample(KnightIdsExample example);

    int deleteByPrimaryKey(Integer idsSystemId);

    int insert(KnightIds record);

    int insertSelective(KnightIds record);

    List<KnightIds> selectByExample(KnightIdsExample example);

    KnightIds selectByPrimaryKey(Integer idsSystemId);

    int updateByExampleSelective(@Param("record") KnightIds record, @Param("example") KnightIdsExample example);

    int updateByExample(@Param("record") KnightIds record, @Param("example") KnightIdsExample example);

    int updateByPrimaryKeySelective(KnightIds record);

    int updateByPrimaryKey(KnightIds record);
}