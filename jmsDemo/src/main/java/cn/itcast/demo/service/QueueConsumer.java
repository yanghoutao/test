package cn.itcast.demo.service;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueConsumer {
    public static void main(String[] args) throws Exception {

        //1.创建工厂连接
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.25.135:61616");
        //2.获取链接
        Connection connection=connectionFactory.createConnection();
        //3.启动连接
        connection.start();
        //4.获取session(参数 1：是否启动事务,参数 2：消息确认模式)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建队列对象
        Queue queue = session.createQueue("test-queue");
        //6.创建消费对象
        MessageConsumer consumer = session.createConsumer(queue);
        //7.设置监听
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage= (TextMessage) message;
                try {
                    System.out.println("提取消息:"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //8.等待建盘输入
        System.in.read();
        //9.关闭资源
        consumer.close();
        session.close();
        connection.close();

    }
}
