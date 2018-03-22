package nimdanoob.knight.web.domain.mapper;

import java.util.List;
import nimdanoob.knight.web.domain.model.UserOauth;
import nimdanoob.knight.web.domain.model.UserOauthExample;
import org.apache.ibatis.annotations.Param;

public interface UserOauthMapper {
    long countByExample(UserOauthExample example);

    int deleteByExample(UserOauthExample example);

    int deleteByPrimaryKey(Integer userOauthId);

    int insert(UserOauth record);

    int insertSelective(UserOauth record);

    List<UserOauth> selectByExampleWithBLOBs(UserOauthExample example);

    List<UserOauth> selectByExample(UserOauthExample example);

    UserOauth selectByPrimaryKey(Integer userOauthId);

    int updateByExampleSelective(@Param("record") UserOauth record, @Param("example") UserOauthExample example);

    int updateByExampleWithBLOBs(@Param("record") UserOauth record, @Param("example") UserOauthExample example);

    int updateByExample(@Param("record") UserOauth record, @Param("example") UserOauthExample example);

    int updateByPrimaryKeySelective(UserOauth record);

    int updateByPrimaryKeyWithBLOBs(UserOauth record);

    int updateByPrimaryKey(UserOauth record);
}