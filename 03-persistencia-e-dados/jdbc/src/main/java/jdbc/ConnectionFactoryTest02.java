package jdbc;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConnectionFactoryTest02 {
    static void main(String[] args) {
        Producer producerToUpdate = Producer.builder().id(1).name("MADHOUSE2").build();
        ProducerServiceRowSet.updateCachedRowSet(producerToUpdate);
//        log.info("------------------------");
//        List<jdbc.Producer> producers = jdbc.ProducerServiceRowSet.findByNameJdbcRowSet("");
//        log.info(producers);

    }
}
