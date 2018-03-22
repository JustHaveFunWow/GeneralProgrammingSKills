package nimdanoob.knight.web.domain.mapper;

import java.util.List;
import nimdanoob.knight.web.domain.model.UserDetails;
import nimdanoob.knight.web.domain.model.UserDetailsExample;
import org.apache.ibatis.annotations.Param;

public interface UserDetailsMapper {
    long countByExample(UserDetailsExample example);

    int deleteByExample(UserDetailsExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UserDetails record);

    int insertSelective(UserDetails record);

    List<UserDetails> selectByExample(UserDetailsExample example);

    UserDetails selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UserDetails record, @Param("example") UserDetailsExample example);

    int updateByExample(@Param("record") UserDetails record, @Param("example") UserDetailsExample example);

    int updateByPrimaryKeySelective(UserDetails record);

    int updateByPrimaryKey(UserDetails record);
}