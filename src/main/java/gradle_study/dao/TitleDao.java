package gradle_study.dao;

import java.util.List;

import gradle_study.dto.Title;

/**
 * C(insert) R(select,select where) U(update) D(delete)
 */
public interface TitleDao {
	List<Title> selectTitleByAll();

	Title selectTitleByNo(Title title);

	int insertTitle(Title title);

	int updateTitle(Title title);

	int deleteTitle(int titleNo);
}
