package net.ktds.drink.category.dao;

import java.util.List;

import net.ktds.drink.category.vo.CategoryVO;

public interface CategoryDao {
	public List<CategoryVO> getAllCategory(String parentCategoryId);
}
