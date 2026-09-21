package jdbc;

import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class ConnectionFactoryTest01 {
    static void main(String[] args) {
        Producer producer = Producer.builder().name("Studio Deen").build();
        Producer producerUpdate = Producer.builder().id(1).name("madhouse").build();
//        jdbc.ProducerService.save(producer);
//        jdbc.ProducerService.delete(4);
//        jdbc.ProducerService.update(producerUpdate);
//        List<jdbc.Producer> producers = jdbc.ProducerService.findAll();
//        List<jdbc.Producer> producers = jdbc.ProducerService.findByName("Mad");
//        log.info("Producers found '{}'",producers);
//        jdbc.ProducerService.showProducerMetaData();
//        jdbc.ProducerService.showDriverMetaData();
//        jdbc.ProducerService.showTypeScrollWorking();
//        List<jdbc.Producer> producers = jdbc.ProducerService.findByNameAndUpdateToUpperCase("Deen");
//        List<jdbc.Producer> producers = jdbc.ProducerService.findByNameAndInsertWhenNotFound("A-1 pictures");
//        jdbc.ProducerService.findByNameAndDelete("A-1 pictures");
//        List<jdbc.Producer> producers = jdbc.ProducerService.findByNamePreparedStatement("Bo");
//        log.info("Producers found '{}'",producers);
//        jdbc.ProducerService.updatePreparedStatement(producerUpdate);
        List<Producer> producers = ProducerService.findByNameCallableStatement("NHK");
        log.info("Producers found '{}'",producers);
    }
}
