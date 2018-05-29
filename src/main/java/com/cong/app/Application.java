package com.cong.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cong.dao.BaseDao;
import com.cong.entity.Table;
import com.cong.util.CommonUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 生成类
 * @author cong
 *
 */
public class Application {
	
	
	public static void main(String[] args) throws Exception {
//		File file = new File("fsa");
//		System.out.println(file.exists());
		
		
		//创建某数据库中所有表对应的实体类
		new Application().createAllEntity("ssm", "entity.txt",null);
		
		//创建指定某个数据库中几个表对应的实体类
		String[] arr = {"sys_role","sys_user"};
		List<String> list = Arrays.asList(arr);
		new Application().createAllEntity("cong", "entity.txt", list);
		
		//创建某数据库中所有表对应的dao接口
		new Application().createAllDao("ssm","basedao.txt","Dao.java",null);
		//创建某数据库中所有表对应的dao接口的实现类
		new Application().createAllDao("ssm","basedaoImpl.txt","DaoImpl.java",null);
		
	}
	
	/**
	 * 创建某数据库中所有表对应的实体类
	 * @param databaseName 数据库名
	 * @param templateName 模板文件名
	 * @param list 表名集合
	 */
	@SuppressWarnings({ "resource", "unused" })
	public void createAllEntity(String databaseName,String templateName,List<String> list) throws Exception {
		System.out.println(templateName);
		String templatePath = this.getClass().getClassLoader().getResource(templateName).getPath().replace(templateName, "");
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
		BaseDao baseDao = ac.getBean("baseDao", BaseDao.class);
		//1、获取模板
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_21);
		Locale.setDefault(Locale.ENGLISH);
		configuration.setDirectoryForTemplateLoading(new File(templatePath));
		Template template = configuration.getTemplate(templateName);
		
		//2、得到某数据库里面所有的表名
		if(list == null) {
			list = baseDao.selectTableName(databaseName);
		}

		
		//3、通过表名得到对应的表信息（表字段名、表字段类型、表字段备注）
		List<Table> tables = new ArrayList<Table>();
		for (String string : list) {
			Table table = baseDao.selectColumnAndType(databaseName,string);
			tables.add(table);
		}
		
		for (Table table : tables) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("className", table.getTableName());
			paramMap.put("nameList", table.getList());
			Writer writer = new OutputStreamWriter(new FileOutputStream("doc/entity/" + table.getTableName() + ".java"),
					"UTF-8");
			template.process(paramMap, writer);
		}
		
		System.out.println("生成成功--------------------------------");
	}
	
	/**
	 * 创建某数据库中所有表对应的dao接口
	 * @param databaseName 数据库名
	 * @param templateName 模板名
	 * @param suffix 后缀
	 * @param list 表名集合
	 * @throws Exception 
	 */
	public void createAllDao(String databaseName,String templateName,String suffix,List<String> list) throws Exception {
		System.out.println(templateName);
		String templatePath = this.getClass().getClassLoader().getResource(templateName).getPath().replace(templateName, "");
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
		BaseDao baseDao = ac.getBean("baseDao", BaseDao.class);
		//1、获取模板
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_21);
		Locale.setDefault(Locale.ENGLISH);
		configuration.setDirectoryForTemplateLoading(new File(templatePath));
		Template template = configuration.getTemplate(templateName);
		
		//2、得到某数据库里面所有的表名
		if(list == null) {
			list = baseDao.selectTableName(databaseName);
			System.out.println(list);
		}
		
		//3、根据模板生成目标文件
		for (String string : list) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("className", CommonUtil.toHumpName(string, 0));
			paramMap.put("repository", CommonUtil.toHumpName(string, 1));
			Writer writer = new OutputStreamWriter(new FileOutputStream("doc/dao/" + CommonUtil.toHumpName(string, 0) + suffix),
					"UTF-8");
			template.process(paramMap, writer);
		}
		
		System.out.println("生成成功--------------------------------");
	}
	
	
}
