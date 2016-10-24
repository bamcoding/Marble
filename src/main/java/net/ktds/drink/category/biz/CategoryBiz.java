package net.ktds.drink.category.biz;

import java.util.List;

import net.ktds.drink.category.vo.CategoryVO;

public interface CategoryBiz {
	public List<CategoryVO> getAllCategoryById(String categoryId);

	public List<CategoryVO> getAllCategory();
	
	public List<CategoryVO> setCategoryLevel();
	
	public boolean isCategoryLeafNode(String parentCategoryId);
	
	
	public boolean addCategory(CategoryVO categoryVO);

	public boolean modifyCategory(String input, String selectedName);

	public boolean deleteCategory(String selectedName);

	public boolean checkExistName(String input);
	public boolean checkExistChild(String input);
	
	public int getNewCategoryId();
}
