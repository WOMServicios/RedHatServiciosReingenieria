bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic productOfferingTopic


curl  http://0.0.0.0:8889/bill/message/generator