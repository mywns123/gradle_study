package gradle_study.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gradle_study.dto.Department;
import gradle_study.dto.Title;
import gradle_study.util.jdbcUtil;

public class TransactionService {

	public String transAddTitleAndDepartment(Title title, Department dept) {
		String titlesql = "insert into title values(?,?)";
		String deptsql = "insert into Department values(?,?,?)";
		Connection con = null;
		PreparedStatement tPstmt = null;
		PreparedStatement dPstmt = null;
		String res = null;
		try {
			con = jdbcUtil.getConnection();
			con.setAutoCommit(false);
			tPstmt = con.prepareStatement(titlesql);
			tPstmt.setInt(1, title.gettNo());
			tPstmt.setString(2, title.gettName());
			tPstmt.executeUpdate();

			dPstmt = con.prepareStatement(deptsql);
			dPstmt.setInt(1, dept.getDeptno());
			dPstmt.setString(2, dept.getDeptName());
			dPstmt.setInt(3, dept.getFloor());
			dPstmt.executeUpdate();

			con.commit();
			res = "commit";

		} catch (SQLException e) {
			res = "rollback";
			rollbackUtil(con);
		} finally {
			System.out.println(res);
			closeUtil(con, tPstmt, dPstmt);
		}
		return res;
	}

	public void closeUtil(Connection con, PreparedStatement tPstmt, PreparedStatement dPstmt) {
		try {
			con.setAutoCommit(true);
			if (tPstmt != null)
				tPstmt.close();
			if (dPstmt != null)
				dPstmt.close();
			tPstmt.close();
			dPstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rollbackUtil(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public int transRemoveTitleAndDepartment(Title title, Department dept) {
		String titlesql = "delete from title where tno=?";
		String deptsql = "delete from Department where deptno=?";
		Connection con = null;
		PreparedStatement tPstmt = null;
		PreparedStatement dPstmt = null;
		int res = 0;
		try {
			con = jdbcUtil.getConnection();
			con.setAutoCommit(false);

			System.out.println("res > " + res);
			tPstmt = con.prepareStatement(titlesql);
			tPstmt.setInt(1, title.gettNo());
			res += tPstmt.executeUpdate();
			System.out.println("res > " + res);

			dPstmt = con.prepareStatement(deptsql);
			dPstmt.setInt(1, dept.getDeptno());
			res += dPstmt.executeUpdate();
			System.out.println("res > " + res);

			if (res == 2) {
				con.commit();
				System.out.println("commit()");
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			rollbackUtil(con);
		} finally {
			System.out.println(res);
			closeUtil(con, tPstmt, dPstmt);
		}
		return res;
	}

}
