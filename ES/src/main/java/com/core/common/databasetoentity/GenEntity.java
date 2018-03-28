package com.core.common.databasetoentity;

import com.core.common.utills.CamelCharOrUnderLine;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * 
 * @ClassName: GenEntity
 * @Description: 数据库映射成entity文件
 * @author 孙文祥
 * @date 2017年8月13日 下午4:26:03
 *
 */
public class GenEntity {

	// 配置部分
	private String packageOutPath = "main.java.com.sys.entity.sys";// 指定实体生成所在包的路径
	private boolean f_rewrite = true;// 是否覆盖导入表
	private int startWrite = 10; // 生成包名 生成包名的时候读取的位置 main.java.com.sys.entity.sys -> com.sys.entity.sys
									// 由于maven包名自动过滤main.java
	private String authorName = "孙文祥";// 作者名字
	private static String dataBaseName = "es";// 数据库名
	private static String dataTableName = "sys_role_user"; // 需要单独生成的表名，如果选择了根据数据库表生成所有的表，可以不用填
	private static boolean f_exportAll = false;// 是否根据数据库中的表生成所有的实体
	private boolean f_constructor = true;// 是否生成构造方法
	private boolean f_hibernate_annotation = true; // 是否生成hibernate注解
	private boolean f_note = true; // 是否根据数据库字段生成注释
	private boolean f_class_note = true;// 是否生成类注释
	private boolean f_toToString = true;// 是否生成toString方法
	private static boolean f_print_note = true;// 是否打印注释
	private static boolean f_print_table = true;// 是否打印表名

	// 数据库连接
	private static final String URL = "jdbc:mysql://192.168.1.150:3306/" + dataBaseName
			+ "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private static final String NAME = "root";
	private static final String PASS = "sun123";
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	private String[] colnames; // 列名数组
	private String[] colTypes; // 列名类型数组
	private int[] colSizes; // 列名大小数组
	private boolean f_util = false; // 是否需要导入包java.util.*
	private boolean f_sql = false; // 是否需要导入包java.sql.*

	// 获得链接
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = (Connection) DriverManager.getConnection(URL, NAME, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 获取当前数据库下的所有表名称
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<String> getAllTableName() throws Exception {
		List<String> tables = new ArrayList<String>();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SHOW TABLES ");
		while (rs.next()) {
			String tableName = rs.getString(1);
			tables.add(tableName);
		}
		if (f_print_table) {
			tables.forEach(System.out::println);
		}
		rs.close();
		stmt.close();
		return tables;
	}

	public GenEntity() {
		super();
	}

	/*
	 * 构造函数 34.
	 */
	public GenEntity(String tablename, Map<String, String> columnComment) {
		// 创建连接
		Connection con;
		// 查要生成实体类的表
		String sql = "select * from " + tablename;
		PreparedStatement pStemt = null;
		try {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			con = (Connection) DriverManager.getConnection(URL, NAME, PASS);
			pStemt = (PreparedStatement) con.prepareStatement(sql);
			ResultSetMetaData rsmd = (ResultSetMetaData) pStemt.getMetaData();
			int size = rsmd.getColumnCount(); // 统计列
			colnames = new String[size];
			colTypes = new String[size];
			colSizes = new int[size];
			for (int i = 0; i < size; i++) {
				colnames[i] = rsmd.getColumnName(i + 1);
				colTypes[i] = rsmd.getColumnTypeName(i + 1);
				if (colTypes[i].equalsIgnoreCase("datetime")) {
					f_util = true;
				}
				if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
					f_sql = true;
				}
				colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
			}

			String content = parse(colnames, colTypes, colSizes, tablename, columnComment);

			try {
				File directory = new File("");
				String path = this.getClass().getResource("").getPath();
				System.out.println(path);
				System.out.println("src/?/" + path.substring(path.lastIndexOf("/com/", path.length())));
				String outputPath = directory.getAbsolutePath() + "/src/" + this.packageOutPath.replace(".", "/") + "/"
						+ initcap(CamelCharOrUnderLine.underlineToCamel(tablename)) + ".java";
				System.out.println(outputPath);
				boolean file = new File(outputPath).isFile();
				if (!f_rewrite) {
					if (file) {
						return;
					}
				}
				FileWriter fw = new FileWriter(outputPath);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(content);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * 功能：生成实体类主体代码
	 * 
	 * @param colnames
	 * @param colTypes
	 * @param colSizes
	 * @return
	 */
	private String parse(String[] colnames, String[] colTypes, int[] colSizes, String tablename,
			Map<String, String> columnComment) {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + this.packageOutPath.substring(startWrite) + ";\r\n");
		// 判断是否导入工具包
		if (f_util) {
			sb.append("import java.util.Date;\r\n");
			if (f_hibernate_annotation) {
				sb.append("import org.springframework.format.annotation.DateTimeFormat;\r\n");
			}
		}
		if (f_sql) {
			sb.append("import java.sql.*;\r\n");
		}
		if (f_hibernate_annotation) {
			sb.append("import javax.persistence.Column;\r\n");
			sb.append("import javax.persistence.Entity;\r\n");
			sb.append("import javax.persistence.Id;\r\n");
			sb.append("import javax.persistence.Table;\r\n");
			sb.append("import org.springframework.stereotype.Component;\r\n");
		}

		sb.append("\r\n");
		// 注释部分
		if (f_class_note) {
			sb.append("   /**\r\n");
			sb.append("    * " + CamelCharOrUnderLine.underlineToCamel(tablename) + " 实体类\r\n");
			sb.append("    * " + new Date() + " " + this.authorName + "\r\n");
			sb.append("    */ \r\n");
		}
		// 实体部分
		if (f_hibernate_annotation) {
			sb.append("@Component" + "\r\n");
			sb.append("@Entity" + "\r\n");
			sb.append("@Table(name = \"" + tablename + "\")" + "\r\n");
		}
		sb.append("public class " + initcap(CamelCharOrUnderLine.underlineToCamel(tablename)) + "{\r\n");
		processAllAttrs(sb, columnComment);// 属性
		if (f_constructor) {
			processAllConstructor(sb, tablename);
		}
		processAllMethod(sb);// get set方法
		if (f_toToString) {
			processToString(sb, tablename);
		}
		sb.append("\r\n}\r\n");
		return sb.toString();
	}

	/**
	 * 功能：生成所有属性
	 * 
	 * @param sb
	 */
	private void processAllAttrs(StringBuffer sb, Map<String, String> columnComment) {

		for (int i = 0; i < colnames.length; i++) {
			if (f_note) {
				sb.append("\r\n\t" + "/**" + columnComment.get(colnames[i]) + "*/" + "\r\n");
			}
			if (f_hibernate_annotation) {
				if (i == 0) {
					sb.append("\t@Id" + "\r\n");
				}
				sb.append("\t" + "@Column(name = \"" + colnames[i] + "\", length = " + colSizes[i] + ")" + "\r\n");
				if (colTypes[i].equalsIgnoreCase("datetime")) {
					sb.append("\t" + "@DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")" + "\r\n");
				}
			}
			sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " "
					+ CamelCharOrUnderLine.underlineToCamel(colnames[i]) + ";\r\n");
		}

	}

	/**
	 * 生成toString方法
	 */
	public void processToString(StringBuffer sb, String tablename) {

		sb.append("\t@Override\r\n" + "	public String toString() {\r\n\t\treturn " + "\""
				+ initcap(CamelCharOrUnderLine.underlineToCamel(tablename)) + " [");
		for (int i = 0; i < colnames.length; i++) {
			if (i == 0) {
				sb.append(CamelCharOrUnderLine.underlineToCamel(colnames[i]) + "=\"" + " + "
						+ CamelCharOrUnderLine.underlineToCamel(colnames[i]) + " + ");
			} else {
				sb.append("\", " + CamelCharOrUnderLine.underlineToCamel(colnames[i]) + "=\"" + " + "
						+ CamelCharOrUnderLine.underlineToCamel(colnames[i]) + " + ");
			}
		}
		sb.append("\"]\";");
		sb.append("\r\n\t}");

	}

	/**
	 * 生成构造方法
	 * 
	 * @param sb
	 */
	private void processAllConstructor(StringBuffer sb, String tablename) {

		sb.append("\r\n\tpublic " + initcap(CamelCharOrUnderLine.underlineToCamel(tablename))
				+ "(){\r\n\t\tsuper();\r\n\t}");
		sb.append("\r\n\tpublic " + initcap(CamelCharOrUnderLine.underlineToCamel(tablename)) + "(" + getAllPram()
				+ ") {" + "\r\n\t\tsuper();\r\n" + pramToThis() + "\t}");
	}

	/**
	 * 得到所有的参数
	 * 
	 * @return
	 */
	private String getAllPram() {
		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < colnames.length; i++) {
			if (i == 0) {
				temp.append(sqlType2JavaType(colTypes[i]) + " " + CamelCharOrUnderLine.underlineToCamel(colnames[i]));
			} else {
				temp.append(", " + sqlType2JavaType(colTypes[i]) + " "
						+ CamelCharOrUnderLine.underlineToCamel(colnames[i]));
			}

		}
		return temp.toString();
	}

	private String pramToThis() {
		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < colnames.length; i++) {
			temp.append("\t\tthis." + CamelCharOrUnderLine.underlineToCamel(colnames[i]) + " = "
					+ CamelCharOrUnderLine.underlineToCamel(colnames[i]) + ";\r\n");
		}
		return temp.toString();
	}

	/**
	 * 功能：生成所有方法
	 * 
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb) {

		for (int i = 0; i < colnames.length; i++) {
			sb.append("\r\n\tpublic void set" + initcap(CamelCharOrUnderLine.underlineToCamel(colnames[i])) + "("
					+ sqlType2JavaType(colTypes[i]) + " " + CamelCharOrUnderLine.underlineToCamel(colnames[i])
					+ "){\r\n");
			sb.append("\r\n\t\tthis." + CamelCharOrUnderLine.underlineToCamel(colnames[i]) + "="
					+ CamelCharOrUnderLine.underlineToCamel(colnames[i]) + ";\r\n");
			sb.append("\t}\r\n");
			sb.append("\r\n\tpublic " + sqlType2JavaType(colTypes[i]) + " get"
					+ initcap(CamelCharOrUnderLine.underlineToCamel(colnames[i])) + "(){\r\n");
			sb.append("\r\n\t\treturn " + CamelCharOrUnderLine.underlineToCamel(colnames[i]) + ";\r\n");
			sb.append("\t}\r\n");
		}

	}

	/**
	 * 功能：将输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private String initcap(String str) {

		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}

		return new String(ch);
	}

	/**
	 * 功能：获得列的数据类型
	 * 
	 * @param sqlType
	 * @return
	 */
	private String sqlType2JavaType(String sqlType) {

		if (sqlType.equalsIgnoreCase("bit")) {
			return "boolean";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "byte";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "short";
		} else if (sqlType.equalsIgnoreCase("int")) {
			return "Integer";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "float";
		} else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
				|| sqlType.equalsIgnoreCase("smallmoney")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
				|| sqlType.equalsIgnoreCase("text")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")) {
			return "Date";
		} else if (sqlType.equalsIgnoreCase("image")) {
			return "Blod";
		}

		return null;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {

		if (f_exportAll) {
			List<String> allTableName = new GenEntity().getAllTableName();

			allTableName.forEach(item -> {
				try {
					new GenEntity(item, getColumnCommentByTableName(allTableName));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}else {
			List<String> tableName = new ArrayList<>();
			tableName.add(dataTableName);
			new GenEntity(dataTableName, getColumnCommentByTableName(tableName));
		}

	}

	/**
	 * 获得某表中所有字段的注释
	 * 
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getColumnCommentByTableName(List<String> tableName) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		for (int i = 0; i < tableName.size(); i++) {
			String table = tableName.get(i);
			ResultSet rs = stmt.executeQuery("show full columns from " + table);
			while (rs != null && rs.next()) {
				map.put(rs.getString("Field"), rs.getString("Comment"));
				map.forEach((k, v) -> {
					if (f_print_note) {
						System.out.println(k + ":" + v);
					}
				});
			}
			rs.close();
		}
		stmt.close();
		return map;
	}

}