package com.core.dao;

import com.alibaba.fastjson.JSON;
import com.core.common.utills.CamelCharOrUnderLine;
import com.sys.entity.resdata.QueryResultPage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @param <T>
 * @author 孙文祥
 * @ClassName: MysqlBaseDaoImp
 * @Description: TODO(dao泛型超级类，实现各种数据库操作)
 * @date 2017年8月11日 下午4:49:31
 */
@Repository
public class MysqlBaseDaoImp<T> implements BaseDao<T> {
    Logger log = Logger.getLogger(MysqlBaseDaoImp.class);

    /**
     * 设置一些操作的常量
     */
    public static final String SQL_INSERT = "insert";
    public static final String SQL_UPDATE = "update";
    public static final String SQL_DELETE = "delete";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    protected Class<T> entityClass;

    /**
     * 根据传入的实体保存到数据库
     */
    @Override
    public void save(T entity) {
        String sql = this.makeSql(SQL_INSERT, null);
        Object[] args = this.setArgs(entity, SQL_INSERT);
        log.info(sql.toString());
        jdbcTemplate.update(sql.toString(), args);

    }

    /**
     * 根据传入的实体更新数据库数据
     */
    @Override
    public void update(T entity) {
        String sql = this.makeSql(SQL_UPDATE, entity);
        Object[] args = this.setArgs(entity, SQL_UPDATE);
        log.info(sql + JSON.toJSONString(entity));
        jdbcTemplate.update(sql, args);
    }

    /**
     * 根据传入的实体数据删除数据库信息
     */
    @Override
    public void delete(T entity) {
        String sql = this.makeSql(SQL_DELETE, null);
        Object[] args = this.setArgs(entity, SQL_DELETE);
        jdbcTemplate.update(sql, args);

    }

    /**
     * 根据传入id删除数据库数据
     */
    @Override
    public void delete(Serializable id) {
        String sql = " DELETE FROM " + entityClass.getSimpleName() + " WHERE id=?";
        jdbcTemplate.update(sql, id);

    }

    /**
     * 根据泛型T删除数据库所有信息
     */
    @Override
    public void deleteAll() {
        String sql = " TRUNCATE TABLE " + CamelCharOrUnderLine.camelCharToUnderLine(entityClass.getSimpleName());
        jdbcTemplate.execute(sql);
    }

    /**
     * 没写实现
     */
    @Override
    public void batchSave(List<T> list) {
        // TODO Auto-generated method stub

    }

    /**
     * 没写实现
     */
    @Override
    public void batchUpdate(List<T> list) {
        // TODO Auto-generated method stub

    }

    /**
     * 没写实现
     */
    @Override
    public void batchDelete(List<T> list) {
        // TODO Auto-generated method stub

    }

    /**
     * 根据传入的的泛型T和id查找数据
     */
    @Override
    public List<T> findById(Serializable id) {
        String sql = "SELECT * FROM " + CamelCharOrUnderLine.camelCharToUnderLine(entityClass.getSimpleName())
                + " WHERE id=?";
        RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
//System.out.println(sql);
        return jdbcTemplate.query(sql, rowMapper, id);

    }

    /**
     * 根据传入的泛型T查询所有数据
     */
    @Override
    public List<T> findAll() {
        String sql = "SELECT * FROM " + CamelCharOrUnderLine.camelCharToUnderLine(entityClass.getSimpleName());
        RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
        return jdbcTemplate.query(sql, rowMapper);

    }

    /**
     * 根据传入的pageno和pagesize实现制定条数查找
     */
    @Override
    public QueryResultPage<T> findByPage(Integer pageNo, Integer pageSize) {
        List<T> list = this.find(pageNo, pageSize, null, null);
        Integer totalRow = this.count(null);
        return new QueryResultPage<T>(list, totalRow, pageNo, pageSize);

    }

    /**
     * 根据传入的pageno和pagesize和where子句实现指定查找
     */
    @Override
    public QueryResultPage<T> findByPage(Integer pageNo, Integer pageSize, T entity) {
        List<T> list = this.find(pageNo, pageSize, entity, null);
        Integer totalRow = this.count(entity);
        return new QueryResultPage<T>(list, totalRow, pageNo, pageSize);

    }

    /**
     * 根据传入的pageno和pagesize和orderby子句实现指定查找
     */
    @Override
    public QueryResultPage<T> findByPage(Integer pageNo, Integer pageSize, Map<String, String> orderby) {
        List<T> list = this.find(pageNo, pageSize, null, orderby);
        Integer totalRow = this.count(null);
        return new QueryResultPage<T>(list, totalRow, pageNo, pageSize);

    }

    /**
     * 根据传入的pageno和pagesize和where和orderby子句实现指定查找
     */
    @Override
    public QueryResultPage<T> findByPage(Integer pageNo, Integer pageSize, T entity,
                                         Map<String, String> orderby) {
        List<T> list = this.find(pageNo, pageSize, entity, orderby);
        Integer totalRow = this.count(entity);
        return new QueryResultPage<T>(list, totalRow, pageNo, pageSize);

    }

    // 组装SQL
    private String makeSql(String sqlFlag, T entity) {
        StringBuffer sql = new StringBuffer();
        Field[] fields = entityClass.getDeclaredFields();// 得到变量
        if (sqlFlag.equals(SQL_INSERT)) {
            sql.append(" INSERT INTO " + CamelCharOrUnderLine.camelCharToUnderLine(entityClass.getSimpleName()));
            sql.append("(");
            for (int i = 0; fields != null && i < fields.length; i++) {
                fields[i].setAccessible(true); // 暴力反射
                String column = CamelCharOrUnderLine.camelCharToUnderLine(fields[i].getName());
                sql.append(column).append(",");
            }
            sql = sql.deleteCharAt(sql.length() - 1);
            sql.append(") VALUES (");
            for (int i = 0; fields != null && i < fields.length; i++) {
                sql.append("?,");
            }
            sql = sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
        } else if (sqlFlag.equals(SQL_UPDATE)) {
            sql.append(" UPDATE " + CamelCharOrUnderLine.camelCharToUnderLine(entityClass.getSimpleName()) + " SET ");

            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    if (null != field.get(entity)) {
                        if (field.getName().equals("id") || null == field.getName()) { // id 代表主键
                            continue;
                        }
                        sql.append(CamelCharOrUnderLine.camelCharToUnderLine(field.getName()))
                                .append("=").append("?,");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            sql = sql.deleteCharAt(sql.length() - 1);
            sql.append(" WHERE id=?");

           /* for (int i = 0; fields != null && i < fields.length; i++) {
                fields[i].setAccessible(true); // 暴力反射
                String column = CamelCharOrUnderLine.camelCharToUnderLine(fields[i].getName());
                if (column.equals("id") || null == column) { // id 代表主键
                    continue;
                }
                sql.append(column).append("=").append("?,");
            }
            sql = sql.deleteCharAt(sql.length() - 1);
            sql.append(" WHERE id=?");*/
        } else if (sqlFlag.equals(SQL_DELETE)) {
            sql.append(" DELETE FROM " + CamelCharOrUnderLine.camelCharToUnderLine(entityClass.getSimpleName())
                    + " WHERE id=?");
        }
        return sql.toString();

    }

    // 设置参数
    private Object[] setArgs(T entity, String sqlFlag) {
        Field[] fields = entityClass.getDeclaredFields();
        if (sqlFlag.equals(SQL_INSERT)) {
            Object[] args = new Object[fields.length];
            for (int i = 0; args != null && i < args.length; i++) {
                try {
                    fields[i].setAccessible(true); // 暴力反射
                    args[i] = fields[i].get(entity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return args;
        } else if (sqlFlag.equals(SQL_UPDATE)) {
            Object[] tempArr = new Object[fields.length];
            for (int i = 0; tempArr != null && i < tempArr.length; i++) {
                try {
                    fields[i].setAccessible(true); // 暴力反射
                    tempArr[i] = fields[i].get(entity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Object[] args = new Object[fields.length];
            args[args.length - 1] = tempArr[0];
            return args;
        } else if (sqlFlag.equals(SQL_DELETE)) {
            Object[] args = new Object[1]; // 长度是1
            fields[0].setAccessible(true); // 暴力反射
            try {
                args[0] = fields[0].get(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return args;
        }
        return null;

    }

    // 设置参数类型（写的不全，只是一些常用的）
    @SuppressWarnings("unused")
    private int[] setArgTypes(T entity, String sqlFlag) {
        Field[] fields = entityClass.getDeclaredFields();
        if (sqlFlag.equals(SQL_INSERT)) {
            int[] argTypes = new int[fields.length];
            try {
                for (int i = 0; argTypes != null && i < argTypes.length; i++) {
                    fields[i].setAccessible(true); // 暴力反射
                    if (fields[i].get(entity).getClass().getName().equals("java.lang.String")) {
                        argTypes[i] = Types.VARCHAR;
                    } else if (fields[i].get(entity).getClass().getName().equals("java.lang.Double")) {
                        argTypes[i] = Types.DECIMAL;
                    } else if (fields[i].get(entity).getClass().getName().equals("java.lang.Integer")) {
                        argTypes[i] = Types.INTEGER;
                    } else if (fields[i].get(entity).getClass().getName().equals("java.util.Date")) {
                        argTypes[i] = Types.DATE;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return argTypes;
        } else if (sqlFlag.equals(SQL_UPDATE)) {
            int[] tempArgTypes = new int[fields.length];
            int[] argTypes = new int[fields.length];
            try {
                for (int i = 0; tempArgTypes != null && i < tempArgTypes.length; i++) {
                    fields[i].setAccessible(true); // 暴力反射
                    if (fields[i].get(entity).getClass().getName().equals("java.lang.String")) {
                        tempArgTypes[i] = Types.VARCHAR;
                    } else if (fields[i].get(entity).getClass().getName().equals("java.lang.Double")) {
                        tempArgTypes[i] = Types.DECIMAL;
                    } else if (fields[i].get(entity).getClass().getName().equals("java.lang.Integer")) {
                        tempArgTypes[i] = Types.INTEGER;
                    } else if (fields[i].get(entity).getClass().getName().equals("java.util.Date")) {
                        tempArgTypes[i] = Types.DATE;
                    }
                }
                argTypes[argTypes.length - 1] = tempArgTypes[0];

            } catch (Exception e) {
                e.printStackTrace();
            }
            return argTypes;

        } else if (sqlFlag.equals(SQL_DELETE)) {
            int[] argTypes = new int[1]; // 长度是1
            try {
                fields[0].setAccessible(true); // 暴力反射
                if (fields[0].get(entity).getClass().getName().equals("java.lang.String")) {
                    argTypes[0] = Types.VARCHAR;
                } else if (fields[0].get(entity).getClass().getName().equals("java.lang.Integer")) {
                    argTypes[0] = Types.INTEGER;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return argTypes;
        }
        return null;
    }

    /**
     * 拼接where条件的时候只写了and 没有考虑or
     *
     * @param pageNo   如果pageNo 和pageNo都为-1的话即不分页
     * @param pageSize
     * @param where
     * @param orderby
     * @return
     */
    private List<T> find(Integer pageNo, Integer pageSize, T entity, Map<String, String> orderby) {
        List<String> temp = new ArrayList<String>();
        StringBuffer sql = new StringBuffer(" SELECT s.* FROM (SELECT t.* FROM (SELECT * FROM "
                + CamelCharOrUnderLine.camelCharToUnderLine(entityClass.getSimpleName()));
        Field[] fields = entityClass.getDeclaredFields();// 得到变量
        if (fields != null && fields.length > 0) {
            sql.append(" WHERE "); //
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    if (null != field.get(entity)) {
                        sql.append(CamelCharOrUnderLine.camelCharToUnderLine(field.getName())); //userName => user_name
                        sql.append(" = ?").append(" AND ");// 没有考虑or的情况
                        temp.add((String) field.get(entity));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            int endIndex = sql.lastIndexOf("AND");
            if (endIndex > 0) {
                sql = new StringBuffer(sql.substring(0, endIndex));
            }
        }
        if (orderby != null && orderby.size() > 0) {
            sql.append(" ORDER BY ");
            for (Map.Entry<String, String> me : orderby.entrySet()) {
                String columnName = CamelCharOrUnderLine.camelCharToUnderLine(me.getKey());
                String columnValue = me.getValue();
                sql.append(columnName).append(" = ?").append(",");
                temp.add(columnValue);
            }
            sql = sql.deleteCharAt(sql.length() - 1);
        }
        if (pageNo == -1 && pageSize == -1) {
            sql.append(" ) t ) s");
        } else {
            sql.append(" LIMIT " + pageNo + "," + pageSize + " ) t ) s");
        }
        log.info(sql.toString() + "    =====================   " + JSON.toJSONString(temp.toString()));
        RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
        return jdbcTemplate.query(sql.toString(), temp.toArray(), rowMapper);
    }

    private Integer count(T entity) {
        StringBuffer sql = new StringBuffer(
                " SELECT COUNT(*) FROM " + CamelCharOrUnderLine.camelCharToUnderLine(entityClass.getSimpleName()));
        Field[] fields = entityClass.getDeclaredFields();// 得到变量
        if (fields != null && fields.length > 0) {
            sql.append(" WHERE "); //
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    if (null != field.get(entity)) {
                        sql.append(CamelCharOrUnderLine.camelCharToUnderLine(field.getName())); //userName => user_name
                        sql.append(" = '" + field.get(entity) + "'").append(" AND ");// 没有考虑or的情况
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            int endIndex = sql.lastIndexOf("AND");
            if (endIndex > 0) {
                sql = new StringBuffer(sql.substring(0, endIndex));
            }
        }
        log.info(sql.toString());
        return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
    }
}
