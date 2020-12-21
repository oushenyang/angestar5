package cn.stylefeng.guns.modular.agent.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.agent.entity.AgentBuyCard;
import cn.stylefeng.guns.modular.agent.model.params.AgentBuyCardParam;
import cn.stylefeng.guns.modular.agent.service.AgentBuyCardService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * 代理购卡记录控制器
 *
 * @author shenyang.ou
 * @Date 2020-12-11 12:34:59
 */
@Controller
@RequestMapping("/agentBuyCard")
public class AgentBuyCardController extends BaseController {

    private String PREFIX = "/modular/agentBuyCard";

    @Autowired
    private AgentBuyCardService agentBuyCardService;

    /**
     * 跳转到主页面
     *
     * @author shenyang.ou
     * @Date 2020-12-11
     */
    @RequestMapping("")
    public String index(Model model, Integer type) {
        model.addAttribute("type", type);
        return PREFIX + "/agentBuyCard.html";
    }

    /**
     * 新增接口
     *
     * @author shenyang.ou
     * @Date 2020-12-11
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(AgentBuyCardParam agentBuyCardParam) {
        this.agentBuyCardService.add(agentBuyCardParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author shenyang.ou
     * @Date 2020-12-11
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(AgentBuyCardParam agentBuyCardParam) {
        this.agentBuyCardService.delete(agentBuyCardParam);
        return ResponseData.success();
    }


    /**
     * 查看详情接口
     *
     * @author shenyang.ou
     * @Date 2020-12-11
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(AgentBuyCardParam agentBuyCardParam) {
        AgentBuyCard detail = this.agentBuyCardService.getById(agentBuyCardParam.getAgentBuyCardId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author shenyang.ou
     * @Date 2020-12-11
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(AgentBuyCardParam agentBuyCardParam) {
//        return this.agentBuyCardService.findPageBySpec(agentBuyCardParam);
        //获取分页参数
        Page page = LayuiPageFactory.defaultPage();
        agentBuyCardParam.setCreateUser(LoginContextHolder.getContext().getUserId());
        //根据条件查询操作日志
        List<Map<String, Object>> result = agentBuyCardService.findListBySpec(page, agentBuyCardParam);
        page.setRecords(result);
        return LayuiPageFactory.createPageInfo(page);
    }

}

