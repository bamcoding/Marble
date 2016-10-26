package net.ktds.drink.gameImages.dao;

import java.util.List;

import net.ktds.drink.gameImages.vo.ImageVO;

public interface ImageDao {
	public int upLoadImage(ImageVO image);
	public List<ImageVO> downLoadImage();
}
