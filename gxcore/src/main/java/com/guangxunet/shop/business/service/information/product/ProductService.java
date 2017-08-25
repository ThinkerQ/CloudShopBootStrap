package com.guangxunet.shop.business.service.information.product;

import com.guangxunet.shop.business.dao.DaoSupport;
import com.guangxunet.shop.base.system.Page;
import com.guangxunet.shop.base.system.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("productService")
public class ProductService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ProductMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("ProductMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("ProductMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ProductMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ProductMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ProductMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ProductMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/*
	* 删除小图片
	*/
	public void delTp1(PageData pd)throws Exception{
		dao.update("ProductMapper.delTp1", pd);
	}
	
	/*
	 * 删除大图片
	 */
	public void delTp2(PageData pd)throws Exception{
		dao.update("ProductMapper.delTp2", pd);
	}
}

