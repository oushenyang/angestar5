package cn.stylefeng.guns.modular.agent.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.agent.entity.AgentExamine;
import cn.stylefeng.guns.modular.agent.model.params.AgentCardParam;
import cn.stylefeng.guns.modular.agent.model.params.AgentExamineParam;
import cn.stylefeng.guns.modular.agent.service.AgentExamineService;
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
 * 代理审核表控制器
 *
 * @author shenyang.ou
 * @Date 2020-12-09 16:46:23
 */
@Controller
@RequestMapping("/actExamine")
public class AgentExamineController extends BaseController {

    private String PREFIX = "/modular/actExamine";

    @Autowired
    private AgentExamineService agentExamineService;

    /**
     * 跳转到主页面
     *
     * @author shenyang.ou
     * @Date 2020-12-09
     */
    @RequestMapping("")
    public String index(Model model, Integer type) {
        model.addAttribute("type", type);
        return PREFIX + "/actExamine.html";
    }

    /**
     * 开发者端新增接口
     *
     * @author shenyang.ou
     * @Date 2020-12-09
     */
    @RequestMapping("/developerAddItem")
    @ResponseBody
    public ResponseData developerAddItem(AgentExamineParam agentExamineParam) {
        this.agentExamineService.developerAddItem(agentExamineParam);
        return ResponseData.success();
    }

    /**
     * 同意代理接口
     *
     * @author shenyang.ou
     * @Date 2020-05-22
     */
    @RequestMapping("/actAgree")
    @ResponseBody
    public ResponseData actAgree(AgentExamineParam agentExamineParam) {
        this.agentExamineService.agree(agentExamineParam);
        return ResponseData.success();
    }

    /**
     * 拒绝代理接口
     *
     * @author shenyang.ou
     * @Date 2020-05-22
     */
    @RequestMapping("/actRefuse")
    @ResponseBody
    public ResponseData actRefuse(AgentExamineParam agentExamineParam) {
        this.agentExamineService.actRefuse(agentExamineParam);
        return ResponseData.success();
    }

    /**
     * 查询列表
     *
     * @author shenyang.ou
     * @Date 2020-12-09
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(AgentExamineParam agentExamineParam) {
        //获取分页参数
        Page page = LayuiPageFactory.defaultPage();
        agentExamineParam.setCreateUser(LoginContextHolder.getContext().getUserId());
        //根据条件查询操作日志
        List<Map<String, Object>> result = agentExamineService.findListBySpec(page, agentExamineParam);
        page.setRecords(result);
        return LayuiPageFactory.createPageInfo(page);
    }

}


