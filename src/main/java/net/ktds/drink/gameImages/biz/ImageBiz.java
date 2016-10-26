package net.ktds.drink.gameImages.biz;

import java.util.List;

import net.ktds.drink.gameImages.vo.ImageVO;

public interface ImageBiz {
	public List<ImageVO> getAllImageList();
	public boolean deleteImage(String imageId);
	public boolean upLoadImage(ImageVO image);
	
}
