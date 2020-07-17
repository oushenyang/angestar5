package cn.stylefeng.guns.webApi.card;

import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.core.constant.state.RedisType;
import cn.stylefeng.guns.modular.apiManage.entity.ApiManage;
import cn.stylefeng.guns.modular.apiManage.service.ApiManageService;
import cn.stylefeng.guns.modular.remote.entity.RemoteData;
import cn.stylefeng.guns.sys.core.auth.util.RedisUtil;
import cn.stylefeng.guns.sys.modular.system.entity.DictType;
import cn.stylefeng.roses.core.util.HttpContext;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>单码用户登录</p>
 *
 * @author shenyang.ou
 * @version 1.0
 * @date 2020/5/28
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/cardLogin")
public class CardLoginController {
    private final ApiManageService apiManageService;

    private final RedisUtil redisUtil;

    public CardLoginController(ApiManageService apiManageService, RedisUtil redisUtil) {
        this.apiManageService = apiManageService;
        this.redisUtil = redisUtil;
    }

    @RequestMapping("/{appId}")
    @ResponseBody
    public Object cardLogin(@PathVariable String appId) {
        Map<String, String[]> cookies = HttpContext.getRequest().getParameterMap();
        ApiManage apiManage;
        if (redisUtil.hasKey(RedisType.CALL_CODE + appId)){
            apiManage = (ApiManage) redisUtil.get(RedisType.CALL_CODE + appId);
        }else {
            QueryWrapper<ApiManage> wrapper = new QueryWrapper<>();
            wrapper.eq("call_code", appId);
            wrapper.eq("api_code", "cardLogin");
            apiManage = apiManageService.getOne(wrapper);
            if (ObjectUtil.isNotNull(apiManage)){
                redisUtil.set(RedisType.CALL_CODE + appId,apiManage);
            }else {
                //接口错误
            }
        }
        String singleCode = null; String edition = null; String mac = null; String model = null; String appCode = null; String sgin = null;
        for (Map.Entry<String, String[]> m : cookies.entrySet()) {
            System.out.println("key:" + m.getKey() + " value:" + String.join("", m.getValue()));
            if (apiManage.getParameterOne().equals(m.getKey())){
                singleCode = String.join("", m.getValue());
            }else if (apiManage.getParameterTwo().equals(m.getKey())){
                edition = String.join("", m.getValue());
            }else if (apiManage.getParameterThree().equals(m.getKey())){
                mac = String.join("", m.getValue());
            }else if (apiManage.getParameterFour().equals(m.getKey())){
                model = String.join("", m.getValue());
            }else if (apiManage.getParameterFive().equals(m.getKey())){
                appCode = String.join("", m.getValue());
            }else if (apiManage.getParameterSix().equals(m.getKey())){
                sgin = String.join("", m.getValue());
            }
        }

        System.out.println(cookies);
        System.out.println(singleCode);
        System.out.println(edition);
        System.out.println(mac);
        System.out.println(model);
        return singleCode;
    }
}