package net.ktds.drink.gameImages.biz;

import java.util.List;

import net.ktds.drink.gameImages.dao.ImageDao;
import net.ktds.drink.gameImages.dao.ImageDaoImpl;
import net.ktds.drink.gameImages.vo.ImageVO;

public class ImageBizImpl implements ImageBiz{
	private ImageDao dao;
	public ImageBizImpl() {
		super();
		dao = new ImageDaoImpl();
	}

	public boolean upLoadImage(ImageVO image) {
		return dao.upLoadImage(image) > 0;
	}

	@Override
	public List<ImageVO> getAllImageList() {
		return dao.getAllImageList();
	}

	@Override
	public boolean deleteImage(String imageId) {
		return dao.deleteImage(imageId)>0;
	}


}
