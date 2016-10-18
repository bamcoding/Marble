package net.ktds.category.biz;

import java.util.List;

import net.ktds.category.dao.CategoryDao;
import net.ktds.category.dao.CategoryDaoImpl;
import net.ktds.category.vo.CategoryVO;

public class CategoryBizImpl implements CategoryBiz{
	
	private CategoryDao dao;
	public CategoryBizImpl() {
		super();
		dao = new CategoryDaoImpl();
	}

	@Override
	public List<CategoryVO> getAllCategory() {
		return dao.getAllCategory();
	}

}
