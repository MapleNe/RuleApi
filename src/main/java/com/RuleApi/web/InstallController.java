package com.RuleApi.web;

import com.RuleApi.common.RedisHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 初次安装控制器
 *
 * 用户检测数据表和字段是否存在，不存在则添加实现安装
 * */
@Controller
@RequestMapping(value = "/install")
public class InstallController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${mybatis.configuration.variables.prefix}")
    private String prefix;

    @Value("${web.prefix}")
    private String dataprefix;

    @Value("${webinfo.key}")
    private String key;

    RedisHelp redisHelp =new RedisHelp();
    /***
     * 文章删除
     */
    @RequestMapping(value = "/newInstall")
    @ResponseBody
    public String newInstall(@RequestParam(value = "webkey", required = false,defaultValue = "") String  webkey) {
        if(!webkey.equals(this.key)){
            return "请输入正确的访问KEY。如果忘记，可在服务器/opt/application.properties中查看";
        }
        try {
            String isInstall = redisHelp.getRedis(this.dataprefix+"_"+"isInstall",redisTemplate);
            if(isInstall!=null){
                return "虽然重复执行也没关系，但是还是尽量不要频繁点哦，十分钟后再来操作吧！";
            }
        }catch (Exception e){
            return "Redis连接失败或未安装";
        }
        Integer i = 1;
        //判断typecho是否安装，或者数据表前缀是否正确

        String text = "执行信息 ------";
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_users';", Integer.class);
        if (i == 0){
            return "Typecho未安装或者数据表前缀不正确，请尝试安装typecho或者修改properties配置文件。";
        }else{
            text+="Typecho程序确认安装。";
        }
        //修改请求头
        jdbcTemplate.execute("ALTER TABLE "+prefix+"_comments MODIFY agent varchar(500);");
        //查询文章表是否存在views字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_contents' and column_name = 'views';", Integer.class);
        if (i == 0){
            //新增字段
            jdbcTemplate.execute("alter table "+prefix+"_contents ADD views integer(10) DEFAULT 0;");
            text+="内容模块，字段views添加完成。";
        }else{
            text+="内容模块，字段views已经存在，无需添加。";
        }
        //查询文章表是否存在likes字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_contents' and column_name = 'likes';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_contents ADD likes integer(10) DEFAULT 0;");
            text+="内容模块，字段likes添加完成。";
        }else{
            text+="内容模块，字段likes已经存在，无需添加。";
        }
        //查询文章表是否存在isrecommend字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_contents' and column_name = 'isrecommend';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_contents ADD isrecommend integer(2) DEFAULT 0;");
            text+="内容模块，字段isrecommend添加完成。";
        }else{
            text+="内容模块，字段isrecommend已经存在，无需添加。";
        }
        //查询文章表是否存在istop字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_contents' and column_name = 'istop';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_contents ADD istop integer(2) DEFAULT 0;");
            text+="内容模块，字段istop添加完成。";
        }else{
            text+="内容模块，字段istop已经存在，无需添加。";
        }
        //查询用户表是否存在introduce字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_users' and column_name = 'introduce';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_users ADD introduce varchar(255);");
            text+="用户模块，字段introduce添加完成。";
        }else{
            text+="用户模块，字段introduce已经存在，无需添加。";
        }
        //查询用户表是否存在account字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_users' and column_name = 'assets';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_users ADD assets integer(11) DEFAULT 0;");
            text+="用户模块，字段assets添加完成。";
        }else{
            text+="用户模块，字段assets已经存在，无需添加。";
        }
        //查询用户表是否存在address字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_users' and column_name = 'address';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_users ADD address text;");
            text+="用户模块，字段address添加完成。";
        }else{
            text+="用户模块，字段address已经存在，无需添加。";
        }
        //查询用户表是否存在address字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_users' and column_name = 'pay';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_users ADD pay text;");
            text+="用户模块，字段pay添加完成。";
        }else{
            text+="用户模块，字段pay已经存在，无需添加。";
        }
        //查询用户表是否存在customize字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_users' and column_name = 'customize';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_users ADD customize varchar(255) DEFAULT NULL;");
            text+="用户模块，字段customize添加完成。";
        }else{
            text+="用户模块，字段customize已经存在，无需添加。";
        }
        //查询用户表是否存在vip字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_users' and column_name = 'vip';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_users ADD vip integer(10) DEFAULT 0;");
            text+="用户模块，字段vip添加完成。";
        }else{
            text+="用户模块，字段vip已经存在，无需添加。";
        }
        //查询用户表是否存在avatar字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_users' and column_name = 'avatar';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_users ADD avatar text;");
            text+="用户模块，字段avatar添加完成。";
        }else{
            text+="用户模块，字段avatar已经存在，无需添加。";
        }

        //查询分类标签表是否存在imgurl字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_metas' and column_name = 'imgurl';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_metas ADD imgurl varchar(500) DEFAULT NULL;");
            text+="标签分类模块，字段imgurl添加完成。";
        }else{
            text+="标签分类模块，字段imgurl已经存在，无需添加。";
        }
        //查询分类标签表是否存在isrecommend字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_metas' and column_name = 'isrecommend';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_metas ADD isrecommend integer(2) DEFAULT 0;");
            text+="标签分类模块，字段isrecommend添加完成。";
        }else{
            text+="标签分类模块，字段isrecommend已经存在，无需添加。";
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        //判断用户日志表是否存在
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_userlog';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("CREATE TABLE `"+prefix+"_userlog` (" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT," +
                    "  `uid` int(11) NOT NULL DEFAULT '-1' COMMENT '用户id'," +
                    "  `cid` int(11) NOT NULL DEFAULT '0'," +
                    "  `type` varchar(255) DEFAULT NULL COMMENT '类型'," +
                    "  `num` int(11) DEFAULT '0' COMMENT '数值，用于后期扩展'," +
                    "  `created` int(10) NOT NULL DEFAULT '0' COMMENT '时间'," +
                    "  PRIMARY KEY (`id`)" +
                    ") ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户日志（收藏，扩展等）';");
            text+="用户操作模块创建完成。";
        }else{
            text+="用户操作模块已经存在，无需添加。";
        }
        //查询日志表是否存在toid字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_userlog' and column_name = 'toid';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_userlog ADD toid integer(11) DEFAULT 0;");
            text+="用户操作模块，字段toid添加完成。";
        }else{
            text+="用户操作模块，字段toid已经存在，无需添加。";
        }
        //判断用户社会化API表是否存在
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_userapi';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("CREATE TABLE `"+prefix+"_userapi` (" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT," +
                    "  `headImgUrl` varchar(255) DEFAULT NULL COMMENT '头像，可能用不上'," +
                    "  `openId` varchar(255) DEFAULT NULL COMMENT '开放平台ID'," +
                    "  `access_token` varchar(255) DEFAULT NULL COMMENT '唯一值'," +
                    "  `appLoginType` varchar(255) DEFAULT NULL COMMENT '渠道类型'," +
                    "  `uid` int(11) DEFAULT '0' COMMENT '用户ID'," +
                    "  PRIMARY KEY (`id`)" +
                    ") ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='社会化登陆';");
            text+="社会化登录模块创建完成。";
        }else{
            text+="社会化登录模块已经存在，无需添加。";
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        //积分商品&积分阅读模块
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_shop';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("CREATE TABLE `"+prefix+"_shop` (" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT," +
                    "  `title` varchar(300) DEFAULT NULL COMMENT '商品标题'," +
                    "  `imgurl` varchar(500) DEFAULT NULL COMMENT '商品图片'," +
                    "  `text` text COMMENT '商品内容'," +
                    "  `price` int(11) DEFAULT '0' COMMENT '商品价格'," +
                    "  `num` int(11) DEFAULT '0' COMMENT '商品数量'," +
                    "  `type` int(11) DEFAULT '1' COMMENT '商品类型（实体，源码，工具，教程）'," +
                    "  `value` text COMMENT '收费显示（除实体外，这个字段购买后显示）'," +
                    "  `cid` int(11) DEFAULT '-1' COMMENT '所属文章'," +
                    "  `uid` int(11) DEFAULT '0' COMMENT '发布人'," +
                    "  PRIMARY KEY (`id`)" +
                    ") ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='商品表';");
            text+="积分商城模块创建完成。";
        }else{
            text+="积分商城模块已经存在，无需添加。";
        }
        //查询商品表是否存在vipDiscount字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_shop' and column_name = 'vipDiscount';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_shop ADD vipDiscount varchar(255) NOT NULL DEFAULT '0.1' COMMENT 'VIP折扣，权高于系统设置折扣';");
            text+="积分商城模块，字段vipDiscount添加完成。";
        }else{
            text+="积分商城模块，字段vipDiscount已经存在，无需添加。";
        }
        //查询商品表是否存在created字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_shop' and column_name = 'created';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_shop ADD created integer(10) DEFAULT 0;");
            text+="积分商城模块，字段created添加完成。";
        }else{
            text+="积分商城模块，字段created已经存在，无需添加。";
        }
        //查询商品表是否存在status字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_shop' and column_name = 'status';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_shop ADD status integer(10) DEFAULT 0;");
            text+="积分商城模块，字段status添加完成。";
        }else{
            text+="积分商城模块，字段status已经存在，无需添加。";
        }
        //判断充值记录表是否存在
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_paylog';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("CREATE TABLE `"+prefix+"_paylog` (" +
                    "  `pid` int(11) NOT NULL AUTO_INCREMENT," +
                    "  `subject` varchar(255) DEFAULT NULL," +
                    "  `total_amount` varchar(255) DEFAULT NULL," +
                    "  `out_trade_no` varchar(255) DEFAULT NULL," +
                    "  `trade_no` varchar(255) DEFAULT NULL," +
                    "  `paytype` varchar(255) DEFAULT '' COMMENT '支付类型'," +
                    "  `uid` int(11) DEFAULT '-1' COMMENT '充值人ID'," +
                    "  `created` int(10) DEFAULT NULL," +
                    "  `status` int(11) DEFAULT '0' COMMENT '支付状态（0未支付，1已支付）'," +
                    "  PRIMARY KEY (`pid`)" +
                    ") ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='支付渠道充值记录';");
            text+="资产日志模块创建完成。";
        }else{
            text+="资产日志模块已经存在，无需添加。";
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        //添加卡密充值模块
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_paykey';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("CREATE TABLE `"+prefix+"_paykey` (" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT," +
                    "  `value` varchar(255) DEFAULT '' COMMENT '密钥'," +
                    "  `price` int(11) DEFAULT '0' COMMENT '等值积分'," +
                    "  `status` int(2) DEFAULT '0' COMMENT '0未使用，1已使用'," +
                    "  `created` int(10) DEFAULT '0' COMMENT '创建时间'," +
                    "  `uid` int(11) DEFAULT '-1' COMMENT '使用用户'," +
                    "  PRIMARY KEY (`id`)" +
                    ") ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='卡密充值相关';");
            text+="卡密充值模块创建完成。";
        }else{
            text+="卡密充值模块已经存在，无需添加。";
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        //添加API配置中心模块
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_apiconfig';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("CREATE TABLE `"+prefix+"_apiconfig` (" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT," +
                    "  `webinfoTitle` varchar(500) NOT NULL DEFAULT '' COMMENT '网站名称'," +
                    "  `webinfoUrl` varchar(500) NOT NULL DEFAULT '' COMMENT '网站URL'," +
                    "  `webinfoUploadUrl` varchar(255) NOT NULL DEFAULT 'http://127.0.0.1:8081/' COMMENT '本地图片访问路径'," +
                    "  `webinfoAvatar` varchar(500) NOT NULL DEFAULT 'https://cdn.helingqi.com/wavatar/' COMMENT '头像源'," +
                    "  `pexelsKey` varchar(255) NOT NULL DEFAULT '' COMMENT '图库key'," +
                    "  `scale` int(11) NOT NULL DEFAULT '100' COMMENT '一元能买多少积分'," +
                    "  `clock` int(11) NOT NULL DEFAULT '5' COMMENT '签到最多多少积分'," +
                    "  `vipPrice` int(11) NOT NULL DEFAULT '200' COMMENT 'VIP一天价格'," +
                    "  `vipDay` int(11) NOT NULL DEFAULT '300' COMMENT '多少天VIP等于永久'," +
                    "  `vipDiscount` varchar(11) NOT NULL DEFAULT '0.1' COMMENT 'VIP折扣'," +
                    "  `isEmail` int(2) NOT NULL DEFAULT '1' COMMENT '是否开启邮箱注册（关闭后不再验证邮箱）'," +
                    "  `isInvite` int(11) NOT NULL DEFAULT '0' COMMENT '注册是否验证邀请码（默认关闭）'," +
                    "  `cosAccessKey` varchar(300) NOT NULL DEFAULT ''," +
                    "  `cosSecretKey` varchar(300) NOT NULL DEFAULT ''," +
                    "  `cosBucket` varchar(255) NOT NULL DEFAULT ''," +
                    "  `cosBucketName` varchar(255) NOT NULL DEFAULT ''," +
                    "  `cosPath` varchar(255) DEFAULT ''," +
                    "  `cosPrefix` varchar(255) NOT NULL DEFAULT ''," +
                    "  `aliyunEndpoint` varchar(500) NOT NULL DEFAULT ''," +
                    "  `aliyunAccessKeyId` varchar(255) NOT NULL DEFAULT ''," +
                    "  `aliyunAccessKeySecret` varchar(255) NOT NULL DEFAULT ''," +
                    "  `aliyunAucketName` varchar(255) NOT NULL DEFAULT ''," +
                    "  `aliyunUrlPrefix` varchar(255) NOT NULL DEFAULT ''," +
                    "  `aliyunFilePrefix` varchar(255) NOT NULL DEFAULT ''," +
                    "  `ftpHost` varchar(255) NOT NULL DEFAULT ''," +
                    "  `ftpPort` int(11) NOT NULL DEFAULT '21'," +
                    "  `ftpUsername` varchar(255) NOT NULL DEFAULT ''," +
                    "  `ftpPassword` varchar(255) NOT NULL DEFAULT ''," +
                    "  `ftpBasePath` varchar(255) NOT NULL DEFAULT ''," +
                    "  `alipayAppId` varchar(255) NOT NULL DEFAULT ''," +
                    "  `alipayPrivateKey` text," +
                    "  `alipayPublicKey` text," +
                    "  `alipayNotifyUrl` varchar(500) NOT NULL DEFAULT ''," +
                    "  `appletsAppid` varchar(255) NOT NULL DEFAULT ''," +
                    "  `appletsSecret` text," +
                    "  `wxpayAppId` varchar(255) NOT NULL DEFAULT ''," +
                    "  `wxpayMchId` varchar(255) NOT NULL DEFAULT ''," +
                    "  `wxpayKey` text," +
                    "  `wxpayNotifyUrl` varchar(500) DEFAULT ''," +
                    "  PRIMARY KEY (`id`)" +
                    ") ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='api配置信息表';");
            text+="API配置中心模块创建完成。";
            //修改请求头
            jdbcTemplate.execute("INSERT INTO `"+prefix+"_apiconfig` (webinfoTitle) VALUES ('网站名称');");
        }else{
            text+="API配置中心模块已经存在，无需添加。";
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        //查询配置中心表是否存在auditlevel字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_apiconfig' and column_name = 'auditlevel';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_apiconfig ADD auditlevel integer(2) DEFAULT 1;");
            text+="配置中心模块，字段auditlevel添加完成。";
        }else{
            text+="配置中心模块，字段auditlevel已经存在，无需添加。";
        }
        //查询配置中心表是否存在forbidden字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_apiconfig' and column_name = 'forbidden';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_apiconfig ADD forbidden text;");
            text+="配置中心模块，字段forbidden添加完成。";
        }else{
            text+="配置中心模块，字段forbidden已经存在，无需添加。";
        }
        //查询配置中心表是否存在qqAppletsAppid字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_apiconfig' and column_name = 'qqAppletsAppid';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_apiconfig ADD qqAppletsAppid varchar(500) DEFAULT NULL;");
            text+="配置中心模块，字段qqAppletsAppid添加完成。";
        }else{
            text+="配置中心模块，字段qqAppletsAppid已经存在，无需添加。";
        }
        //查询配置中心表是否存在qqAppletsSecret字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_apiconfig' and column_name = 'qqAppletsSecret';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_apiconfig ADD qqAppletsSecret varchar(500) DEFAULT NULL;");
            text+="配置中心模块，字段qqAppletsSecret添加完成。";
        }else{
            text+="配置中心模块，字段qqAppletsSecret已经存在，无需添加。";
        }
        //查询配置中心表是否存在fields字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_apiconfig' and column_name = 'fields';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_apiconfig ADD fields varchar(500) DEFAULT 'able';");
            text+="配置中心模块，字段fields添加完成。";
        }else{
            text+="配置中心模块，字段fields已经存在，无需添加。";
        }
        //查询配置中心表是否存在pushAdsPrice字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_apiconfig' and column_name = 'pushAdsPrice';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_apiconfig ADD `pushAdsPrice` int(11) NOT NULL DEFAULT '100' COMMENT '推流广告价格(积分/天)'");
            text+="配置中心模块，字段pushAdsPrice添加完成。";
        }else{
            text+="配置中心模块，字段pushAdsPrice已经存在，无需添加。";
        }
        //查询配置中心表是否存在pushAdsNum字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_apiconfig' and column_name = 'pushAdsNum';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_apiconfig ADD `pushAdsNum` int(11) NOT NULL DEFAULT '10' COMMENT '推流广告数量'");
            text+="配置中心模块，字段pushAdsNum添加完成。";
        }else{
            text+="配置中心模块，字段pushAdsNum已经存在，无需添加。";
        }
        //查询配置中心表是否存在bannerAdsPrice字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_apiconfig' and column_name = 'bannerAdsPrice';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_apiconfig ADD `bannerAdsPrice` int(11) NOT NULL DEFAULT '100' COMMENT '横幅广告价格(积分/天)'");
            text+="配置中心模块，字段bannerAdsPrice添加完成。";
        }else{
            text+="配置中心模块，字段bannerAdsPrice已经存在，无需添加。";
        }
        //查询配置中心表是否存在bannerAdsNum字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_apiconfig' and column_name = 'bannerAdsNum';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_apiconfig ADD `bannerAdsNum` int(11) NOT NULL DEFAULT '5' COMMENT '横幅广告数量'");
            text+="配置中心模块，字段bannerAdsNum添加完成。";
        }else{
            text+="配置中心模块，字段bannerAdsNum已经存在，无需添加。";
        }
        //查询配置中心表是否存在startAdsPrice字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_apiconfig' and column_name = 'startAdsPrice';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_apiconfig ADD `startAdsPrice` int(11) NOT NULL DEFAULT '100' COMMENT '启动图广告价格(积分/天)'");
            text+="配置中心模块，字段startAdsPrice添加完成。";
        }else{
            text+="配置中心模块，字段startAdsPrice已经存在，无需添加。";
        }
        //查询配置中心表是否存在startAdsNum字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_apiconfig' and column_name = 'startAdsNum';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_apiconfig ADD `startAdsNum` int(11) NOT NULL DEFAULT '1' COMMENT '启动图广告数量'");
            text+="配置中心模块，字段startAdsNum添加完成。";
        }else{
            text+="配置中心模块，字段startAdsNum已经存在，无需添加。";
        }
        //查询配置中心表是否存在epayUrl字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_apiconfig' and column_name = 'epayUrl';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_apiconfig ADD `epayUrl` varchar(500) DEFAULT '' COMMENT '易支付接口地址'");
            text+="配置中心模块，字段epayUrl添加完成。";
        }else{
            text+="配置中心模块，字段epayUrl已经存在，无需添加。";
        }
        //查询配置中心表是否存在epayPid字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_apiconfig' and column_name = 'epayPid';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_apiconfig ADD `epayPid` int(11) COMMENT '易支付商户ID'");
            text+="配置中心模块，字段epayPid添加完成。";
        }else{
            text+="配置中心模块，字段epayPid已经存在，无需添加。";
        }
        //查询配置中心表是否存在epayKey字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_apiconfig' and column_name = 'epayKey';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_apiconfig ADD `epayKey` varchar(300) DEFAULT '' COMMENT '易支付商户密钥'");
            text+="配置中心模块，字段epayKey添加完成。";
        }else{
            text+="配置中心模块，字段epayKey已经存在，无需添加。";
        }
        //查询配置中心表是否存在epayNotifyUrl字段
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_apiconfig' and column_name = 'epayNotifyUrl';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("alter table "+prefix+"_apiconfig ADD `epayNotifyUrl` varchar(500) DEFAULT '' COMMENT '易支付回调地址'");
            text+="配置中心模块，字段epayNotifyUrl添加完成。";
        }else{
            text+="配置中心模块，字段epayNotifyUrl已经存在，无需添加。";
        }
        //添加邀请码模块
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_invitation';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("CREATE TABLE `"+prefix+"_invitation` (" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT," +
                    "  `code` varchar(255) DEFAULT NULL COMMENT '邀请码'," +
                    "  `created` int(10) DEFAULT '0' COMMENT '创建时间'," +
                    "  `uid` int(11) DEFAULT '0' COMMENT '创建者'," +
                    "  `status` int(2) DEFAULT '0' COMMENT '0未使用，1已使用'," +
                    "  PRIMARY KEY (`id`)" +
                    ") ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='邀请码';");
            text+="邀请码模块创建完成。";
        }else{
            text+="邀请码模块已经存在，无需添加。";
        }
        //添加付费广告模块
        i = jdbcTemplate.queryForObject("select count(*) from information_schema.columns where table_name = '"+prefix+"_ads';", Integer.class);
        if (i == 0){
            jdbcTemplate.execute("CREATE TABLE `"+prefix+"_ads` (" +
                    "  `aid` int(11) NOT NULL AUTO_INCREMENT," +
                    "  `name` varchar(255) DEFAULT '' COMMENT '广告名称'," +
                    "  `type` int(11) DEFAULT '0' COMMENT '广告类型（0推流，1横幅，2启动图）'," +
                    "  `img` varchar(500) DEFAULT NULL COMMENT '广告缩略图'," +
                    "  `close` int(10) DEFAULT '0' COMMENT '0代表永久，其它代表结束时间'," +
                    "  `created` int(10) unsigned DEFAULT '0' COMMENT '创建时间'," +
                    "  `price` int(11) unsigned DEFAULT '0' COMMENT '购买价格'," +
                    "  `intro` varchar(500) DEFAULT '' COMMENT '广告简介'," +
                    "  `urltype` int(11) DEFAULT '0' COMMENT '0为APP内部打开，1为跳出APP'," +
                    "  `url` text COMMENT '跳转Url'," +
                    "  `uid` int(11) DEFAULT '-1' COMMENT '发布人'," +
                    "  `status` int(2) DEFAULT '0' COMMENT '0审核中，1已公开，2已到期'," +
                    "  PRIMARY KEY (`aid`)" +
                    ") ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='广告表';");
            text+="付费广告模块创建完成。";
        }else{
            text+="付费广告模块已经存在，无需添加。";
        }
        text+=" ------ 执行结束，安装执行完成";

        redisHelp.setRedis(this.dataprefix+"_"+"isInstall","1",600,redisTemplate);
        return text;
    }
}
