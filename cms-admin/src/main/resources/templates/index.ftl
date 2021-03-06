<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-3.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>CMS manage</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <script src="/util/jquery.min.js"></script>
    <script src="/util/util.js"></script>
    <script src="/util/jquery-editable-select.js"></script>
    <script src="/util/jquery.form.js"></script>
    <script src="/js/echarts.js"></script>
    <script src="../qrcode/jquery.qrcode.min.js"></script>
    <script src="/js/cookie.js"></script>
    <script src="/js/content.js"></script>
    <script src="https://cdn.ckeditor.com/ckeditor5/11.0.1/classic/ckeditor.js"></script>
    <link rel="stylesheet" href="/util/jquery-editable-select.css">
</head>
<body class="layui-layout-body">

<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">Gangster-CMS</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">


        <#--站点选择区域-->
            <li class="layui-nav-item">
                <a>选择站点</a>
                <dl class="layui-nav-child">
                <#list siteList as site>
                    <dd id="choose_site_${site.siteId}"><a
                            onclick="changeSite(${site.siteId})"> ${site.siteName}</a></dd>
                </#list>
                </dl>
            </li>
            <button class="layui-btn">
                <a class="layui-icon" href="/index?flush=yes">刷新权限</a>
            </button>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="" id="userinfo">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                ${user.userName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a onclick="showAtRight('/module/information.html')">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>

            <li class="layui-nav-item"><a onclick="showAtRight('/module/mail/listToReadMail.html')">最新邮件<span
                    class="layui-badge">${mailTotalNum!'0'}</span></a></li>
            <li class="layui-nav-item"><a href="/logout">退了</a></li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->


            <#--模块选择区域-->
            <#list moduleTreeList as tree>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">${tree.module.moduleName}</a>
                    <dl class="layui-nav-child">
                        <#list tree.list as module>
                            <dd id="sidebar:/module${module.moduleUrl}">
                                <a onclick="showAtRight('/module${module.moduleUrl}')">${module.moduleName}</a>
                            </dd>
                        </#list>
                    </dl>
                </li>
            </#list>

            </ul>
        </div>
    </div>
    <div class="layui-body" id="content">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">内容主体区域</div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © Gangster-trio CMS.
    </div>
</div>
<!--<script src="/layui/"></script>-->
<script src="/layui/layui.all.js" type="text/javascript"></script>
</body>
</html>
<script>
    var siteId = ${currentSite.siteId!'0'};
    var siteName = "${currentSite.siteName!'null'}";
    var currentSite;
    if (siteId === 0) {
        alert("您当前没有任何好在哪点的管理权限,请联系管理员")
    } else {
        init(siteId, siteName)
    }

    function init(id, name) {
        siteId = id;
        setCookie("siteId", siteId);
        layer.msg("当前站点:" + name, {icon: 6});
        $("#choose_site_" + id).addClass("layui-this");
        $.ajax({
            'url': '/index?siteId=' + id
            , 'success': function (data) {
            }
            , 'async': false
            , 'dataType': 'json'
        });

        $.ajax({
            'url': '/site/details/' + id
            , 'success': function (data) {
                currentSite = data;
                showAtRight("/module/count.html");
            }
            , 'async': false
            , 'dataType': 'json'
        })
    }

    function changeSite(id) {
        window.location.href = "/index?siteId=" + id;
    }

    oldSelectNode = $('[id="' + 'sidebar:module/count.html' + '"]');

    //不要妄图修改这里
    window.onpopstate = (ev => {
        if (ev.state !== "") {
            showAtRight(ev.state, false, false);
            let rawId = 'sidebar:' + ev.state;
            //id含有特殊字符不可使用ID选择器
            oldSelectNode.removeClass("layui-this");
            let highlightNode = $('[id="' + rawId + '"]');
            highlightNode.addClass("layui-this");
            oldSelectNode = highlightNode;
        }
    })
</script>
