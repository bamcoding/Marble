package net.ktds.drink.category.biz;

import java.util.List;

import net.ktds.drink.category.vo.CategoryVO;

public interface CategoryBiz {
	public List<CategoryVO> getAllCategory(String categoryId);
	public boolean isCategoryLeafNode(String parentCategoryId);
}
