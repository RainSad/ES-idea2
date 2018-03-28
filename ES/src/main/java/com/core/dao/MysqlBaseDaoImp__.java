package com.core.dao;
/*package com.core.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.core.common.sqltool.CamelCharOrUnderLine;
import com.core.common.sqltool.QueryResult;

*//**
 * @ClassName: MysqlBaseDaoImp
 * @Description: TODO(dao泛型超级类，实现各种数据库操作)
 * @author 孙文祥
 * @date 2017年8月11日 下午4:49:31
 * @param <T>
 * 设置一些操作的常量
 * 根据传入的实体保存到数据库
 * <p>
 * 根据传入的实体更新数据库数据
 * <p>
 * 根据传入的实体数据删除数据库信息
 * <p>
 * 根据传入id删除数据库数据
 * <p>
 * 根据泛型T删除数据库所有信息
 * <p>
 * 没写实现
 * <p>
 * 没写实现
 * <p>
 * 没写实现
 * <p>
 * 根据传入的的泛型T和id查找数据
 * <p>
 * 根据传入的泛型T查询所有数据
 * <p>
 * 根据传入的pageno和pagesize实现制定条数查找
 * <p>
 * 根据传入的pageno和pagesize和where子句实现指定查找
 * <p>
 * 根据传入的pageno和pagesize和orderby子句实现指定查找
 * <p>
 * 根据传入的pageno和pagesize和where和orderby子句实现指定查找
 * <p>
 * 拼接where条件的时候只写了and 没有考虑or
 * @param pageNo
 * @param pageSize
 * @param where
 * @param orderby
 * @return
 *//*
@Repository
public class MysqlBaseDaoImp<T> implements BaseDao<T> {

	*//** 设置一些操作的常量 *//*
    public static final String SQL_INSERT = "insert";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_DELETE = "delete";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	protected Class<T> entityClass;

	*//**
 * 根据传入的实体保存到数据库
 *//*
	@Override
	public void save(T entity) {
		String sql = this.makeSql(SQL_INSERT);
		Object[] args = this.setArgs(entity, SQL_INSERT);
		int[] argTypes = this.setArgTypes(entity, SQL_INSERT);
		jdbcTemplate.update(sql.toString(), args, argTypes);

	}

	*//**
 * 根据传入的实体更新数据库数据
 *//*
	@Override
	public void update(T entity) {
		String sql = this.makeSql(SQL_UPDATE);
		Object[] args = this.setArgs(entity, SQL_UPDATE);
		int[] argTypes = this.setArgTypes(entity, SQL_UPDATE);
		jdbcTemplate.update(sql, args, argTypes);
	}

	*//**
 * 根据传入的实体数据删除数据库信息
 *//*
	@Override
	public void delete(T entity) {
		String sql = this.makeSql(SQL_DELETE);
		Object[] args = this.setArgs(entity, SQL_DELETE);
		int[] argTypes = this.setArgTypes(entity, SQL_DELETE);
		jdbcTemplate.update(sql, args, argTypes);

	}

	*//**
 * 根据传入id删除数据库数据
 *//*
	@Override
	public void delete(Serializable id) {
		String sql = " DELETE FROM " + entityClass.getSimpleName() + " WHERE id=?";
		jdbcTemplate.update(sql, id);

	}

	*//**
 * 根据泛型T删除数据库所有信息
 *//*
	@Override
	public void deleteAll() {
		String sql = " TRUNCATE TABLE " + CamelCharOrUnderLine.camelCharToUnderLine(entityClass.getSimpleName());
		jdbcTemplate.execute(sql);
	}

	*//**
 * 没写实现
 *//*
	@Override
	public void batchSave(List<T> list) {
		// TODO Auto-generated method stub

	}

	*//**
 * 没写实现
 *//*
	@Override
	public void batchUpdate(List<T> list) {
		// TODO Auto-generated method stub

	}

	*//**
 * 没写实现
 *//*
	@Override
	public void batchDelete(List<T> list) {
		// TODO Auto-generated method stub

	}

	*//**
 * 根据传入的的泛型T和id查找数据
 *//*
	@Override
	public T findById(Serializable id) {
		String sql = "SELECT * FROM " + CamelCharOrUnderLine.camelCharToUnderLine(entityClass.getSimpleName())
				+ " WHERE id=?";
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		return jdbcTemplate.query(sql, rowMapper, id).get(0);

	}

	*//**
 * 根据传入的泛型T查询所有数据
 *//*
	@Override
	public List<T> findAll() {
		String sql = "SELECT * FROM " + CamelCharOrUnderLine.camelCharToUnderLine(entityClass.getSimpleName());
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		return jdbcTemplate.query(sql, rowMapper);

	}

	*//**
 * 根据传入的pageno和pagesize实现制定条数查找
 *//*
	@Override
	public QueryResult<T> findByPage(int pageNo, int pageSize) {
		List<T> list = this.find(pageNo, pageSize, null, null);
		int totalRow = this.count(null);
		return new QueryResult<T>(list, totalRow,pageNo,pageSize);

	}

	*//**
 * 根据传入的pageno和pagesize和where子句实现指定查找
 *//*
	@Override
	public QueryResult<T> findByPage(int pageNo, int pageSize, T where) {
		List<T> list = this.find(pageNo, pageSize, where, null);
		int totalRow = this.count(where);
		return new QueryResult<T>(list, totalRow,pageNo,pageSize);
	}

	*//**
 * 根据传入的pageno和pagesize和orderby子句实现指定查找
 *//*
	@Override
	public QueryResult<T> findByPage(int pageNo, int pageSize, LinkedHashMap<String, String> orderby) {
		List<T> list = this.find(pageNo, pageSize, null, orderby);
		int totalRow = this.count(null);
		return new QueryResult<T>(list, totalRow,pageNo,pageSize);

	}

	*//**
 * 根据传入的pageno和pagesize和where和orderby子句实现指定查找
 *//*
	@Override
	public QueryResult<T> findByPage(int pageNo, int pageSize, Map<String, String> where,
			LinkedHashMap<String, String> orderby) {
		List<T> list = this.find(pageNo, pageSize, where, orderby);
		int totalRow = this.count(where);
		return new QueryResult<T>(list, totalRow,pageNo,pageSize);

	}

	// 组装SQL
	private String makeSql(String sqlFlag) {
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
			for (int i = 0; fields != null && i < fields.length; i++) {
				fields[i].setAccessible(true); // 暴力反射
				String column = CamelCharOrUnderLine.camelCharToUnderLine(fields[i].getName());
				if (column.equals("id") || null == column) { // id 代表主键
					continue;
				}
				sql.append(column).append("=").append("?,");
			}
			sql = sql.deleteCharAt(sql.length() - 1);
			sql.append(" WHERE id=?");
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

	*//**
 * 拼接where条件的时候只写了and 没有考虑or
 *
 * @param pageNo
 * @param pageSize
 * @param where
 * @param orderby
 * @return
 *//*
	private List<T> find(Integer pageNo, Integer pageSize, Map<String, String> where, LinkedHashMap<String, String> orderby) {
		List<String> temp = new ArrayList<String>();
		StringBuffer sql = new StringBuffer(" SELECT s.* FROM (SELECT t.* FROM (SELECT * FROM "
				+ CamelCharOrUnderLine.camelCharToUnderLine(entityClass.getSimpleName()));
		if (where != null && where.size() > 0) {
			sql.append(" WHERE "); // 注意不是where
			for (Map.Entry<String, String> me : where.entrySet()) {
				String columnName = CamelCharOrUnderLine.camelCharToUnderLine(me.getKey());
				String columnValue = me.getValue();
				sql.append(columnName).append(" = ?").append(" AND "); // 没有考虑or的情况
				temp.add(columnValue);
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
		sql.append(" LIMIT "+ pageNo+","+ pageSize +" ) t ) s");
		
		Object[] args = temp.toArray();
		System.out.println(sql.toString() + "=====================" + temp.toString());
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		return jdbcTemplate.query(sql.toString(), args, rowMapper);
	}

	private int count(Map<String, String> where) {
		StringBuffer sql = new StringBuffer(
				" SELECT COUNT(*) FROM " + CamelCharOrUnderLine.camelCharToUnderLine(entityClass.getSimpleName()));
		if (where != null && where.size() > 0) {
			sql.append(" WHERE ");
			for (Map.Entry<String, String> me : where.entrySet()) {
				String columnName = CamelCharOrUnderLine.camelCharToUnderLine(me.getKey());
				String columnValue = me.getValue();
				sql.append(columnName).append(" = '").append(columnValue).append("' AND "); // 没有考虑or的情况
			}
			int endIndex = sql.lastIndexOf("AND");
			if (endIndex > 0) {
				sql = new StringBuffer(sql.substring(0, endIndex));
			}
		}
		System.out.println(sql.toString());
		return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
	}
}
*/