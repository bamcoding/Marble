package net.ktds.drink.category.biz;

import java.util.List;

import net.ktds.drink.category.dao.CategoryDao;
import net.ktds.drink.category.dao.CategoryDaoImpl;
import net.ktds.drink.category.vo.CategoryVO;

public class CategoryBizImpl implements CategoryBiz{
	
	private CategoryDao dao;
	public CategoryBizImpl() {
		super();
		dao = new CategoryDaoImpl();
	}

	@Override
	public List<CategoryVO> getAllCategory(String parentCategoryId) {
		return dao.getAllCategory(parentCategoryId);
	}
	
	public boolean isCategoryLeafNode(String categoryId) {
		return getAllCategory(categoryId).size() == 0;
	}

}
