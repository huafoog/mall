1.何时创建用户钱包纪录？。新用户在注册时可创建。老用户写脚本创建。

2.创建用户钱包纪录根据用的钱包纪录信息生产一个sign。并同时创建一条流水纪录。

3.操作钱包表时，要先获取一条纪录。生产一个sign值，然后根据纪录的sign值进行对比。相同则继续走正常的步骤。不同时，则应该终止后续操作，并发通知给相关人员进行排查异常。

4.重新生成sign值，并正常更新数据，纪录流水纪录。

5.支付纪录表，应该是每发起一次支付，就要生成一条支付纪录。同一个订单对应多个支付纪录。
