package nimdanoob.knight.web.dao.mapper;

import java.util.List;
import nimdanoob.knight.web.dao.model.Oauth;
import nimdanoob.knight.web.dao.model.OauthExample;
import org.apache.ibatis.annotations.Param;

public interface OauthMapper {
    long countByExample(OauthExample example);

    int deleteByExample(OauthExample example);

    int deleteByPrimaryKey(Integer oauthId);

    int insert(Oauth record);

    int insertSelective(Oauth record);

    List<Oauth> selectByExample(OauthExample example);

    Oauth selectByPrimaryKey(Integer oauthId);

    int updateByExampleSelective(@Param("record") Oauth record, @Param("example") OauthExample example);

    int updateByExample(@Param("record") Oauth record, @Param("example") OauthExample example);

    int updateByPrimaryKeySelective(Oauth record);

    int updateByPrimaryKey(Oauth record);
}