import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class Test {
    public static void main(String[] args) {

        KafkaSource kafkaSource = new KafkaSource();
        KafkaConsumer<String, String> consumer = kafkaSource.getConsumer();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {





                //写数据
                FileSink fileSink = new FileSink();
                fileSink.writeFile(record.value(),"text.txt");
            }
        }
    }
}
