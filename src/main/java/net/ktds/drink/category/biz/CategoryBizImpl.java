package net.ktds.drink.category.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.ktds.drink.category.dao.CategoryDao;
import net.ktds.drink.category.dao.CategoryDaoImpl;
import net.ktds.drink.category.vo.CategoryVO;

public class CategoryBizImpl implements CategoryBiz {

	private CategoryDao dao;

	public CategoryBizImpl() {
		super();
		dao = new CategoryDaoImpl();
	}

	@Override
	public List<CategoryVO> getAllCategoryById(String parentCategoryId) {
		return dao.getAllCategoryById(parentCategoryId);
	}

	public boolean isCategoryLeafNode(String categoryId) {
		return dao.getAllCategoryById(categoryId).size() == 0;
	}

	@Override
	public List<CategoryVO> getAllCategory() {
		return dao.getAllCategory();
	}

	public List<CategoryVO> setCategoryLevel() {
		List<CategoryVO> categoryVOs = dao.getAllCategory();
		List<CategoryVO> hasLevel = setLevel("0", 0, categoryVOs, new ArrayList<CategoryVO>());
		return hasLevel;
	}

	private List<CategoryVO> setLevel(String root, int count, List<CategoryVO> categoryVOs,
			List<CategoryVO> arrayList) {
		count++;
		for (CategoryVO category : categoryVOs) {
			if (category.getParentCategoryId().equals(root) && !category.getCategoryName().equalsIgnoreCase("root")) {
				if (category.getLevel() == null) {
					category.setLevel(count + "");
					arrayList.add(category);
					setLevel(category.getCategoryId(), count, categoryVOs, arrayList);
				} else {
					break;
				}
			}
		}
		return arrayList;
	}

	@Override
	public boolean addCategory(CategoryVO categoryVO) {
		return dao.addCategory(categoryVO) > 0;
	}

	@Override
	public boolean modifyCategory(String input, String selectedName) {
		return dao.modifyCategory(input, selectedName) > 0;
	}

	@Override
	public boolean deleteCategory(String selectedName) {
		return dao.deleteCategory(selectedName) > 0;
	}

	@Override
	public boolean checkExistName(String input) {
		return dao.countName(input) > 0;
	}
	
	public boolean checkExistChild(String input){
		return dao.countChild(input) > 0;
	}

	@Override
	public int getNewCategoryId() {
		return dao.getNewCategoryId();
	}
	
}
