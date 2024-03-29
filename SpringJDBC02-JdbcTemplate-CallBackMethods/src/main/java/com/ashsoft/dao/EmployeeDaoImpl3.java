package com.ashsoft.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ashsoft.bo.EmployeeBO;

@Repository("empDAO")
public class EmployeeDaoImpl3 implements IEmployeeDao {

	@Autowired
	private JdbcTemplate jt;

	private static final String GET_EMP_BY_ENO = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE EMPNO = ?";

	private static final String GET_EMPS_BY_DESG = "SELECT EMPNO,ENAME,JOB, SAL FROM EMP WHERE JOB IN(?,?,?)";

	@Override
	public EmployeeBO getEmployeeByNo(int eno) {

		EmployeeBO ebo = jt.queryForObject(GET_EMP_BY_ENO, new BeanPropertyRowMapper<EmployeeBO>(EmployeeBO.class)

										   , eno);

		return ebo;

	}

	@Override
	public List<EmployeeBO> getEmployeesByDesgs(String desg1, String desg2, String desg3) {

		BeanPropertyRowMapper<EmployeeBO> rowMapper = new BeanPropertyRowMapper<EmployeeBO>(EmployeeBO.class);

		List<EmployeeBO> listBO = jt.query(GET_EMPS_BY_DESG,
											new RowMapperResultSetExtractor<EmployeeBO>(rowMapper), desg1, desg2, desg3);

		return listBO;
	}

}
