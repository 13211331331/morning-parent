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
          <h5>更新商品<small> 商品信息时应当遵循合法、正当、必要的原则，明示目的、方式和范围。</small></h5>
          <div class="ibox-tools"> <a class="collapse-link"><i class="fa fa-chevron-up"></i></a> <a class="close-link"><i class="fa fa-times"></i></a> </div>
        </div>
        <div class="ibox-content">
          <form id="form" class="form-horizontal" action="${ctx}/product/list/" data-method="put">
            <div class="form-group m-t">
              <label class="col-sm-2 col-xs-offset-1 control-label">商品名称：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" name="name" value="${product.name}">
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
            <div class="form-group m-t">
              <label class="col-sm-2 col-xs-offset-1 control-label">标签：</label>
              <div class="col-sm-7">
                <label class="radio-inline">
                  <input type="radio" name="labelId" value="" ${empty product.labelId ?'checked="checked"':''}>
                  无</label>
                <c:forEach items="${labels}" var="label">
                  <label class="radio-inline">
                    <input type="radio" name="labelId" value="${label.labelId}" ${product.labelId eq label.labelId ?'checked="checked"':''}>
                    ${label.labelName}</label>
                </c:forEach>
              </div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group m-t">
              <label class="col-sm-2 col-xs-offset-1 control-label">积分：</label>
              <div class="col-sm-7">
                <input type="number" class="form-control" name="showScore" value="${product.showScore}">
              </div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group m-t">
              <label class="col-sm-2 col-xs-offset-1 control-label">价格：</label>
              <div class="col-sm-7">
                <input type="number" class="form-control" name="showPrice" value="${product.showPrice}">
              </div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group">
              <label class="col-sm-2 col-xs-offset-1 control-label">所属种类：</label>
              <div class="col-sm-9">
                <div id="ztree" class="ztree"></div>
              </div>
            </div>
              <div class="hr-line-dashed"></div>
              <c:forEach items="${productParameters}" var="parameter" varStatus="i">
                <div class="form-group m-t paramFormGroup" id="${i.index}">
                  <label class="col-sm-2 col-xs-offset-1 control-label"><c:if test="${i.first}">商品参数：</c:if></label>
                  <div class="col-sm-2">
                    <input type="text" class="form-control" name="paramNames" value="${parameter.name}">
                  </div>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="paramValues" value="${parameter.value}">
                  </div>
                  <div class="col-sm-1">
                    <button type="button" class="btn btn-default" onclick="deleteParamInput(${i.index})"><i class="glyphicon glyphicon-minus"></i></button>
                  </div>
                </div>
              </c:forEach>
              <div class="form-group m-t paramFormGroup">
                <label class="col-sm-2 col-xs-offset-1 control-label"><c:if test="${empty productParameters}">商品参数：</c:if></label>
                <div class="col-sm-1">
                  <button type="button" class="btn btn-default" onclick="addParamInput()"><i class="glyphicon glyphicon-plus"></i></button>
                </div>
              </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group m-t">
              <label class="col-sm-2 col-xs-offset-1 control-label">商品简介：</label>
              <div class="col-sm-7">
                <textarea class="form-control" rows="2" name="introduce" placeholder="请输入简介...">${product.introduce}</textarea>
              </div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group">
              <label class="col-sm-2 col-xs-offset-1 control-label">设置：</label>
              <div class="col-sm-9">
                <label class="radio-inline">
                  <input type="checkbox" class="js-switch" name="showInTop" value="1" ${product.showInTop eq '1'?'checked="checked"':''}/>
                  是否置顶</label>
                <label class="radio-inline">
                  <input type="checkbox" class="js-switch" name="showInNav" value="1" ${product.showInNav eq '1'?'checked="checked"':''}/>
                  是否导航栏</label>
                <label class="radio-inline">
                  <input type="checkbox" class="js-switch" name="showInHot" value="1" ${product.showInHot eq '1'?'checked="checked"':''} />
                  是否热门</label>
              </div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group">
              <label class="col-sm-2 col-xs-offset-1 control-label">是否上架：</label>
              <div class="col-sm-9">
                <label class="radio-inline">
                  <input type="checkbox" class="js-switch" name="showInShelve" value="1" ${product.showInShelve eq '1'?'checked="checked"':''}/>
                  是否上架</label>
              </div>
            </div>
              <div class="hr-line-dashed"></div>
              <div class="form-group m-t">
                  <label class="col-sm-2 col-xs-offset-1 control-label">搜索关键字：</label>
                  <div class="col-sm-7">
                      <input type="text" class="form-control" name="searchKey" value="${product.searchKey}">
                  </div>
              </div>
              <div class="hr-line-dashed"></div>
              <div class="form-group m-t">
                  <label class="col-sm-2 col-xs-offset-1 control-label">页面标题：</label>
                  <div class="col-sm-7">
                      <input type="text" class="form-control" name="pageTitle" value="${product.pageTitle}">
                  </div>
              </div>
              <div class="hr-line-dashed"></div>
              <div class="form-group m-t">
                  <label class="col-sm-2 col-xs-offset-1 control-label">页面描述：</label>
                  <div class="col-sm-7">
                      <textarea class="form-control" rows="2" name="pageDescription" placeholder="请输入简介...">${product.pageDescription}</textarea>
                  </div>
              </div>
              <div class="hr-line-dashed"></div>
              <div class="form-group m-t">
                  <label class="col-sm-2 col-xs-offset-1 control-label">页面关键字：</label>
                  <div class="col-sm-7">
                      <input type="text" class="form-control" name="pageKeyword" value="${product.pageKeyword}">
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
  <script src="${ctxsta}/common/switchery/switchery.min.js"></script>
  <!-- 自定义js --> 
  <script src="${ctxsta}/cms/js/product.js"></script>
  <!-- ztree -->
  <script src="${ctxsta}/common/ztree/js/jquery.ztree.all.min.js"></script>
  <!-- 自定义js -->
  <script type="text/javascript">
      var treedata = '${categories}';
  </script>
  <!-- iCheck -->
  <script src="${ctxsta}/common/icheck/icheck.min.js"></script>
</myfooter>
</body>
</html>
