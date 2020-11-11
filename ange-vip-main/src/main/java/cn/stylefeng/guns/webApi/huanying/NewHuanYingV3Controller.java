package cn.stylefeng.guns.webApi.huanying;

import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.modular.appPower.service.AppPowerService;
import cn.stylefeng.guns.sys.core.auth.util.RedisUtil;
import cn.stylefeng.guns.sys.core.util.CustomEnAndDe;
import cn.stylefeng.guns.sys.modular.system.service.DictService;
import cn.stylefeng.roses.core.util.HttpContext;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 *
 * @author shenyang.ou
 * @version 1.0
 * @date 2020/7/16
 * @since JDK 1.8
 */
@Controller
@RequestMapping("api/v3")
public class NewHuanYingV3Controller {

    @Autowired
    private DictService dictService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private AppPowerService appPowerService;

    @RequestMapping("/user")
    @ResponseBody
    public String user(@RequestHeader(value = "User-Token", required = false) String token){
        //应用名称
        String virtualId = HttpContext.getRequest().getParameter("virtual_id");
        String sign;
        if (StringUtils.isEmpty(virtualId)||StringUtils.isEmpty(token)){
            return null;
        }else {
            String deSign = CustomEnAndDe.deCrypto(token);
//            String time =  deSign.substring(deSign.length() -7);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMd");
//            Date date = new Date(System.currentTimeMillis());
//            String newTime = simpleDateFormat.format(date);
//            //说明盗版
//            if (!time.equals(newTime)){
//                sign = deSign;
//            }else {
                //从数据库里查是否正版
                //去除最后七位
                sign = deSign.substring(0,deSign.length()-8);
//            }
        }
        boolean whetherLegal = appPowerService.whetherLegalBySignAndAppCodeNoInsert(sign,virtualId);
        Map<String, Object> map = new HashMap<>();
        if (whetherLegal){
            map.put("data","h9M9kQsr/d+/mG43m8PfkKl1eJGN4Jrr2/XvSj2zVs/aZpXPZgg73oJcUjlpK33BVIbdFMBuuRMr82QrX3uRBvXozAZVWs2k95uAzFGJmFNlbYXQTvhr+vzOtYaWOW0FOFrNgHV0JZDO28Fmcb8DY+l9n44wZN7syIaHl/SDFA58FBGMVZVTm9jM+ApjKInmchgxljBCEOcxyo7YsvjMFT1ILzrgMzvoaaIMuqP7B4NpPp4x2Hbf2tbKNT55CvLuL8E4ctWmIDHNmzefT7gvWgFEvQImldioI4UWdxaBcwV4Q8vv4/xv0aMBHXyuvkW1yg83i01pCEBaUDszCm7uq4ptk0GD1grdysRgW+NWsPIXSLSsmhvQTkJzMUW7jpJZN35gzerV9kUxMMSaJunU/fzGnfR1iJ/VGBw00HS0kiJoX3cZEoN15VBwYbvIIcaKepeyR9SCiVXQLGPOdxbqOEaAdbwQiMbb1l5ZELRyaeeGTirbVVlVxMnk8Zi3j91rs5Jfjka9dLQIcI3sc5vFpt57vQOX1hffOLq7U1t5FxNq7S/qIom1/tqXr03cvMLtCYjayE8CFxiplfNk2EdfkLHhvFaSLJwei6MGAWG3meGQs8TUUb3P1UUAEOWALAoFEPV0YRIAP7Cs86hapIKjy1W6NE4NZoj2xaLKs2oobUHzMKEJ02GHCXwLT7z9EJ6dTdtnJ9tht4OnUIm+tS5GLPAIX7zjxhnKnbKP3A4v7JZ6QIhUjDCAJfnWuDjtTqwXhg31f6XtRoyarn5LyiXDZFy824TweySPrSCk9NnwoteJQ/J1gtKcQgSlgqA1bcEIV3vLzd1gpeHd4zZJF0esIamdzMumIEBYCwmuSvpgRFt+siLJ6kMdKi3f90oRu4mILVWGG7mVAdWboraKa+qIgKz9Xfuo6ajmMEAB+lzkufgCXRkg+sZsWDhDkuQrRJWYw1G07puNpN0G/+gM0nWwCQ==");
            map.put("message", ConstantsContext.getPirateContact());
            map.put("code", 141);
        }else {
            map.put("data","h9M9kQsr/d+/mG43m8PfkKl1eJGN4Jrr2/XvSj2zVs/aZpXPZgg73oJcUjlpK33BVIbdFMBuuRMr82QrX3uRBvXozAZVWs2k95uAzFGJmFNlbYXQTvhr+vzOtYaWOW0FOFrNgHV0JZDO28Fmcb8DY+l9n44wZN7syIaHl/SDFA58FBGMVZVTm9jM+ApjKInmchgxljBCEOcxyo7YsvjMFT1ILzrgMzvoaaIMuqP7B4NpPp4x2Hbf2tbKNT55CvLuL8E4ctWmIDHNmzefT7gvWgFEvQImldioI4UWdxaBcwV4Q8vv4/xv0aMBHXyuvkW1yg83i01pCEBaUDszCm7uq4ptk0GD1grdysRgW+NWsPIXSLSsmhvQTkJzMUW7jpJZN35gzerV9kUxMMSaJunU/fzGnfR1iJ/VGBw00HS0kiJoX3cZEoN15VBwYbvIIcaKepeyR9SCiVXQLGPOdxbqOEaAdbwQiMbb1l5ZELRyaeeGTirbVVlVxMnk8Zi3j91rs5Jfjka9dLQIcI3sc5vFpt57vQOX1hffOLq7U1t5FxNq7S/qIom1/tqXr03cvMLtCYjayE8CFxiplfNk2EdfkLHhvFaSLJwei6MGAWG3meGQs8TUUb3P1UUAEOWALAoFEPV0YRIAP7Cs86hapIKjy1W6NE4NZoj2xaLKs2oobUHzMKEJ02GHCXwLT7z9EJ6dTdtnJ9tht4OnUIm+tS5GLPAIX7zjxhnKnbKP3A4v7JZ6QIhUjDCAJfnWuDjtTqwXhg31f6XtRoyarn5LyiXDZFy824TweySPrSCk9NnwoteJQ/J1gtKcQgSlgqA1bcEIV3vLzd1gpeHd4zZJF0esIamdzMumIEBYCwmuSvpgRFt+siLJ6kMdKi3f90oRu4mILVWGG7mVAdWboraKa+qIgKz9Xfuo6ajmMEAB+lzkufgCXRkg+sZsWDhDkuQrRJWYw1G07puNpN0G/+gM0nWwCQ==");
            map.put("message", "success");
            map.put("code", 200);
        }

        JSONObject json = new JSONObject(map);
        return json.toString();
    }

    @RequestMapping("/now")
    @ResponseBody
    public String now(){
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("now", new Date().getTime());
        map.put("data",map1);
        map.put("message", "ok");
        map.put("code", 0);
        JSONObject json = new JSONObject(map);
        return json.toString();
    }

    @RequestMapping("/recell")
    @ResponseBody
    public String recell(){
        Map<String, Object> map = new HashMap<>();
        map.put("data","");
        map.put("message", "some error in database");
        map.put("code", 141);
        JSONObject json = new JSONObject(map);
        return json.toString();
    }

    @RequestMapping("/appactive")
    @ResponseBody
    public String appactive(@RequestHeader(value = "User-Token", required = false) String token){
        //应用名称
        String virtualId = HttpContext.getRequest().getParameter("virtual_id");
        String sign;
        if (StringUtils.isEmpty(virtualId)||StringUtils.isEmpty(token)){
            return null;
        }else {
            String deSign = CustomEnAndDe.deCrypto(token);
//            String time =  deSign.substring(deSign.length() -8);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMd");
//            Date date = new Date(System.currentTimeMillis());
//            String newTime = simpleDateFormat.format(date);
//            //说明盗版
//            if (!time.equals(newTime)){
//                sign = deSign;
//            }else {
                //从数据库里查是否正版
                //去除最后七位
                sign = deSign.substring(0,deSign.length()-8);
//            }
        }
        boolean whetherLegal = appPowerService.whetherLegalBySignAndAppCodeNoInsert(sign,virtualId);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("content", ConstantsContext.getPirateContact());
        map1.put("title", "重要通知");
        map1.put("type", "");
        if (whetherLegal){
            map1.put("show", 1);
        }else {
            map1.put("show", 0);
        }

        map.put("data",map1);
        map.put("message", "ok");
        map.put("code", 0);
        JSONObject json = new JSONObject(map);
        return json.toString();
    }

    @RequestMapping("/stepinfo")
    @ResponseBody
    public String stepinfo(){
        Map<String, Object> map = new HashMap<>();
        map.put("data","");
        map.put("message", "no data in database");
        map.put("code", 199);
        JSONObject json = new JSONObject(map);
        return json.toString();
    }

    @RequestMapping("/vipprice")
    @ResponseBody
    public String vipprice(){
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("vip7", "-9.9");
        map1.put("vip6", 359.9);
        map1.put("vip5", 139.9);
        map1.put("vip4", 49.9);
        map1.put("vip3", 25.9);
        map1.put("vip2", -11.9);
        map1.put("vip1", 3.9);
        map1.put("vip4_5", 89.9);
        map1.put("vip9", "-39.9");
        map1.put("vip8", "-29.9");
        map1.put("vip10", "-89.9");
        map.put("data",map1);
        map.put("message", "ok");
        map.put("code", 0);
        JSONObject json = new JSONObject(map);
        return json.toString();
    }
}