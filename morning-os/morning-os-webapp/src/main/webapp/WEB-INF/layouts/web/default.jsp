<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title><sitemesh:write property='title' /> - 但行好事，莫问前程</title>
	<meta name="author" content="猫宁Morning. - 但行好事，莫问前程。" />  
	<meta name="keywords" content="猫宁商城,猫宁Morning,猫宁公益商城,公益,电子商城,猫宁社区,公益商城,在线购物,社会责任">
	<meta name="description" content="猫宁Morning公益商城是中国公益性在线电子商城，以商城B2C模式运营的公益在线商城，是一家致力于将传统公益商城互联网化的创新公益商城。">
	<link rel="shortcut icon" href="${ctxsta}/web/img/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="${ctxsta}/common/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${ctxsta}/web/css/mystyle.css">
	<script> var t1 = new Date().getTime(); baselocation='${ctx}';</script>
	<sitemesh:write property='head' />
  </head>
  <body>
  	<!-- 页面内容控制开始 -->
	<div id="page-containner">
	 	<!-- 公共头引入 -->
		<jsp:include page="/WEB-INF/layouts/web/header.jsp" />
		<!-- 公共头引入 -->
		<sitemesh:write property='body' />
		<!-- 公共底引入 -->
		<jsp:include page="/WEB-INF/layouts/web/footer.jsp" />
		<!-- 公共底引入 -->
	</div>
	<!-- 全局js -->
	<script src="${ctxsta}/common/jquery/jquery-2.2.3.min.js"></script>
	<script src="${ctxsta}/common/bootstrap/js/bootstrap.min.js"></script>
	<!-- 自定义js -->
	<script src="${ctxsta}/web/js/myapp.js"></script>
	<script src="${ctxsta}/web/js/jump.js"></script>
	<script src="${ctxsta}/web/js/base.js"></script>
	<!-- 第三方插件 -->
	<sitemesh:write property='myfooter' />

  </body>
</html>