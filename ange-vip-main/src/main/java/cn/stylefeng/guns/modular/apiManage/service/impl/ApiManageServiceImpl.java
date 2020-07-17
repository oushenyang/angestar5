package cn.stylefeng.guns.modular.apiManage.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.apiManage.entity.ApiManage;
import cn.stylefeng.guns.modular.apiManage.mapper.ApiManageMapper;
import cn.stylefeng.guns.modular.apiManage.model.params.ApiManageParam;
import cn.stylefeng.guns.modular.apiManage.model.result.ApiManageResult;
import  cn.stylefeng.guns.modular.apiManage.service.ApiManageService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 接口管理  服务实现类
 * </p>
 *
 * @author shenyang.ou
 * @since 2020-05-21
 */
@Service
public class ApiManageServiceImpl extends ServiceImpl<ApiManageMapper, ApiManage> implements ApiManageService {

    @Override
    public void add(ApiManageParam param){
        ApiManage entity = getEntity(param);
        entity.setAppId(1L);
        this.save(entity);
    }

    @Override
    public void delete(ApiManageParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void batchRemove(String ids){
        List<String> idList = Arrays.asList(ids.split(","));
        this.removeByIds(idList);
    }

    @Override
    public void update(ApiManageParam param){
        ApiManage oldEntity = getOldEntity(param);
        ApiManage newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ApiManageResult findBySpec(ApiManageParam param){
        return null;
    }

    @Override
    public List<Map<String, Object>> findListBySpec(Page page, ApiManageParam param){
        return baseMapper.findListBySpec(page,param);
    }

    @Override
    public LayuiPageInfo findPageBySpec(ApiManageParam param){
        Page pageContext = getPageContext();
        QueryWrapper<ApiManage> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ApiManageParam param){
        return param.getApiManageId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ApiManage getOldEntity(ApiManageParam param) {
        return this.getById(getKey(param));
    }

    private ApiManage getEntity(ApiManageParam param) {
        ApiManage entity = new ApiManage();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}