<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>更新商品- 叮咚Morning</title>
<link rel="stylesheet" href="${ctxsta}/common/icheck/flat/green.css" />
  <link rel="stylesheet" href="${ctxsta}/common/ztree/css/metroStyle/metroStyle.css" />
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
  <div class="row">
    <div class="col-sm-12">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <h5>更新图片</h5>
          <div class="ibox-tools"> <a class="collapse-link"><i class="fa fa-chevron-up"></i></a> <a class="close-link"><i class="fa fa-times"></i></a> </div>
        </div>
        <div class="ibox-content">
          <form id="form" class="form-horizontal" action="${ctx}/product/list/image/edit" data-method="post">

            <div class="form-group">
              <label class="col-sm-2 col-xs-offset-1 control-label">类型：</label>
              <div class="col-sm-6">
                <select class="form-control" name="organizationId">
                  <option value="1">主要展示</option>
                  <option value="2">详情展示</option>
                </select>
              </div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group m-t">
              <label class="col-sm-2 col-xs-offset-1 control-label">排序：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" name="sort" value="${productImage.sort}">
              </div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group">
              <label class="col-sm-2 col-xs-offset-1 control-label">展示图片：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" name="picImg" value="${product.picImg}">
              </div>
              <a class="btn btn-info view-button"> <i class="fa fa-image"> </i> 查看 </a>
            </div>
            <div class="form-group">
              <div class="col-sm-7 col-xs-offset-3">
                <input type="file" class="form-control">
              </div>
              <button class="btn btn-success upload-button" type="button"><i class="fa fa-upload"></i>&nbsp;&nbsp;<span class="bold">上传</span> </button>
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
  <script src="${ctxsta}/common/switchery/switchery.min.js"></script>
  <!-- 自定义js --> 
  <script src="${ctxsta}/cms/js/product.js"></script>

  <!-- iCheck -->
  <script src="${ctxsta}/common/icheck/icheck.min.js"></script>
</myfooter>
</body>
</html>
