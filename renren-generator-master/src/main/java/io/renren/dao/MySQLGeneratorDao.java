package io.renren.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * MySQL代码生成器
 *
 * @author Mark sunlightcs@gmail.com
 * @since 2018-07-24
 */
@Mapper
public interface MySQLGeneratorDao extends GeneratorDao {

    /**
     * 获取所有数据库
     * @return 所有数据库名称
     */
    List<String> queryDatabase();
}
