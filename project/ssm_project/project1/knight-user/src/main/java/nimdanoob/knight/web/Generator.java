package nimdanoob.knight.web;

import com.knight.common.util.MybatisGeneratorUtil;
import com.knight.common.util.PropertiesFileUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by zhuoxiuwu on 2017/11/16.
 */
public class Generator {
    // 根据命名规范，只修改此常量值即可
    private static String MODULE = "knight";
    private static String DATABASE = "knight";
    private static String TABLE_PREFIX = "ucenter_";
    private static String PACKAGE_NAME = "nimdanoob.knight.web";
    private static String JDBC_DRIVER = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.driver");
    private static String JDBC_URL = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.url");
    private static String JDBC_USERNAME = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.username");
    private static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.password");
    // 需要insert后返回主键的表配置，key:表名,value:主键名
    private static Map<String, String> LAST_INSERT_ID_TABLES = new HashMap<>();
    //用于 只更新部分表情况
    private static HashSet<String> onlyTables = new HashSet<>();

    private static final int GENERATOR_MODE_ALL= 1;//更新所有的表
    private static final int GENERATOR_MODE_APPOINT= 2;// 只更新指定的表
    private static final int GENERATOR_MODE= GENERATOR_MODE_ALL;

    static {
    }

    /**
     * 自动代码生成
     * @param args
     */
    public static void main(String[] args) throws Exception {
        MybatisGeneratorUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, MODULE, DATABASE, TABLE_PREFIX, PACKAGE_NAME, LAST_INSERT_ID_TABLES
        ,null);
    }
}
