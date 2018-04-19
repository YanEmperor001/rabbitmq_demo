package com.spring.rabbitmq.enums;

public class RabbitMqEnum {

    //交换机
    public enum Exchage {
        CONTRACT_DIRECT("CONTRACT_DIRECT", "点对点"),
        CONTRACT_TOPIC("CONTRACT_TOPIC", "消息订阅");

        private String code;
        private String name;

        Exchage(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    //队列
    public enum Queue {
        TESTQUEUE("TESTQUEUE", "测试队列"),
        TOPICTEST("TOPICTEST", "topic测试队列");

        private String code;
        private String name;

        Queue(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    //队列key
    public enum QueueKey {
        TESTQUEUE("TESTQUEUE", "测试队列"),
        TESTTOPICQUEUE("TOPICQUEUE.#", "topic测试队列");

        private String code;
        private String name;

        QueueKey(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
