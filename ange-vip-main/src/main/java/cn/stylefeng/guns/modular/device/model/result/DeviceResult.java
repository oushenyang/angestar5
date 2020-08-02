package cn.stylefeng.guns.modular.device.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 绑定设备信息
 * </p>
 *
 * @author shenyang.ou
 * @since 2020-08-02
 */
@Data
public class DeviceResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 设备id
     */
    private Long deviceId;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 卡密或账号id
     */
    private Long cardOrUserId;

    /**
     * 卡密或账号
     */
    private String cardOrUserCode;

    /**
     * 卡密类型；1-单码；2-注册码
     */
    private Integer cardType;

    /**
     * mac地址
     */
    private String mac;

    /**
     * IP
     */
    private String ip;

    /**
     * 设备型号
     */
    private String model;

    /**
     * ip详细地址
     */
    private String ipAddress;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private Long updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

}