package nimdanoob.knight.web.domain.mapper;

import java.util.List;
import nimdanoob.knight.web.domain.model.UserLog;
import nimdanoob.knight.web.domain.model.UserLogExample;
import org.apache.ibatis.annotations.Param;

public interface UserLogMapper {
    long countByExample(UserLogExample example);

    int deleteByExample(UserLogExample example);

    int deleteByPrimaryKey(Integer userLogId);

    int insert(UserLog record);

    int insertSelective(UserLog record);

    List<UserLog> selectByExampleWithBLOBs(UserLogExample example);

    List<UserLog> selectByExample(UserLogExample example);

    UserLog selectByPrimaryKey(Integer userLogId);

    int updateByExampleSelective(@Param("record") UserLog record, @Param("example") UserLogExample example);

    int updateByExampleWithBLOBs(@Param("record") UserLog record, @Param("example") UserLogExample example);

    int updateByExample(@Param("record") UserLog record, @Param("example") UserLogExample example);

    int updateByPrimaryKeySelective(UserLog record);

    int updateByPrimaryKeyWithBLOBs(UserLog record);

    int updateByPrimaryKey(UserLog record);
}