<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>修改订单商品详情 - 叮咚Morning</title>
<link rel="stylesheet" href="${ctxsta}/common/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="${ctxsta}/common/icheck/flat/green.css" />
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
  <div class="row">
    <div class="col-sm-12">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <h5>修改订单商品详情<small> 商品详情信息时应当遵循合法、正当、必要的原则，明示目的、方式和范围。</small></h5>
          <div class="ibox-tools"> <a class="collapse-link"><i class="fa fa-chevron-up"></i></a> <a class="close-link"><i class="fa fa-times"></i></a> </div>
        </div>
        <div class="ibox-content">
          <form id="form" class="form-horizontal" action="${ctx}/system/order/list/${order.orderId}/product/${orderProduct.orderProductId}" data-method="put">
            <div class="form-group">
              <label class="col-sm-2 col-xs-offset-1 control-label">商品编号：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" readonly name="productNumber" value="${orderProduct.productNumber}">
              </div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group m-t">
              <label class="col-sm-2 col-xs-offset-1 control-label">商品名称：</label>
              <div class="col-sm-7">
                <input type="text" disabled="" name="name" value="${orderProduct.name}" class="form-control">
              </div>
            </div>
            <div class="hr-line-dashed"></div>
              <div class="form-group">
                <label class="col-sm-2 col-xs-offset-1 control-label">展示图片：</label>
                <div class="col-sm-7">
                  <input type="text" disabled="" class="form-control" name="picImg" value="${orderProduct.picImg}">
                </div>
                <a class="btn btn-info view-button"> <i class="fa fa-image"> </i> 查看 </a>
              </div>
            <c:forEach items="${productSpecDTO.kindVOs}" var="kind" varStatus="i">
              <div class="hr-line-dashed"></div>
              <div class="form-group">
                <label class="col-sm-2 col-xs-offset-1 control-label">${kind.name}：</label>
                <div class="col-sm-9">
                  <input type="hidden" name="kind" value="spec${i.index}">
                  <c:forEach items="${kind.kindAttributes}" var="kindAttribute">
                    <label class="radio-inline">
                      <input type="radio" name="spec${i.index}" value="${kindAttribute.specAttrId}-${kindAttribute.name}" ${fn:contains(spec, kindAttribute.specAttrId) ?'checked="checked"':''}>
                      ${kindAttribute.name}</label>
                  </c:forEach>
                </div>
              </div>
            </c:forEach>
            <div class="hr-line-dashed"></div>
            <div class="form-group">
              <label class="col-sm-2  col-xs-offset-1 control-label">购买数量：</label>
              <div class="col-sm-7">
                <input type="number" class="form-control" name="buyNumber" value="${orderProduct.buyNumber}">
              </div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group">
              <div class="col-sm-12 text-center">
                <button class="btn btn-primary" type="submit">提交</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<myfooter> 
  <!-- Data picker --> 
  <script src="${ctxsta}/common/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script> 
  <script src="${ctxsta}/common/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script> 
  <script src="${ctxsta}/common/bootstrap-prettyfile/bootstrap-prettyfile.js"></script> 
  <!-- 自定义js --> 
  <script src="${ctxsta}/cms/js/orderProduct.js"></script>
</myfooter>
</body>
</html>
