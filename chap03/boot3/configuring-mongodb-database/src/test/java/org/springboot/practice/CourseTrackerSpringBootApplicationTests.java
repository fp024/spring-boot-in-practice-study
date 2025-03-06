package org.springboot.practice;

import static org.assertj.core.api.Assertions.assertThat;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@DataMongoTest
class CourseTrackerSpringBootApplicationTests {

  @Autowired private MongoTemplate mongoTemplate;

  @Test
  void givenObjectAvailableWhenSaveToCollectionThenExpectValue() {
    // given
    DBObject object =
        BasicDBObjectBuilder.start() //
            .add("Manning", "Spring Boot In Practice")
            .get();
    // when
    mongoTemplate.save(object, "collection");
    // then
    assertThat(mongoTemplate.findAll(DBObject.class, "collection"))
        .extracting("Manning")
        .containsOnly("Spring Boot In Practice");
  }
}
