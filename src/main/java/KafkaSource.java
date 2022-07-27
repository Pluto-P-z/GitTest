import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class KafkaSource {
    Properties props = new Properties();
    KafkaConsumer<String, String> consumer;

    public KafkaConsumer<String, String> getConsumer() {
        return consumer;
    }

    public void setConsumer(KafkaConsumer<String, String> consumer) {
        props.put("bootstrap.servers", "192.168.19.4:9092");
        props.put("group.id", "groupIdName");
        props.put("enable.auto.commit", "false");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("test-topic"));
        this.consumer = consumer;
    }


    public KafkaSource(){
        setConsumer(consumer);
    }
    public ConsumerRecords getSource() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
//        for (ConsumerRecord<String, String> record : records) {
////                    System.out.printf("comsumer:>>>>>offset = %d, key= %s , value = %s\n", record.offset(),
////                            record.key(), record.value());
//            System.out.println(record.value());
////                    String r = record.offset() + "\t" + record.key() + "\t" + record.value();
////                    System.out.println(r);
////                    System.out.println("addforbranch");
//
//        }
            return records;
        }
    }
}
