package net.ktds.drink.category.biz;

import java.util.List;

import net.ktds.drink.category.vo.CategoryVO;

public interface CategoryBiz {
	public List<CategoryVO> getAllCategoryById(String categoryId);

	public List<CategoryVO> getAllCategory();
	
	public List<CategoryVO> setCategoryLevel();
	
	public boolean isCategoryLeafNode(String parentCategoryId);
}
