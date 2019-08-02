<html>
    <head>
        <meta charset="utf-8">
        <title>卖家商品列表</title>
        <link href="https://cdn.bootcss.com/twitter-bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>
                            订单id
                        </th>
                        <th>
                            姓名
                        </th>
                        <th>
                            手机号
                        </th>
                        <th>
                            地址
                        </th>
                        <th>
                            金额
                        </th>
                        <th>
                            订单状态
                        </th>
                        <th>
                            支付状态
                        </th>
                        <th>
                            创建时间
                        </th>
                        <th colspan="2">
                            操作
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list orderDTOPage.content as orderDTO>
                    <tr>
                        <td>
                            ${orderDTO.orderId}
                        </td>
                        <td>
                            ${orderDTO.buyerName}
                        </td>
                        <td>
                            ${orderDTO.buyerPhone}
                        </td>
                        <td>
                            ${orderDTO.buyerAddress}
                        </td>
                        <td>
                            ${orderDTO.orderAmount}
                        </td>
                        <td>
                            ${orderDTO.getOrderStatusEnum().meg}
                        </td>
                        <td>
                            ${orderDTO.getPayStatusEnum().msg}
                        </td>
                        <td>
                            ${orderDTO.createTime}
                        </td>
                        <td>
                            详情
                        </td>
                        <td>
                            <#if orderDTO.getOrderStatusEnum().meg != "取消">
                                <a href="/sell/seller/order/cancle?orderId=${orderDTO.orderId}">取消</a>
                            </#if>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
            <#--分页-->
            <div class="col-md-12 column">
                <ul class="pagination pull-right">
                    <#if currentpage lte 1>
                        <li class="disabled">
                            <a href="#">上一页</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/sell/seller/order/list?page=${currentpage - 1}&size=#{size}">上一页</a>
                        </li>
                    </#if>
                    <#list 1..orderDTOPage.getTotalPages() as index>
                    <#if currentpage == index>
                        <li class="disabled">
                            <a href="#">${index}</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/sell/seller/order/list?page=${index}&size=#{size}">${index}</a>
                        </li>
                    </#if>

                    </#list>

                    <#if currentpage gte orderDTOPage.totalPages>
                        <li class="disabled">
                            <a href="#">下一页</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/sell/seller/order/list?page=${currentpage + 1}&size=#{size}">下一页</a>
                        </li>
                    </#if>
                </ul>
            </div>
        </div>
    </div>
    </body>
</html>