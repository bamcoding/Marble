package net.ktds.drink.category.dao;

import java.util.List;

import net.ktds.drink.category.vo.CategoryVO;

public interface CategoryDao {
	public List<CategoryVO> getAllCategoryById(String parentCategoryId);
	public List<CategoryVO> getAllCategory();
	
	public int addCategory(String name, String parentName);
	public int modifyCategory(String input, String selectedName);
	public int deleteCategory(String selectedName);
	public int countName(String input);
	public int countChild(String input);
}
