<html>
<head>
    <meta charset="utf-8">
    <title>订单详情</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-4 column">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        订单id
                    </th>
                    <th>
                        总额
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        ${orderDTO.orderId}
                    </td>
                    <td>
                        ${orderDTO.orderAmount}
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="col-md-12 column">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        商品id
                    </th>
                    <th>
                        商品名称
                    </th>
                    <th>
                        价格
                    </th>
                    <th>
                        数量
                    </th>
                    <th>
                        总额
                    </th>
                </tr>
                </thead>
                <tbody>
                <#list orderDTO.orderDetails as orderDetail>
                <tr>
                    <td>
                        ${orderDetail.productId}
                    </td>
                    <td>
                        ${orderDetail.productName}
                    </td>
                    <td>
                        ${orderDetail.productPrice}
                    </td>
                    <td>
                        ${orderDetail.productQuantity}
                    </td>
                    <td>
                        ${orderDetail.productQuantity * orderDetail.productPrice}
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>


        <div class="col-md-12 column">
            <#if orderDTO.getOrderStatusEnum().meg == "新下单">
                <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>
                <a href="/sell/seller/order/cancle?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
            </#if>
        </div>

    </div>
</div>

</body>
</html>