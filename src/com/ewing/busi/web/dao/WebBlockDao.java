package com.ewing.busi.web.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.web.model.WebBlock;
import com.ewing.common.constants.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.utils.SqlUtil;

/**
 * web模板分组配置屬性
 * 
 * @author tansonlam
 * @createDate 2016年3月3日
 * 
 */
@Repository("webBlockDao")
public class WebBlockDao {

    @Resource
    private BaseDao baseDao;

    /**
     * 查询多个分组的属性块
     * 
     * @param shopId
     * @param groupKeys
     * @return
     */
    public List<WebBlock> listGroupBlock(Integer shopId, String[] groupKeys) {
        StringBuilder query = new StringBuilder();
        query.append(" and shop_id = ").append(IsEff.EFFECTIVE.getValue());
        query.append(" and group_key in (").append(SqlUtil.array2InCondition(groupKeys))
                .append(")");
        query.append(" and iseff = '").append(IsEff.EFFECTIVE.getValue()).append("'");
        return baseDao.find(query.toString(), WebBlock.class);
    }
}
